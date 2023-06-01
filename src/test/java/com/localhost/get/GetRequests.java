package com.localhost.get;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static data.TestData.LOCAL_HOST;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


public class GetRequests {

    @Test
    public void getStatusCode() {

        given().
                baseUri(LOCAL_HOST).
        when().
                get("/players").
        then().
        assertThat().
        statusCode(200).log().all();
    }

    @Test
    public void validateResponseBody() {

        given().
                baseUri(LOCAL_HOST).
        when().
               get("/players").
        then().
            log().all().
        assertThat().
        statusCode(200).body("id",hasItems(125,150,175),"name",hasItems("Alexander",
                        "Christopher","Jackson"),"lastName",hasItems("Patos","Valli","Smith"),
                        "number",hasItems(11,8,5),"team",hasItems("FC Astos","Teambats","Superwarriors"),
                "id[0]",equalTo(125),
                "name[0]", is(equalTo("Alexander")),
                "size()",equalTo(3));

    }

    @Test
    public void extractResponse() {
        Response response = given().
               baseUri(LOCAL_HOST).
        when().
            get("/players").
        then().
        assertThat().
        statusCode(200).
        extract().
        response();
        System.out.println("Response = "+response.asString());
    }

    @Test
    public void extractSingleValueFromResponse() {

        String response = given().
                baseUri(LOCAL_HOST).
        when().
          get("/players").
        then().
        assertThat().
        statusCode(200).
        extract().response().asString();

        System.out.println("Response = " + JsonPath.from(response).getString("id[0]"));
    }

    @Test
    public void hamcrestAssertOnExtractedResponse() {

        String lastName = given().
                baseUri(LOCAL_HOST).
                when().
                get("/players").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("lastName[0]");

        System.out.println("LastName of 0 is = " + lastName);

        Assert.assertEquals(lastName, "Patos");

    }
        @Test
        public void validateResponseResponseBodyHamcrest() {

        given().
           baseUri(LOCAL_HOST).
         when().
            get("/players").
         then().
         log().all().assertThat().
          statusCode(200).body("team",containsInAnyOrder("Teambats","FC Astos","Superwarriors"),
                "team", is(not(emptyArray())),
               "team",hasSize(3),
                "team[0]",allOf(startsWith("FC"),containsString("Astos")));
        }

        @Test
        public void requestResponseLogging() {

         given()
                .baseUri(LOCAL_HOST).
                log().all().
         when().
                get("/players").
         then().
               log().all().
               assertThat().statusCode(200);
        }

        @Test
        public void logOnlyIfError() {

        given().
            baseUri(LOCAL_HOST).
            log().all().
        when().
            get("/players").
        then().
                log().ifError().
        assertThat().statusCode(200);
        }

    }


