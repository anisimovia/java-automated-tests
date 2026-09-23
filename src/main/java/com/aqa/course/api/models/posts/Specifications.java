package com.aqa.course.api.models.posts;

import com.aqa.course.core.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    public RequestSpecification baseRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setBaseUri(new Config().getApiUrl());
        return requestSpecBuilder.build();

    }
}
