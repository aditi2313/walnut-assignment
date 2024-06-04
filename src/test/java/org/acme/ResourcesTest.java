package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ResourcesTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }

    @Test
    public void testGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get("/greeting/{name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + uuid));
    }

    @Test
    public void testTask1Endpoint() {
        String testJson = "{\"message\": \"This is a test message\"}";

        given()
                .body(testJson)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/task1")
                .then()
                .statusCode(200)
                .body(is(testJson));
    }

    @Test
    public void testTask2Endpoint() {
        String name = "John Doe";
        String testJson = "{\"name\": \"" + name + "\"}";

        given()
                .body(testJson)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/task2")
                .then()
                .statusCode(201); // Status code for successful save
    }

    @Test
    public void testTask3Endpoint() {
        given()
                .when().get("/task3")
                .then()
                .statusCode(200);
    }

}