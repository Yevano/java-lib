package yevano.resource;

import java.util.ArrayList;
import java.util.Map;

import lombok.NonNull;
import lombok.val;

public class MapResource<T extends Resource> extends Destructor {
    public static <T extends Resource> MapResource<T> of(@NonNull Map<?, T> map) {
        return new MapResource<>(map);
    }

    private final Map<?, T> map;

    MapResource(Map<?, T> map) {
        this.map = map;
    }

    @Override
    final void destroy(Destructor $) {
        for(val e : new ArrayList<>(map.values())) {
            e.close();
        }
    }
}
