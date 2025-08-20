package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import com.freedcamp.utils.FormDataSpecHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EventController extends BaseController<EventController> {

    @Step("Create calendar event in a project")
    public Response createCalendarEvent(Object calendarEvent) {
        return FormDataSpecHelper.applyJsonFormData(given()
                        .spec(RequestSpecFactory.getSpec())
                        .contentType(ContentType.MULTIPART), calendarEvent)
                .when()
                .post("/events")
                .then()
                .extract()
                .response();
    }
}