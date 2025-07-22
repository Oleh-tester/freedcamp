package com.freedcamp.api.controllers;

import com.freedcamp.api.RequestSpecFactory;
import com.freedcamp.utils.FormDataSpecHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProjectController extends BaseController<ProjectController> {

    @Step("Get all projects")
    public Response getAllProjects() {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/projects")
                .then()
                .extract()
                .response();
    }

    @Step("Get project by ID: {projectId}")
    public Response getProjectById(String projectId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/projects/" + projectId)
                .then()
                .extract()
                .response();
    }

    @Step("Create new project")
    public Response createProject(Object projectDto) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), projectDto)
                .when()
                .post("/projects")
                .then()
                .extract()
                .response();
    }

    @Step("Update project")
    public Response updateProject(Object projectDto, String projectId) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), projectDto)
                .when()
                .post("/projects/" + projectId)
                .then()
                .extract()
                .response();
    }

    @Step("Delete project by ID: {projectId}")
    public Response deleteProject(String projectId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .delete("/projects/" + projectId)
                .then()
                .extract()
                .response();
    }
}