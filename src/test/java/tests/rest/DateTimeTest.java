package tests.rest;

import com.jayway.restassured.response.Response;
import definition.rest.service.GetTimeService;
import execution.BaseAPITest;
import features.SuiteParam;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DateTimeTest extends BaseAPITest {

    @Test
    public void checkCurrentDateTimeTest(ITestContext context) {
        String country = context.getSuite().getParameter(SuiteParam.country.name());

        Response currentDateTimeResponse = GetTimeService.getInstance().getCurrentTime();
//        AllureLogger.logInfo("Current time : [" + currentDateTimeResponse + "]");
    }
}
