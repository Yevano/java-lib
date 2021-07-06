package yevano.configcore;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import lombok.Getter;
import lombok.NonNull;
import lombok.val;

public class ConfigManager<T> implements Closeable {
    private final Path DEFAULT_CONFIG_DIRECTORY = FileSystems.getDefault().getPath("config");

    private final Path configRoot;
    private final Class<T> configClass;
    private final Logger logger;
    
    private final Path configFilePath;
    private final WatchService watchService;
    private final Thread watchTask;
    private final Gson gson;

    // NOTE: Marked volatile so that updates from the watch thread always happen before reads from
    // other threads.
    @Getter private volatile T config = null;
    private final AtomicBoolean enableWatch;

    public ConfigManager(@NonNull String fileName, @NonNull Class<T> configClass, Logger logger) throws IOException {
        this.configFilePath = FileSystems.getDefault().getPath(fileName);
        this.configClass = configClass;
        this.logger = logger;

        this.gson = buildGson();
        this.configRoot = DEFAULT_CONFIG_DIRECTORY;

        this.enableWatch = new AtomicBoolean(true);
        this.watchService = FileSystems.getDefault().newWatchService();
        
        this.configRoot.register(
            watchService,
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY
        );

        init();

        this.watchTask = new Thread(this::watch);
        this.watchTask.start();
    }

    public void forceLoad() throws FileNotFoundException, IOException {
        Path configPath = configRoot.resolve(configFilePath);
        getConfigFromFileSystem(configPath);
    }

    private Gson buildGson() {
        return new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.PROTECTED)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .setPrettyPrinting()
            .create();
    }

    private void init() {
        File configDir = new File("config");
        if(!configDir.exists()) {
            configDir.mkdir();

            Bukkit.getLogger().info(() ->
                String.format("Created config directory %s.", configDir.getAbsolutePath())
            );
        }

        Path configPath = configRoot.resolve(configFilePath);

        if(configPath.toFile().exists()) {
            Bukkit.getLogger().info(() ->
                String.format("Loading config from %s.", configPath.toAbsolutePath())
            );

            boolean success = getConfigFromFileSystemFirstTime(configPath);
            
            if(success) {
                logger.info("Successfully loaded custom config from file system.");
            } else {
                logger.info("Error loading custom config from file system.");
            }

            return;
        }

        getConfigFromClassPath(configPath);
    }

    private boolean getConfigFromFileSystemFirstTime(@NonNull Path path) {
        try {
            getConfigFromFileSystem(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void watch() {
        for(;;) {
            try {
                WatchKey key = watchService.take();
                if(enableWatch.get()) handleDirEvents(key);
                if(!key.reset()) return;
            } catch(InterruptedException e) {
                // Thread can die now.
                break;
            } catch(Throwable e) {
                e.printStackTrace();
            }
        }

        closeWatchService();
    }

    private void handleDirEvents(@NonNull WatchKey key) {
        for(WatchEvent<?> event : key.pollEvents()) {
            val kind = event.kind();
            if(!kind.type().equals(Path.class)) continue;

            Path path = (Path) event.context();
            String eventType = kind.name();

            if(!configFilePath.equals(path)) continue;

            if(eventType.equals("ENTRY_CREATE") || eventType.equals("ENTRY_MODIFY")) {
                handleConfigModification(configRoot.resolve(path));
            }
        }
    }

    private void handleConfigModification(@NonNull Path path) {
        logger.info(() -> "Reloading config.");

        try {
            getConfigFromFileSystem(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        logger.info(() -> String.format("Successfully reloaded %s.", path.getFileName().toString()));
    }

    private void closeWatchService() {
        try {
            watchService.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing config watch service.", e);
        }
    }

    private void loadConfig(@NonNull InputStream inputStream)
        throws IOException, JsonSyntaxException
    {
        try(InputStreamReader reader = new InputStreamReader(inputStream)) {
            T conf = gson.fromJson(reader, configClass);
            this.config = conf;
        }
    }

    private Optional<InputStream> getDefaultConfigInputStream(@NonNull Path configPath) {
        return Optional.ofNullable(configClass.getResourceAsStream("/" + configPath.toString()));
    }

    private void getConfigFromFileSystem(@NonNull Path path)
        throws FileNotFoundException, IOException
    {
        try {
            loadConfig(new FileInputStream(path.toFile()));
        } catch(JsonSyntaxException e) {
            Bukkit.getLogger().info(() ->
                String.format("Could not parse custom config.", path)
            );

            e.printStackTrace();
        }
    }

    private void getConfigFromClassPath(@NonNull Path path) {
        Optional<InputStream> defaultConfigStreamOpt = getDefaultConfigInputStream(path);
        File file = path.toFile();

        if(defaultConfigStreamOpt.isPresent()) {
            try {
                loadConfig(defaultConfigStreamOpt.get());

                logger.info(() ->String.format("Successfully loaded default config from file system."));

                val fileWriteInStream = getDefaultConfigInputStream(path).get();
                val outStream = new FileOutputStream(file);

                IOUtils.copy(fileWriteInStream, outStream);
                outStream.close();

                logger.info("Default config has been written to file system.");
            } catch(JsonSyntaxException e) {
                logger.info(() -> String.format("Could not parse default config. %s.", path));
                e.printStackTrace();
            } catch(IOException e) {
                logger.info(() ->String.format("Could not write default config to %s.", path));
                e.printStackTrace();

                withWatchDisabled(() -> {
                    if(file.exists()) {
                        file.delete();
                    }
                });
            }
        } else {
            logger.info(() ->String.format("Did not find %s in the plugin classpath.", path.toString()));
        }
    }

    private void withWatchDisabled(@NonNull Runnable runnable) {
        this.enableWatch.set(false);
        runnable.run();
        this.enableWatch.set(true);
    }

    @Override
    public void close() {
        this.watchTask.interrupt();
    }
}
