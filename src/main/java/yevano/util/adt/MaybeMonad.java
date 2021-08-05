package yevano.util.adt;

import yevano.util.Fun;
import yevano.util.Maybe;
import yevano.util.Maybe.K;

public class MaybeMonad implements Monad<Maybe.K> {
    @Override
    public <A> Higher<Maybe.K, A> pure(A x) {
        return Maybe.some(x);
    }

    @Override
    public <A, B> Higher<Maybe.K, B> flatMap(Fun<A, Higher<K, B>> f, Higher<K, A> xs) {
        return Maybe.narrowK(xs).flatMap(x -> Maybe.narrowK(f.apply(x)));
    }
}
