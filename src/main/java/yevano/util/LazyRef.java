package yevano.util;

import java.util.Optional;
import java.util.function.Supplier;

public class LazyRef<A> extends Ref<A> {
    public static final <A> Ref<A> of(Supplier<A> supplier) {
        return new LazyRef<>(supplier);
    }

    final Supplier<A> supplier;
    Optional<A> value;

    LazyRef(Supplier<A> supplier) {
        super(null);
        this.supplier = supplier;
    }

    @Override
    public A get() {
        if(!value.isPresent()) {
            A computed = supplier.get();
            this.value = Optional.of(computed);
            return computed;
        }

        return value.get();
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
