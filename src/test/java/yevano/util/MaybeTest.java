package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MaybeTest {
    @Test
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void matchTest(Object value) {
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
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void optionalConvert(Object value) {
        assertEquals(Maybe.fromOptional(Optional.empty()), Maybe.none());
        assertEquals(Maybe.fromOptional(Optional.of(value)), Maybe.some(value));
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void isSomeAndIsNone(Object value) {
        assertTrue(Maybe.some(value).isSome());
        assertFalse(Maybe.some(value).isNone());
        assertFalse(Maybe.none().isSome());
        assertTrue(Maybe.none().isNone());
    }
}
