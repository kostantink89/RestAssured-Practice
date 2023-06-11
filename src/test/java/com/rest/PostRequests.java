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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostRequests {


    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri(LOCAL_HOST).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePostRequestBDDStyle() {
        String myPostRequest = "{\n" +
                "        \"id\": 165,\n" +
                "        \"name\": \"Luizio\",\n" +
                "        \"lastName\": \"Contri\",\n" +
                "        \"number\": 45,\n" +
                "        \"team\": \"FC Brahamas\"\n" +
                "    }";

        given().
                body(myPostRequest).
        when().
           post("/players").

        then().
           log().all().
           assertThat().
           body("name",equalTo("Luizio"));

    }
}
