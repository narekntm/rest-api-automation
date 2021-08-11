package utils.assertHelpers;

import org.testng.Assert;

public class HardAssertHelper {

    public static void valueEquals(String actualValue, String expectedValue, String message) {
        Assert.assertEquals(actualValue, expectedValue, message);
    }

    public static void valueEquals(int actualValue, int expectedValue, String message) {
        Assert.assertEquals(actualValue, expectedValue, message);
    }

    public static void textContains(String actualValue, String expectedValue, String message) {
        Assert.assertTrue(actualValue.contains(expectedValue), message);
    }

    public static void notNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void manualError(String message) {
        Assert.fail(message);
    }
}
