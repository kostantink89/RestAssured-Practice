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

public class PutRequest {

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
    public void validatePutRequest() {
        int playerdId = 190;
        String payload = "{\n" +
                "    \"id\": 190,\n" +
                "    \"name\": \"Roberto\",\n" +
                "    \"lastName\": \"Martinos\",\n" +
                "    \"number\": 33,\n" +
                "    \"team\": \"FC Brasio\"\n" +
                "}";
        given().
                body(payload).
                pathParam("playerdId",playerdId).
        when().
                put("/players/{playerdId}").
        then().
                log().all().
                body("name",equalTo("Roberto"),"id",equalTo(playerdId));

    }

    @Test
    public void anotherPutRequest() {
        int idNumber = 150;
        String payload = " {\n" +
                "        \"id\": 150,\n" +
                "        \"name\": \"Christopher\",\n" +
                "        \"lastName\": \"Valli\",\n" +
                "        \"number\": 11,\n" +
                "        \"team\": \"Superhitters\"\n" +
                "    }";
        given().
                body(payload).
                pathParam("idNumber",idNumber).
        when().
                put("/players/{idNumber}").
        then().
                log().all().
        body("team",equalTo("Superhitters")).
        body("number",equalTo(11));
    }
}
