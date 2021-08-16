package yevano.bukkit;

import org.bukkit.Bukkit;

import yevano.util.Format;

public class LogUtil {
    public interface Context {
        default void log(String string) {
            LogUtil.log(string);
        }

        default void logf(String format, Object... args) {
            LogUtil.logf(format, args);
        }
    }

    public static void log(String string) {
        Bukkit.getLogger().info(string);
    }

    public static void logf(String format, Object... args) {
        log(Format.stringf(format, args));
    }
}
