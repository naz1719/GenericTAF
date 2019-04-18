package definition.rest;

import com.jayway.restassured.response.Response;
import io.qameta.allure.Step;
import org.testng.Assert;

public class BaseService {

    protected static String content_type_json = "application/json";
    protected static String content_type_multipart = "multipart/form-data";

    protected void validateResponseBasic(Response response) {
        if (response == null) {
            Assert.fail("FAIL: response is null");
        }
    }

    protected void validateResponseBasic(io.restassured.response.Response response) {
        if (response == null) {
            Assert.fail("FAIL: response is null");
        }
    }

    @Step(value = "Validate that http status code = {expectedStatusCode}")
    protected void validateResponseStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            Assert.fail("FAIL: response StatusCode: " + response.statusCode() + " but expected: " + expectedStatusCode);
        }
    }

    @Step(value = "Validate that http status code = {expectedStatusCode}")
    protected void validateResponseStatusCode(io.restassured.response.Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            Assert.fail("FAIL: response StatusCode: " + response.statusCode() + " but expected: " + expectedStatusCode);
        }
    }
}
