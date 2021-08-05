package yevano.util.adt;

import java.util.stream.Collectors;

import yevano.util.Fun;
import yevano.util.ListKind;
import yevano.util.ListKind.K;

public class ListFunctor implements Functor<ListKind.K> {
    @Override
    public <T, R> Higher<K, R> map(Fun<T, R> fn, Higher<K, T> ds) {
        return ListKind.lift(
            ListKind.narrowK(ds).stream().map(fn.javaFun()).collect(Collectors.toList())
        );
    }
}
