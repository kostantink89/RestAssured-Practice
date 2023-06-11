package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static data.TestData.LOCAL_HOST;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestParameters {

    @Test
    public void single_query_param() {
        given().
                baseUri(LOCAL_HOST).
                queryParam("param","first").
                log().all().
       when().
             get("/players").
       then().
            log().all().
            assertThat().
            statusCode(200);
    }

    @Test
    public void multi_query_parameter() {
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("param","first");
        queryParams.put("paramTwo","second");
        queryParams.put("paramThree","third");

        given().
                baseUri(LOCAL_HOST).
                queryParams(queryParams).
                log().all().
       when().
              get("/players").
       then().
             log().all().
             assertThat().
       statusCode(200);
    }

    @Test
    public void multi_value_parameter() {
        given().
                baseUri(LOCAL_HOST).
                queryParam("paramOne","first,second,third").
                log().all().
        when().
                get("/players").
        then().
               log().all().
               assertThat().
               statusCode(200);
    }


}
