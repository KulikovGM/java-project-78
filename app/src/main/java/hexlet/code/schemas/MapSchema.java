package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int maxSize) {
        addValidation("maxSize", obj -> obj == null || obj.size() == maxSize);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation("shape",
                map -> {
                    return schemas.entrySet().stream().allMatch(entry -> {
                        if (!map.containsKey(entry.getKey()) || map.get(entry.getKey()) == null) {
                            return false;
                        }
                        return entry.getValue().isValid((T) map.get(entry.getKey()));
                    });
                });
        return this;
    }

}
