package yevano.util;

import static yevano.util.Maybe.none;
import static yevano.util.Maybe.some;

import java.util.function.Supplier;

public class LazyRef<A> extends Ref<A> {
    public static final <A> Ref<A> of(Supplier<A> supplier) {
        return new LazyRef<>(supplier);
    }

    protected final Supplier<A> supplier;
    protected Maybe<A> value;

    LazyRef(Supplier<A> supplier) {
        super(null);
        this.supplier = supplier;
        this.value = none();
    }

    @Override
    public A get() {
        return value.defaultTo(() -> {
            A computed = supplier.get();
            this.value = some(computed);
            return computed;
        });
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
