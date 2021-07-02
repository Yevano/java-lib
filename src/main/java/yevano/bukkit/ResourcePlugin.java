package yevano.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import yevano.resource.Resource;
import yevano.resource.ResourceCloseException;
import yevano.resource.ResourceCollector;

public abstract class ResourcePlugin extends JavaPlugin {
    private Resource resource;

    @Override
    public final void onEnable() {
        super.onEnable();
        this.resource = ResourceCollector.of(this::destroy, this.init());
    }

    @Override
    public final void onDisable() {
        super.onDisable();

        try {
            resource.close();
        } catch (ResourceCloseException e) {
            e.printStackTrace();
        }
    }

    public abstract Resource init();

    public abstract void destroy();
}