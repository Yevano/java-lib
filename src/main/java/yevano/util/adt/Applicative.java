package yevano.util.adt;

import yevano.util.Fun;
import yevano.util.Functions.Fun2;

public interface Applicative<F> extends Functor<F> {
    <A> Higher<F, A> pure(A x);
    <A, B> Higher<F, B> apply(Higher<F, Fun<A, B>> fab, Higher<F, A> fa);
    <A, B, C> Fun2<Higher<F, A>, Higher<F, B>, Higher<F, C>> liftA2(Fun2<A, B, C> f);

    default <A, B> Higher<F, B> map(Fun<A, B> f, Higher<F, A> fa) {
        return apply(pure(f), fa);
    }
}
