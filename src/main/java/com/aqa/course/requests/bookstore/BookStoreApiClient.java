package com.aqa.course.requests.bookstore;

import com.aqa.course.api.models.bookstore.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreApiClient {

    private static final String BASE_URL = "https://demoqa.com";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    // --- BookStore endpoints ---

    public Response getAllBooks() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books");
    }

    public Response getBookByIsbn(String isbn) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("ISBN", isbn)
                .when()
                .get("/BookStore/v1/Book");
    }

    // --- Account endpoints ---

    public Response createUser(UserCredentials credentials) {
        return given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/Account/v1/User");
    }

    public Response generateToken(UserCredentials credentials) {
        return given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/Account/v1/GenerateToken");
    }

    public Response isAuthorized(UserCredentials credentials) {
        return given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/Account/v1/Authorized");
    }

    public Response getUser(String userId, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId);
    }

    public Response deleteUser(String userId, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/Account/v1/User/" + userId);
    }
}