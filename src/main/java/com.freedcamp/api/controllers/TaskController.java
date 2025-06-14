package com.freedcamp.api.controllers;

import com.freedcamp.api.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TaskController extends BaseController<TaskController> {

    @Step("Get all tasks")
    public Response getAllTasks() {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/tasks")
                .then()
                .extract()
                .response();
    }

    @Step("Get task by ID: {taskId}")
    public Response getTaskById(String taskId) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/tasks/" + taskId)
                .then()
                .extract()
                .response();
    }

    @Step("Create new task")
    public Response createTask(String title, String description) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("title", title)
                .multiPart("description", description)
                .when()
                .post("/tasks")
                .then()
                .extract()
                .response();
    }
}