package yevano.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import lombok.val;
import yevano.util.Pair;

public class MapBuilder<K, V> {
    public static <K, V> MapBuilder<K, V> create() {
        return new MapBuilder<>(new HashMap<>(), new ArrayList<>());
    }

    public static <K, V> MapBuilder<K, V> create(Supplier<Map<K, V>> mapInitializer) {
        return new MapBuilder<>(mapInitializer.get(), new ArrayList<>());
    }

    public static <K, V> MapBuilder<K, V> create(
        Supplier<Map<K, V>> mapInitializer, Supplier<List<Pair<K, V>>> listInitializer
    ) {
        return new MapBuilder<>(mapInitializer.get(), listInitializer.get());
    }

    private final Map<K, V> map;
    private final List<Pair<K, V>> entries;

    MapBuilder(Map<K, V> map, List<Pair<K, V>> entries) {
        this.map = map;
        this.entries = new ArrayList<>(entries);
    }

    public MapBuilder<K, V> with(K key, V value) {
        val entriesCopy = new ArrayList<>(entries);
        entriesCopy.add(Pair.of(key, value));
        return new MapBuilder<>(map, entriesCopy);
    }

    public Map<K, V> construct() {
        for(val e : entries) {
            e.run(map::put);
        }

        return map;
    }
}
