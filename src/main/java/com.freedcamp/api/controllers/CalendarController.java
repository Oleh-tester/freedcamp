package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CalendarController extends BaseController<CalendarController> {

    @Step("Get all calendar events")
    public Response getCalendarEvents() {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .when()
                .get("/calendar")
                .then()
                .extract()
                .response();
    }

    @Step("Update task deadline in calendar (taskId: {taskId}, date: {newDate})")
    public Response updateTaskDate(String taskId, String newDate) {
        return given()
                .spec(RequestSpecFactory.getSpec())
                .multiPart("due_date", newDate)
                .when()
                .put("/tasks/" + taskId)
                .then()
                .extract()
                .response();
    }
}