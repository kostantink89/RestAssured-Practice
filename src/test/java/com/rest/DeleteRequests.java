package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static data.TestData.LOCAL_HOST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequests {

    @BeforeClass
    public void beforeClass() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
        setBaseUri(LOCAL_HOST).
        setContentType(ContentType.JSON).
        log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
        expectStatusCode(200).
        expectContentType(ContentType.JSON).
        log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void deleteRequest() {
        int idNumber = 200;
        given().
                pathParam("idNumber",idNumber).
        when().
                delete("/players/{idNumber}").
        then().
               log().all();


    }

}
