package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClassMapTest {
    public interface Super {

    }

    public class Derived implements Super {

    }

    @Test
    public void getFromSuperClass() {
        var classMap = new ClassMap<Object, Integer>();
        classMap.put(Super.class, 3);
        assertEquals(classMap.get(Derived.class), Maybe.some(3));
    }

    @Test
    public void getFromExactClass() {
        var classMap = new ClassMap<Object, Integer>();
        classMap.put(Super.class, 3);
        assertEquals(classMap.get(Super.class), Maybe.some(3));
    }

    @Test
    public void getFromUnpopulatedClass() {
        var classMap = new ClassMap<Object, Integer>();
        classMap.put(Super.class, 3);
        assertEquals(classMap.get(String.class), Maybe.none());
    }
}
