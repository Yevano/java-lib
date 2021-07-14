package yevano.bukkit;

import java.io.Closeable;
import java.util.function.Consumer;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import yevano.resource.CloseableResourceWrapper;
import yevano.resource.Destructor;
import yevano.resource.Resource;
import yevano.resource.ResourceStack;

public abstract class ResourceOwningPlugin extends JavaPlugin {
    private final ResourceStack resourceStack = new ResourceStack();

    protected <T extends Resource> T pushResource(@NonNull T resource) {
        return resourceStack.pushResource(resource);
    }

    protected <T extends Closeable> T pushResource(@NonNull T closeable) {
        resourceStack.pushResource(CloseableResourceWrapper.of(closeable));
        return closeable;
    }

    protected <A> void defer(@NonNull Consumer<A> f, A x) {
        defer(() -> f.accept(x));
    }

    protected void defer(@NonNull Runnable runnable) {
        resourceStack.pushResource(new Destructor() {
            protected void destroy(Destructor resource) {
                runnable.run();
            }
        });
    }

    @Override
    public final void onEnable() {
        super.onEnable();
        this.init();
    }

    @Override
    public final void onDisable() {
        super.onDisable();

        try {
            this.destroy();
        } catch(Exception e) {
            e.printStackTrace();
        }

        resourceStack.close();
    }

    public abstract void init();

    public abstract void destroy();
}