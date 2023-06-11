package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static data.TestData.LOCAL_HOST;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationPracticeTwo {


    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(LOCAL_HOST);
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public void queryTest() {

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());

    }

    @Test
    public void validateStatusCode() {
        Response response = get("/players").then().log().all().extract().response();
        assertThat(response.statusCode(),equalTo(200));

    }

    @Test
    public void validateResponseBody() {
        Response response = get("/players").then().log().all().extract().response();
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.path("name[1]").toString(),equalTo("Christopher"));
    }


}
