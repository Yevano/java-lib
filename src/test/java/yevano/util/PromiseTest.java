package yevano.util;

import org.junit.jupiter.api.Test;

public class PromiseTest {
    Promise<Unit> sleep(long millis) {
        return Promises.newPromise((resolve) -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resolve.accept(Unit.unit);
        });
    }

    @Test
    void promiseTest() {
        sleep(1000).map(Functions.k(x -> System.out.println("Done sleeping!")));
    }
}
