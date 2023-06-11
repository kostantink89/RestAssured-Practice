package com.rest;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static data.TestData.LOCAL_HOST;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationPracticeOne {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void before() {
        requestSpecification = with().
                baseUri(LOCAL_HOST);
    }

    @Test
    public void validateStatusCode() {

        given().spec(requestSpecification).
        when().
                get("/players").
        then().
                assertThat().
                statusCode(200).log().all();
    }

    @Test
    public void validateResponseBody() {

        given().spec(requestSpecification).
        when().
                get("/players").
        then().
            log().all().
            assertThat().
            statusCode(200).
            body("id[0]",equalTo(125));


    }
}
