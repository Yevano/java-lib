package yevano.util;

import java.util.function.Consumer;

public interface Promise<A> {
    <B> Promise<B> map(Fun<A, B> f);
    Promise<Unit> map(Consumer<A> f);
    <B> Promise<B> flatMap(Fun<A, Promise<B>> f);
}
