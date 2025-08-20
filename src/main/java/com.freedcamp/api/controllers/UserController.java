package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserController extends BaseController<UserController> {

    @Step("Get all users for project {projectId}")
    public Response getUsersForProject(String projectId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/projects/" + projectId + "/users")
                .then()
                .extract()
                .response();
    }
}