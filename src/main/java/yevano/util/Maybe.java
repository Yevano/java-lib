package yevano.util;

import java.util.Optional;
import java.util.function.Consumer;

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

    public static <A> Maybe<A> someNullable(A value) {
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

    public Maybe<A> ifSome(@NonNull Consumer<A> f) {
        if(value != null) f.accept(value);
        return this;
    }

    public Maybe<A> ifNone(@NonNull Runnable f) {
        if(value == null) f.run();
        return this;
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
}
