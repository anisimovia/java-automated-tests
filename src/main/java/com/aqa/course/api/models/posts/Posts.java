package com.aqa.course.api.models.posts;

import java.util.Objects;
public class Posts {
    private String title;
    private String body;
    private String userId;

    public Posts(String title, String body, String userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return userId == posts.userId && Objects.equals(title, posts.title) && Objects.equals(body, posts.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, userId);
    }
}
