package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected final void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    /**
     * Проверяет, соответствует ли переданный объект всем критериям валидации.
     *
     * @param obj объект для проверки валидации
     * @return {@code true} если объект проходит все проверки,
     * {@code false} если хотя бы одна проверка не пройдена
     */

    public boolean isValid(T obj) {
        for (Predicate<T> value : this.validations.values()) {
            if (!value.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
