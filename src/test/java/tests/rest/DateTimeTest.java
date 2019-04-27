package tests.rest;

import definition.rest.dto.CurrentDateTimeResponse;
import definition.rest.service.GetTimeService;
import execution.BaseAPITest;
import features.SuiteParam;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DateTimeTest extends BaseAPITest {

    @Test
    public void checkCurrentDateTimeTest(ITestContext context) {
        String country = context.getSuite().getParameter(SuiteParam.country.name());

        System.out.println("Supe Log");
//        CurrentDateTimeResponse currentDateTimeResponse = GetTimeService.getInstance().getCurrentTime();
//        AllureLogger.logInfo("Current time : [" + currentDateTimeResponse + "]");
    }
}
