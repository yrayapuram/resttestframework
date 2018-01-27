package com.testrest.smoke;

import com.testrest.setup.SetupEnv;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItems;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SmokeTestSuite extends SetupEnv {
    @Test
    public void testMockServer() {
        SetupEnv.setBaseURI("baseurl");
        given()
                .when().get("/test")
                .then().statusCode(200);
    }

    @Test
    public void testForDriverSignup() {
        SetupEnv.setBaseURI("baseurl");
        given()
                .when().body("{\n" +
                "  \"user\": {\n" +
                "    \"cc\": \"viswa\",\n" +
                "    \"last_name\": \"prasad\",\n" +
                "    \"mobile\": 9611909045,\n" +
                "    \"driving_licence\": \"ADKPV19287\",\n" +
                "    \"password\": \"viswa123\",\n" +
                "    \"vin\": \"KA04MD484\",\n" +
                "    \"email\": \"viswa.prasad@cognitiveclouds.com\"\n" +
                "  }\n" +
                "}").post("/users/driver_signup")
                .then().statusCode(200);
    }

    @Test
    @Category(com.testrest.smoke.SmokeTestSuite.class)
    public void testForStateName() {
        given()
                .when().get("/state/get/USA/all")
                .then().contentType(ContentType.JSON).body("RestResponse.result.name", hasItems("Californiaaa"));
    }

    @Test
    @Category(com.testrest.smoke.SmokeTestSuite.class)
    public void testForLargerCity() {
        given()
                .when().get("/state/get/USA/all")
                .then().contentType(ContentType.JSON).body("RestResponse.result.largest_city", hasItems("Los Angeles"));

    }
}