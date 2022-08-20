package yevano.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.NonNull;

public class Maybe<A> {
    private static final Maybe<?> NONE = new Maybe<>(null);

    @SuppressWarnings("unchecked")
    public static <A> Maybe<A> none() {
        return (Maybe<A>) NONE;
    };

    public static <A> Maybe<A> some(@NonNull A value) {
        return new Maybe<>(value);
    }

    public static <A> Maybe<A> someUnlessNull(A value) {
        return new Maybe<>(value);
    }

    public static <A> Maybe<A> fromOptional(@NonNull Optional<A> optional) {
        if(optional.isPresent()) return new Maybe<>(optional.get());
        return none();
    }

    protected A value;

    Maybe(A value) {
        this.value = value;
    }

    public <B> Maybe<B> map(@NonNull Fun<A, B> f) {
        if(value == null) return none();
        return Maybe.some(f.apply(value));
    }

    public <B> Maybe<B> flatMap(@NonNull Fun<A, Maybe<B>> f) {
        if(value == null) return none();
        return f.apply(value);
    }

    public Maybe<A> filter(@NonNull Fun<A, Boolean> f) {
        if(value != null && f.apply(value)) return this;
        return none();
    }

    public Maybe<A> ifSome(@NonNull Consumer<A> f) {
        if(value != null) f.accept(value);
        return this;
    }

    public Maybe<A> ifNone(@NonNull Runnable f) {
        if(value == null) f.run();
        return this;
    }

    public A defaultTo(@NonNull Supplier<A> f) {
        return value != null ? value : f.get();
    }

    public Maybe<A> defaultMap(@NonNull Supplier<A> f) {
        return value != null ? this : some(f.get());
    }

    public A getOrThrow(Supplier<? extends RuntimeException> supplier) {
        if(value == null) throw supplier.get();
        return value;
    }

    public A getOrDo(Runnable runnable) {
        if(value == null) runnable.run();
        return value;
    }

    public boolean isSome() {
        return value != null;
    }

    public boolean isNone() {
        return value == null;
    }

    public Optional<A> toOptional() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(!Maybe.class.isInstance(obj)) return false;
        Maybe<?> m = (Maybe<?>) obj;
        if(value == null) return m.value == null;
        return value.equals(m.value);
    }

    @Override
    public String toString() {
        return value == null ? "None" : Format.stringf("Some(%s)", value);
    }
}
