package yevano.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

public class ConcurrentMoveHashMapTest {
    void populate(Map<String, String> map, int length, String prefix) {
        for (int i = 0; i < length; i++) {
            map.put(prefix + "-key " + Integer.toString(i), prefix + "-value " + Integer.toString(i));
        }
    }

    void verify(Map<String, String> map, int length, String prefix) {
        for (int i = 0; i < length; i++) {
            assertTrue(map.containsKey(prefix + "-key " + Integer.toString(i)));
            assertEquals(prefix + "-value " + Integer.toString(i), map.get(prefix + "-key " + Integer.toString(i)));
        }
    }

    void populate(Set<String> set, int length, String prefix) {
        for (int i = 0; i < length; i++) {
            set.add(prefix + "-key " + Integer.toString(i));
        }
    }

    void verify(Set<String> set, int length, String prefix) {
        for (int i = 0; i < length; i++) {
            assertTrue(set.contains(prefix + "-key " + Integer.toString(i)));
        }
    }

    @Test
    public void fillHashMap() {
        final int LENGTH = 1000000;
        var map = new HashMap<>();
        for (int i = 0; i < LENGTH; i++) {
            map.put("key-" + i, "value-" + i);
        }
        assertEquals(LENGTH, map.size());
    }

    @Test
    public void fillConcurrentHashMap() {
        final int LENGTH = 1000000;
        var map = new ConcurrentHashMap<>();
        for (int i = 0; i < LENGTH; i++) {
            map.put("key-" + i, "value-" + i);
        }
        assertEquals(LENGTH, map.size());
    }

    @Test
    public void fillConcurrentMoveHashMap() {
        final int LENGTH = 1000000;
        var map = new ConcurrentMoveHashMap<>();
        for (int i = 0; i < LENGTH; i++) {
            map.put("key-" + i, "value-" + i);
        }
        assertEquals(LENGTH, map.size());
    }

    @Test
    public void move() {
        int LENGTH = 1000000;
        var map = new ConcurrentMoveHashMap<String, String>();
        populate(map, LENGTH, "");
        var newMap = new HashMap<String, String>();
        map.move(newMap);

        assertEquals(0, map.size());
        assertEquals(LENGTH, newMap.size());

        verify(newMap, LENGTH, "");
    }

    @Test
    public void asyncMove() {
        final int LENGTH = 1000000;
        var map = new ConcurrentMoveHashMap<String, String>();
        var newMap = new HashMap<String, String>();
        populate(map, LENGTH, "original");

        var nonMoveThread = Executors.defaultThreadFactory().newThread(() -> {
            while(!map.isMoveLocked());
            populate(map, LENGTH, "async");
        });

        var moveThread = Executors.defaultThreadFactory().newThread(() -> {
            map.move(newMap);
        });

        moveThread.start();
        nonMoveThread.start();

        try {
            moveThread.join();
            nonMoveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(map, LENGTH, "async");
        verify(newMap, LENGTH, "original");
    }

    @Test
    public void moveSets() {
        final int LENGTH = 1000000;
        var set = ConcurrentMoveHashMap.<String>newKeySet();
        populate(set, LENGTH, "");
        var newSet = new HashSet<String>();
        set.move(newSet);

        assertEquals(0, set.size());
        assertEquals(LENGTH, newSet.size());

        verify(newSet, LENGTH, "");
    }

    @Test
    public void asyncMoveSets() {
        final int LENGTH = 1000000;
        var set = ConcurrentMoveHashMap.<String>newKeySet();
        var newSet = new HashSet<String>();
        populate(set, LENGTH, "original");

        var nonMoveThread = Executors.defaultThreadFactory().newThread(() -> {
            while(!set.isMoveLocked());
            populate(set, LENGTH, "async");
        });

        var moveThread = Executors.defaultThreadFactory().newThread(() -> {
            set.move(newSet);
        });

        moveThread.start();
        nonMoveThread.start();

        try {
            moveThread.join();
            nonMoveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(set, LENGTH, "async");
        verify(newSet, LENGTH, "original");
    }
}
