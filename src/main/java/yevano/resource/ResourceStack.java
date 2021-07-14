package yevano.resource;

import java.util.function.Consumer;

import lombok.NonNull;

public class ResourceStack extends ResourceOwner {
    @Override
    public Resource pushResource(@NonNull Resource resource) {
        return super.pushResource(resource);
    }

    @Override
    protected <A> void defer(@NonNull Consumer<A> f, A x) {
        super.defer(f, x);
    }
}
