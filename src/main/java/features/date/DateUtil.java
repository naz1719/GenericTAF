package features.date;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static Date parseDate(String time, String pattern) throws Exception {
        SimpleDateFormat spf = new SimpleDateFormat(pattern);
        return spf.parse(time);
    }


    public static String fromatDateByPatternGetAsString(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }


    @Step("Validate date [{actual}] and [{expected}]")
    public static void validateDate(String actual, String actualPattern, String expected, String expectedPattern, SoftAssert sa, String message) {
        if (actual != null && expected != null) {
            Date expectedDate = null;
            Date actualDate = null;
            try {
                expectedDate = DateUtil.parseDate(actual, actualPattern);
                actualDate = DateUtil.parseDate(expected, expectedPattern);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            sa.assertEquals(actualDate, expectedDate, message);
        }
    }

    public static class TimePattern {
        public static final String YYYY_MM_DD_BIRTHDAY_FORMAT = "yyyy-MM-dd";
        public static final String YYYY_MM_DD_T_HH_MM_SS_SSSX = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        public static final String YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    }
}



