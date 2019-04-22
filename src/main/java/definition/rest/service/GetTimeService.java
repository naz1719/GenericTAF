package definition.rest.service;

import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import definition.rest.BaseService;
import definition.rest.dto.CurrentDateTimeResponse;
import execution.logger.AllureLogger;
import execution.logger.LogHolder;
import features.env.EndpointPropNames;
import io.qameta.allure.Step;

import static com.jayway.restassured.RestAssured.given;


public class GetTimeService extends BaseService {
    private static GetTimeService instance;

    private GetTimeService() {
    }

    public static GetTimeService getInstance() {
        if (instance == null) {
            instance = new GetTimeService();
        }
        return instance;
    }

    @Step(value = "REST: Get current time")
    public CurrentDateTimeResponse getCurrentTime() {
        LogHolder logHolder = new LogHolder();
        Response response;
        CurrentDateTimeResponse timeResponse;

        try {
            String uri = EndpointPropNames.currentDateTime.getEndPoint();
            RequestSpecification request = given().relaxedHTTPSValidation().filters(new ResponseLoggingFilter(LogDetail.ALL, logHolder.getResponseVar()),
                    new RequestLoggingFilter(LogDetail.ALL, logHolder.getRequestVar())).
                    contentType(content_type_json);
//                    header("X-CI-Country", country);

//            request
//                    .pathParam("token", token)
//                    .pathParam("subscriptionId", subscriptionId);
            response = request.when().get(uri);

            AllureLogger.attachJsone("Request :", logHolder.getRequestStream());
            AllureLogger.attachJsone("Response :", logHolder.getResponseStream());

            validateResponseBasic(response);
            validateResponseStatusCode(response, 200);

            timeResponse = response.then().extract().as(CurrentDateTimeResponse.class, ObjectMapperType.JACKSON_2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return timeResponse;
    }


}