package yevano.util;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class Promises {
    protected static class PromiseImpl<A> implements Promise<A> {
        private Queue<Consumer<A>> handlerQueue = new ArrayDeque<>();
        private Maybe<A> resolvedValue = Maybe.none();

        public PromiseImpl(Consumer<Consumer<A>> setup) {
            setup.accept(value -> {
                resolvedValue = resolvedValue.defaultMap(() -> value);
            });
        }

        @Override
        public <B> Promise<B> map(Fun<A, B> f) {
            return Promises.newPromise(resolve -> {
                if(resolvedValue.map(f).ifSome(resolve).isSome()) {
                    return;
                }
                handlerQueue.add(value -> resolve.accept(f.apply(value)));
            });
        }

        @Override
        public <B> Promise<B> flatMap(Fun<A, Promise<B>> f) {
            return Promises.newPromise(resolve -> {
                handlerQueue.add(value -> f.apply(value).map(resolve));
            });
        }

        @Override
        public Promise<Unit> map(Consumer<A> f) {
            return this.map(Functions.k(f));
        }
    }
    
    public static <A> Promise<A> newPromise(Consumer<Consumer<A>> f) {
        return new PromiseImpl<>(f);
    }
}
