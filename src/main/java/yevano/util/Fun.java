package yevano.util;

import java.util.function.Function;

public interface Fun<A, B> {
    B apply(A x);
    default Function<A, B> javaFun() { return a -> apply(a); }
}
