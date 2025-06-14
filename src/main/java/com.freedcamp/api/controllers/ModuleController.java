package com.freedcamp.api.controllers;

import com.freedcamp.api.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ModuleController extends BaseController<ModuleController> {

    @Step("Add module {moduleName} to project {projectId}")
    public Response addModuleToProject(String projectId, String moduleName) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("module", moduleName)
                .when()
                .post("/projects/" + projectId + "/modules")
                .then()
                .extract()
                .response();
    }

    @Step("Get all modules for project {projectId}")
    public Response getModulesForProject(String projectId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/projects/" + projectId + "/modules")
                .then()
                .extract()
                .response();
    }
}