package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class InstanceSetTest {
    public class Super {

    }

    public class Derived extends Super {

    }

    @Test
    public void getFromSuper() {
        var instanceSet = new InstanceSet();
        var instance = new Derived();
        instanceSet.add(instance);
        assertEquals(instanceSet.get(Super.class), instance);
    }

    @Test
    public void getFromExactClass() {
        var instanceSet = new InstanceSet();
        var instance = new Super();
        instanceSet.add(instance);
        assertEquals(instanceSet.get(Super.class), instance);
    }

    @Test
    public void getFromUnpopulatedClass() {
        var instanceSet = new InstanceSet();
        instanceSet.add("instance");
        assertThrows(NoSuchElementException.class, () -> {
            instanceSet.get(Super.class);
        });
    }
}
