package com.testrest.setup;

import com.testrest.mockserver.MockServer;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetupEnv {
    public static boolean isMockServerOn = false;
    public static Properties prop;

    @BeforeClass
    public static void setup() {
        try {
            prop = new Properties();
            File testEnvFile = new File("src/test/resources/testenv.properties");
            prop.load(new FileInputStream(testEnvFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("1".equals(System.getProperty("mock"))) {
            MockServer.start();
            isMockServerOn = true;
        }
    }

    @AfterClass
    public static void close() {
        if (isMockServerOn) {
            MockServer.stop();
        }
    }

    public static void setBaseURI(String url) {
        RestAssured.baseURI = prop.getProperty(url.toLowerCase());
    }
}
