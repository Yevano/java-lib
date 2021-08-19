package yevano.util;

import static yevano.util.Maybe.fromOptional;
import static yevano.util.Maybe.some;

import java.util.HashMap;
import java.util.Map;

public class ClassMap<CommonClass, ValueClass> {
    private final Map<Class<CommonClass>, ValueClass> map;

    public ClassMap() {
        this.map = new HashMap<>();
    }

    public <KeyType extends CommonClass> Maybe<ValueClass> get(Class<KeyType> key) {
        if(map.containsKey(key)) {
            return some(map.get(key));
        }

        var keyStream = map.keySet().stream();
        var opt = keyStream.filter(classInMap -> classInMap.isAssignableFrom(key)).findFirst();
        return fromOptional(opt.map(map::get));
    }

    @SuppressWarnings("all")
    public <KeyType extends CommonClass> void put(Class<KeyType> key, ValueClass value) {
        this.map.put((Class<CommonClass>) key, value);
    }
}
