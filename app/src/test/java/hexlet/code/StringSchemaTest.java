package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {
    @Test
    void testValid() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        // Пока не вызван метод required(), null и пустая строка считаются валидным
        schema.required();
        assertTrue(schema.isValid("text"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testContains() {
        var v2 = new Validator();
        var schema2 = v2.string().contains("text");
        assertTrue(schema2.isValid("test text"));
        assertFalse(schema2.isValid("tex"));
    }

    @Test
    void testMinLength() {
        var v3 = new Validator();
        var schema3 = v3.string().minLength(100).minLength(4); //последний метод имеет приоритет (перетирает предыдущий)
        assertTrue(schema3.isValid("text"));
        assertTrue(schema3.isValid("text text"));
        assertFalse(schema3.isValid("min"));
        assertFalse(schema3.isValid(""));
    }

    @Test
    void testAllStringMethods() {
        var v4 = new Validator();
        var schema4 = v4.string();
        schema4.required().minLength(3).contains("tex");
        assertTrue(schema4.isValid("text"));
        assertFalse(schema4.isValid("ext"));
        assertFalse(schema4.isValid("e"));
        assertFalse(schema4.isValid(""));
    }
}
