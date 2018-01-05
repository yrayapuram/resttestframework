package com.testrest.smoke;

import com.testrest.setup.SetupEnv;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItems;

import io.restassured.http.ContentType;
import org.junit.Test;

public class SmokeTestSuite extends SetupEnv {

    @Test
    public void testForStateName() {
        given()
                .when().get("/state/get/USA/all")
                .then().contentType(ContentType.JSON).body("RestResponse.result.name", hasItems("California"));
    }
}
