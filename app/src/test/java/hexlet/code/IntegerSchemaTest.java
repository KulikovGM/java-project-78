package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import hexlet.code.Validator;

class IntegerSchemaTest {

    @Test
    void testValidInteger() {
        var v = new Validator();
        var schema = v.number();
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testPositive() {
        var v = new Validator();
        var schema = v.number();
        schema.positive();
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
    }

    @Test
    void testRange() {
        var v = new Validator();
        var schema = v.number();
        schema.range(2, 6);
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(4));
        assertTrue(schema.isValid(6));
        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(10));
    }

    @Test
    void testAllMethods() {
        var v = new Validator();
        var schema = v.number();
        schema.required().positive().range(2, 6);
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(6));
        assertFalse(schema.isValid(10));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(null));
    }
}
