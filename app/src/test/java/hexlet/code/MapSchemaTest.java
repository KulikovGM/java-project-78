package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    @Test
    void testValid() {
        var v = new Validator();
        var schema = v.map();
        var testMap = new HashMap<String, String>();
        assertTrue(schema.isValid(testMap));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
    }

    @Test
    void testSizeof() {
        var v = new Validator();
        var schema = v.map();
        var testMap = new HashMap<String, String>();
        testMap.put("key1", "str1");
        schema.sizeof(2);
        assertFalse(schema.isValid(testMap));
        testMap.put("key2", "str2");
        assertTrue(schema.isValid(testMap));
        testMap.put("key3", "str3");
        assertFalse(schema.isValid(testMap));
    }

    @Test
    void testAllMapMethods() {
        var v = new Validator();
        var schema = v.map();
        var testMap = new HashMap<String, String>();
        schema.required().sizeof(2);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(testMap));
        testMap.put("key1", "str1");
        schema.sizeof(2);
        assertFalse(schema.isValid(testMap));
        testMap.put("key2", "str2");
        assertTrue(schema.isValid(testMap));
    }

    @Test
    void testShape() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));  //true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false
    }
}
