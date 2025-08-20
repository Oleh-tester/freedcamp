package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GroupController extends BaseController<GroupController> {

    @Step("Create new group")
    public Response createGroup(String name) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("name", name)
                .when()
                .post("/groups")
                .then()
                .extract()
                .response();
    }

    @Step("Move project {projectId} to group {groupId}")
    public Response moveProjectToGroup(String projectId, String groupId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("group_id", groupId)
                .when()
                .post("/projects/" + projectId + "/group")
                .then()
                .extract()
                .response();
    }

    @Step("Get all groups")
    public Response getAllGroups() {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/groups")
                .then()
                .extract()
                .response();
    }
}