package service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonService {

    public RequestSpecification REQUEST_SPECIFICATION;
    public static final String JSON_API = "json_api";

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(SpellerProperties.getProperties().getProperty(JSON_API))
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();
    }

    public Response getWithParams(String uri, Map<String, Object> params) {
        RequestSpecification requestSpecification = given(REQUEST_SPECIFICATION);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            requestSpecification.param(param.getKey(), param.getValue());
        }
        return requestSpecification.post(uri);
    }
}
