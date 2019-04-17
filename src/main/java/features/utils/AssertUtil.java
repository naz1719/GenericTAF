package features.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;

public class AssertUtil {
    final static Logger logger = LoggerFactory.getLogger(AssertUtil.class);


    public static void assertEquals(String paramName, Object actual, Object expected) {
        Assert.assertEquals(actual, expected, "ParameterName: [" + paramName + "]");
    }

    public static void equalsIgnoreCase(String paramName, String actual, String expected) {
        if (!actual.equalsIgnoreCase(expected)) {
            Assert.fail("ParameterName: [" + paramName + "] ActualValue : [" + actual + "] does not equal with Expected : [" + expected + "]");
        }
    }

    public static void contains(String paramName, String actual, String expected) {
        if (!actual.contains(expected)) {
            Assert.fail("ParameterName: [" + paramName + "] ActualValue : [" + actual + "] does not equal with Expected : [" + expected + "]");
        }
    }


    public static void assertEquals(String paramName, Object actual, Object expected, List<Throwable> errorList) {
        try {
            Assert.assertEquals(actual, expected, "ParameterName: [" + paramName + "]");
        } catch (Throwable e) {
            errorList.add(e);
        }
    }

    public static void assertEqualsIgnoreCase(String paramName, String actual, String expected, List<Throwable> errorList) {
        try {
            Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), "ParameterName: [" + paramName + "]");
        } catch (Throwable e) {
            errorList.add(e);
        }
    }


    public static void assertIsNotEmpty(String paramName, Object actual, List<Throwable> errorList) {

        try {
            Assert.assertNotNull(actual, "ParameterName: [" + paramName + "]");
            Assert.assertNotEquals(actual.toString(), "", "ParameterName: [" + paramName + "]");

        } catch (Throwable e) {
            errorList.add(e);
        }
    }

    public static void assertIsEmpty(String paramName, Object actual, List<Throwable> errorList) {
        try {
            Assert.assertNotNull(actual, "ParameterName: [" + paramName + "]");
            Assert.assertEquals(actual, "", "ParameterName: [" + paramName + "]");

        } catch (Throwable e) {
            errorList.add(e);
        }
    }


    public static void assertIsNull(String paramName, Object actual, List<Throwable> errorList) {
        try {
            Assert.assertNull(actual, "ParameterName: [" + paramName + "]");


        } catch (Throwable e) {
            errorList.add(e);
        }
    }


    public static void equalsIgnoreCase(String paramName, String actual, String expected, List<Throwable> errorList) {
        try {
            if (!actual.equalsIgnoreCase(expected)) {
                Assert.fail("ParameterName: [" + paramName + "] ActualValue : [" + actual + "] does not equal with Expected : [" + expected + "]");
            }
        } catch (Throwable e) {
            errorList.add(e);
        }
    }

    public static void contains(String paramName, String actual, String expected, List<Throwable> errorList) {
        try {
            if (!actual.contains(expected)) {
                Assert.fail("ParameterName: [" + paramName + "] ActualValue : [" + actual + "] does not equal with Expected : [" + expected + "]");
            }
        } catch (Throwable e) {
            errorList.add(e);
        }
    }


    public static void equalsIgnoreCaseSkipIfExpectedNull(String paramName, String actualValue, String expectedValue, List<Throwable> errorList) {
        try {
            if (expectedValue != null) {
                if (actualValue == null) {
                    Assert.fail("Validation : [" + paramName + "] Failed because actual parameter is null, Expected is [" + expectedValue + "]");
                } else {
                    Assert.assertEquals(actualValue, expectedValue, "ParameterName: [" + paramName + "]");
                }
            } else {
                logger.info("Validation has been skipped as expected parameter not available [" + paramName + "]");
            }

        } catch (Throwable e) {
            errorList.add(e);
        }


    }


    public static void assertEqualsSkipIfExpectedNull(String paramName, Object actual, Object expected, List<Throwable> errorList) {
        try {
            if (expected != null) {
                if (actual == null) {
                    Assert.fail("Validation : [" + paramName + "] Failed because actual parameter is null, Expected is [" + expected + "]");
                } else {
                    Assert.assertEquals(actual, expected, "ParameterName: [" + paramName + "]");
                }
            } else {
                logger.info("Validation has been skipped as expected parameter not available [" + paramName + "]");
            }

        } catch (Throwable e) {
            errorList.add(e);
        }

    }

    public static String replaceNull(String string) {
        return Optional.ofNullable(string).isPresent() ? string : "";
    }
}
