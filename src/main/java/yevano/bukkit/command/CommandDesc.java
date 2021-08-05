package yevano.bukkit.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDesc {
    String name();
    String[] aliases() default { };
}
