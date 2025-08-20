package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentController extends BaseController<CommentController> {

    @Step("Get all comments")
    public Response getAllComments() {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/comments")
                .then()
                .extract()
                .response();
    }

    @Step("Get comment by ID: {commentId}")
    public Response getCommentById(String commentId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/comments/" + commentId)
                .then()
                .extract()
                .response();
    }

    @Step("Create new comment")
    public Response createComment(String parentType, String parentId, String commentText) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("parent_type", parentType)
                .multiPart("parent_id", parentId)
                .multiPart("body", commentText)
                .when()
                .post("/comments")
                .then()
                .extract()
                .response();
    }
}