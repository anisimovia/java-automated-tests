package com.aqa.course.requests.posts;

import com.aqa.course.api.models.posts.Posts;
import org.apache.http.HttpStatus;

public class SuccessfulRequests {
    private Requests requests;

    public SuccessfulRequests() {
        this.requests = new Requests();
    }

    public void createPosts(Posts posts) {
        requests.createPosts(posts)
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);
        }

    public Posts getPosts() {
        return requests.getPosts()
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().body().as(Posts.class);
    }

    public void updatePosts(Posts posts) {
        requests.updatePosts(posts)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    public void deletePosts() {
        requests.deletePosts()
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
