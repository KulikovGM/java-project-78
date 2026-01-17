package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public boolean isValid(T obj) {
        for (Predicate<T> value : this.validations.values()) {
            if (!value.test(obj)) {
                return false;
            }
        }
        return true;
    } // переписать в виде лямбды validations.values().stream().allMatch(...)

}
