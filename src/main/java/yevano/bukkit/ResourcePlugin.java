package yevano.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import yevano.resource.Resource;
import yevano.resource.ResourceStack;

public abstract class ResourcePlugin extends JavaPlugin {
    private final ResourceStack resourceStack = new ResourceStack();

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

    public abstract Resource init();

    public abstract void destroy();
}