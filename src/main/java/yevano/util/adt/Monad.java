package yevano.util.adt;

import yevano.util.Fun;
import yevano.util.Functions.Fun2;

public interface Monad<M> extends Applicative<M> {
    <A, B> Higher<M, B> flatMap(Fun<A, Higher<M, B>> f, Higher<M, A> ma);

    default <A, B> Higher<M, B> map(Fun<A, B> f, Higher<M, A> ma) {
        return flatMap(x -> pure(f.apply(x)), ma);
    }

    default <A, B, C> Fun2<Higher<M, A>, Higher<M, B>, Higher<M, C>> liftA2(Fun2<A, B, C> f) {
        return (ma, mb) -> flatMap(a -> map(f.apply(a), mb), ma);
    }

    default <A, B> Higher<M, B> apply(Higher<M, Fun<A, B>> mab, Higher<M, A> ma) {
        return flatMap(f -> map(f, ma), mab);
    }
}
