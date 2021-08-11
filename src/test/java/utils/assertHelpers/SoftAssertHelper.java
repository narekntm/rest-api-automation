package utils.assertHelpers;

import org.testng.asserts.SoftAssert;

public class SoftAssertHelper {
    SoftAssert instance;

    public SoftAssertHelper() {
        instance = new SoftAssert();
    }

    public void valueEquals(String actualValue, String expectedValue, String message) {
        instance.assertEquals(actualValue, expectedValue, message);
    }

    public void valueEquals(int actualValue, int expectedValue, String message) {
        instance.assertEquals(actualValue, expectedValue, message);
    }

    public void textContains(String actualValue, String expectedValue, String message) {
        instance.assertTrue(actualValue.contains(expectedValue), message);
    }

    public void assertAll() {
        instance.assertAll();
    }
}
