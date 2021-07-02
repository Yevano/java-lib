package yevano.util;

public class MutRef<A> extends Ref<A> {
    private static final MutRef<Object> EMPTY = MutRef.of(null);

    public static <A> MutRef<A> empty() {
        @SuppressWarnings("unchecked")
        MutRef<A> result = (MutRef<A>) EMPTY;
        return result;
    }

    public static <A> MutRef<A> of(A value) {
        return new MutRef<>(value);
    }

    MutRef(A value) {
        super(value);
    }

    public void set(A value) {
        this.value = value;
    }

    public Ref<A> immutable() {
        return Ref.of(value);
    }
}
