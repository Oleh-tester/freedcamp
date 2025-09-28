package com.freedcamp.api.controllers;

import com.freedcamp.utils.RequestSpecFactory;
import com.freedcamp.utils.FormDataSpecHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class TaskController extends BaseController {

    @Step("Get all tasks")
    public Response getAllTasks(RequestSpecification spec) {
        return given()
                .spec(spec)
                .when()
                .get("/tasks")
                .then()
                .extract()
                .response();
    }

    public Response getAllTasks() {
        return getAllTasks(RequestSpecFactory.getSpec());
    }

    @Step("Get task by ID: {taskId}")
    public Response getTaskById(long taskId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/tasks/" + taskId)
                .then()
                .extract()
                .response();
    }

    @Step("Create new task")
    public Response createTask(Object taskDto) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), taskDto)
                .when()
                .post("/tasks")
                .then()
                .extract()
                .response();
    }

    @Step("Update task")
    public Response updateTask(long taskId, Object taskDto) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), taskDto)
                .when()
                .post("/tasks/"+ taskId)
                .then()
                .extract()
                .response();
    }

    @Step("Delete task")
    public Response deleteTask(long taskId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .delete("/tasks/"+ taskId)
                .then()
                .extract()
                .response();
    }
}