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


//Схема StringSchema содержит такой набор методов:
//
//required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
//minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа
//contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку