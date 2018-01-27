package com.testrest.mockserver;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;

import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 * Created by Viswa on 1/27/2018.
 */
public class MockServer {
    public static StubServer mockServer;

    public MockServer() {
        whenHttp(mockServer).
                match(get("/test")).
                then(status(HttpStatus.OK_200));
        whenHttp(mockServer).
                match(post("/users/driver_signup")).
                then(status(HttpStatus.OK_200));
    }

    public static void start() {
        mockServer = new StubServer(8889).run();
        MockServer mockServerCon = new MockServer();
    }

    public static void stop() {
        mockServer.stop();
    }
}