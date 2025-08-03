package com.freedcamp.api.controllers;

import com.freedcamp.api.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginController extends BaseController<LoginController> {

    @Step("Login")
    public Response login(String email, String password) {
        return given()
                .spec(RequestSpecFactory.getSpecWithoutAuth())
                .formParam("username", email)
                .formParam("password", password)
                .when()
                .get("/login")
                .then()
                .extract()
                .response();
    }
}