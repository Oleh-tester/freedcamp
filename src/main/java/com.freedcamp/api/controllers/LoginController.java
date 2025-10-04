package com.freedcamp.api.controllers;

import com.freedcamp.utils.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class LoginController extends BaseController {

    @Step("Login")
    public Response login(String email, String password) {
        return given()
                .spec(RequestSpecFactory.getWebSpec())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0")
                .header("X-Requested-With", "XMLHttpRequest")
                .formParam("is_ajax", "true")
                .formParam("username", email)
                .formParam("password", password)
                .formParam("remember", "1")
                .formParam("a_token", UUID.randomUUID().toString().replace("-", "").substring(0, 13))
                .formParam("f_ajax_login", "1")
                .when()
                .post("/login")
                .then()
                .extract()
                .response();
    }
}