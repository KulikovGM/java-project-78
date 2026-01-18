package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    // Пока не вызван метод required(), null и пустая строка считаются валидным

    public StringSchema required() {
        addValidation("required", obj -> ((obj != null) && !(obj.isEmpty())));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidation("minLength", obj -> obj.length() >= minLength);
        return this;
    }

    public StringSchema contains(String str) {
        addValidation("contains", obj -> obj.contains(str));
        return this;
    }
}