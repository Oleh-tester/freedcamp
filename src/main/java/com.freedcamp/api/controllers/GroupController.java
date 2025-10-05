package com.freedcamp.api.controllers;

import com.freedcamp.utils.RequestSpecFactory;
import com.freedcamp.utils.FormDataSpecHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GroupController extends BaseController {

    @Step("Create new group")
    public Response createGroup(Object group) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), group)
                .when()
                .post("/groups")
                .then()
                .extract()
                .response();
    }

    @Step("Delete new group")
    public Response deleteGroup(int groupId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .delete("/groups/{groupId}", groupId)
                .then()
                .extract()
                .response();
    }

    @Step("Add user to the project group")
    public Response addUserToProjectGroup(String projectId, Object group) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), group)
                .when()
                .post("/group_memberships/" + projectId)
                .then()
                .extract()
                .response();
    }
}