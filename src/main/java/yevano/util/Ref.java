package yevano.util;

public class Ref<A> {
    public static <A> Ref<A> of(A value) {
        return new Ref<>(value);
    }

    protected A value;

    Ref(A value) {
        this.value = value;
    }

    public A get() {
        return this.value;
    }

    public <B> Ref<B> map(Fun<A, B> f) {
        return Ref.of(f.apply(value));
    }

    public <B> Ref<B> flatMap(Fun<A, Ref<B>> f) {
        return f.apply(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ref) return ((Ref<?>) obj).value.equals(value);
        return value.equals(obj);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        if(value == null) return 0;
        return value.hashCode();
    }
}
