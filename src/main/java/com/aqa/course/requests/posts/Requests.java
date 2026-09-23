package com.aqa.course.requests.posts;

import com.aqa.course.api.models.posts.Posts;
import com.aqa.course.api.models.posts.Specifications;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {
    private Specifications specifications;
    private Gson gsonMapper;

    public Requests() {
        this.specifications = new Specifications();
        this.gsonMapper = new Gson();
    }

    public Response createPosts (Posts posts) {
        return given()
                .spec(specifications.baseRequestSpec())
                .body(gsonMapper.toJson(posts))
                .post("/posts/");
    }

    public Response getPosts () {
        return given()
                .spec(specifications.baseRequestSpec())
                .get("/posts/2");
    }

    public Response updatePosts(Posts posts) {
        return given()
                .spec(specifications.baseRequestSpec())
                .body(gsonMapper.toJson(posts))
                .put("/posts/2");
    }

    public Response deletePosts() {
        return given()
                .spec(specifications.baseRequestSpec())
                .delete("/posts/2");
    }
}
