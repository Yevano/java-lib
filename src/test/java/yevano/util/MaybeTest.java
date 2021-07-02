package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class MaybeTest {
    @Test
    public void matchTest() {
        int value = 0;
        Maybe.some(value)
            .ifSome(x -> assertEquals(value, x))
            .ifNone(() -> { throw new RuntimeException(); }
        );

        Maybe.none()
            .ifSome(x -> { throw new RuntimeException(); })
            .ifNone(() -> { }
        );
    }

    @Test
    public void optionalConvert() {
        int value = 0;
        assertEquals(Maybe.fromOptional(Optional.empty()), Maybe.none());
        assertEquals(Maybe.fromOptional(Optional.of(value)), Maybe.some(value));
    }

    @Test
    public void isSomeAndIsNone() {
        int value = 0;
        assertTrue(Maybe.some(value).isSome());
        assertFalse(Maybe.some(value).isNone());
        assertFalse(Maybe.none().isSome());
        assertTrue(Maybe.none().isNone());
    }
}
