package yevano.resource;

import lombok.NonNull;
import lombok.val;

public class IterableResource<T extends Resource> extends Destructor {
    public static <T extends Resource> IterableResource<T> of(@NonNull Iterable<T> collection) {
        return new IterableResource<>(collection);
    }

    private final Iterable<T> iterable;

    IterableResource(Iterable<T> collection) {
        this.iterable = collection;
    }

    @Override
    protected final void destroy(Destructor $) {
        for(val e : iterable) {
            e.close();
        }
    }
}
