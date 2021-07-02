package yevano.resource;

import lombok.NonNull;

public class ResourceCollector extends Resource {
    public static ResourceCollector of(@NonNull Runnable destructor, Resource... resources) {
        return new ResourceCollector(destructor, resources);
    }

    private Runnable destructor;

    public ResourceCollector(@NonNull Runnable destructor, Resource... resources) {
        super(resources);
        this.destructor = destructor;
    }

    @Override
    public void destroy() {
        this.destructor.run();
    }
}
