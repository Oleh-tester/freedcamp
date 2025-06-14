package com.freedcamp.api.controllers;

import com.freedcamp.api.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileController extends BaseController<FileController> {

    @Step("Upload file to project")
    public Response uploadFileToProject(String projectId, File file) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("project_id", projectId)
                .multiPart("file", file)
                .when()
                .post("/files")
                .then()
                .extract()
                .response();
    }

    @Step("Attach file to task {taskId}")
    public Response attachFileToTask(String taskId, File file) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("task_id", taskId)
                .multiPart("file", file)
                .when()
                .post("/files")
                .then()
                .extract()
                .response();
    }

    @Step("Attach file to discussion {discussionId}")
    public Response attachFileToDiscussion(String discussionId, File file) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("discussion_id", discussionId)
                .multiPart("file", file)
                .when()
                .post("/files")
                .then()
                .extract()
                .response();
    }
}