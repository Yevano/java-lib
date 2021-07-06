package yevano.resource;

import java.util.function.Consumer;

import lombok.NonNull;

public class ResourceStack extends ResourceOwner {
    @Override
    public void pushResource(@NonNull Resource resource) {
        super.pushResource(resource);
    }

    @Override
    protected <A> void defer(@NonNull Consumer<A> f, A x) {
        super.defer(f, x);
    }

    

    @Override void destroy(ResourceOwner resourceOwner) { }
}
