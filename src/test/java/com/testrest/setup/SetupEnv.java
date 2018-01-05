package com.testrest.setup;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetupEnv {

    @BeforeClass
    public static void setup() {
        try {
            Properties prop = new Properties();
            File testEnvFile = new File("src/test/resources/testenv.properties");
            prop.load(new FileInputStream(testEnvFile));
            RestAssured.baseURI = prop.getProperty("basewsurl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
