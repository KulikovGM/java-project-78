package hexlet.code.schemas;

import java.util.Objects;

public final class IntegerSchema extends BaseSchema<Integer> {

    public IntegerSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public IntegerSchema positive() {
        addValidation("positive", obj -> obj == null || obj > 0);
        return this;
    }

    public IntegerSchema range(Integer num1, Integer num2) {
        addValidation("range", obj -> obj == null || (obj >= num1 && obj <= num2));
        return this;
    }
}
