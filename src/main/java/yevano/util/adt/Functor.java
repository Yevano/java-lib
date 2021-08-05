package yevano.util.adt;

import yevano.util.Fun;

public interface Functor<F> {
    <A, B> Higher<F, B> map(Fun<A, B> fn, Higher<F, A> ds);
}
