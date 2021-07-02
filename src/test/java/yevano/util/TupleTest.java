package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TupleTest {
    @Test
    public void match() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 4;
        Integer i5 = 5;
        Integer i6 = 6;
        Integer i7 = 7;
        Integer i8 = 8;

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2)
            .match((o1, o2) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2)
            .match((o1, o2) -> o2));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3)
            .match((o1, o2, o3) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3)
            .match((o1, o2, o3) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3)
            .match((o1, o2, o3) -> o3));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4)
            .match((o1, o2, o3, o4) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4)
            .match((o1, o2, o3, o4) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4)
            .match((o1, o2, o3, o4) -> o3));
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4)
            .match((o1, o2, o3, o4) -> o4));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5)
            .match((o1, o2, o3, o4, o5) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5)
            .match((o1, o2, o3, o4, o5) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5)
            .match((o1, o2, o3, o4, o5) -> o3));
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5)
            .match((o1, o2, o3, o4, o5) -> o4));
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5)
            .match((o1, o2, o3, o4, o5) -> o5));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o3));
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o4));
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o5));
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)
            .match((o1, o2, o3, o4, o5, o6) -> o6));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o3));
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o4));
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o5));
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o6));
        assertEquals((Integer) i7, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)
            .match((o1, o2, o3, o4, o5, o6, o7) -> o7));

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o1));
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o2));
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o3));
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o4));
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o5));
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o6));
        assertEquals((Integer) i7, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o7));
        assertEquals((Integer) i8, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)
            .match((o1, o2, o3, o4, o5, o6, o7, o8) -> o8));
    }

    @Test
    public void componentTest() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 4;
        Integer i5 = 5;
        Integer i6 = 6;
        Integer i7 = 7;
        Integer i8 = 8;

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2)._2());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3)._3());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4)._3());
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4)._4());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5)._3());
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5)._4());
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5)._5());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._3());
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._4());
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._5());
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6)._6());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._3());
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._4());
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._5());
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._6());
        assertEquals((Integer) i7, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7)._7());

        assertEquals((Integer) i1, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._1());
        assertEquals((Integer) i2, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._2());
        assertEquals((Integer) i3, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._3());
        assertEquals((Integer) i4, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._4());
        assertEquals((Integer) i5, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._5());
        assertEquals((Integer) i6, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._6());
        assertEquals((Integer) i7, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._7());
        assertEquals((Integer) i8, (Integer) Tuple.of(i1, i2, i3, i4, i5, i6, i7, i8)._8());
    }
}
