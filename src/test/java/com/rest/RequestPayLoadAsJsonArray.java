package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static data.TestData.LOCAL_HOST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestPayLoadAsJsonArray {



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
    public void newPostRequestWithArray() {
        HashMap<String,Object> thirdTeam = new HashMap<String,Object>();
        thirdTeam.put("id",240);
        thirdTeam.put("name","Andrea");
        thirdTeam.put("lastName","Wong");
        thirdTeam.put("number",19);
        thirdTeam.put("team","Rappers");

        given().
                body(thirdTeam).
                when().
         post("/players").
                then().
          log().all().
                assertThat().
                body("name",equalTo("Andrea")).
                body("team",equalTo("Rappers"));

    }
}
