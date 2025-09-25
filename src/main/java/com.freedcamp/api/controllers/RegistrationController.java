package com.freedcamp.api.controllers;

import com.freedcamp.api.UserSession;
import com.freedcamp.api.helpers.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * Handles public registration flow (unauthenticated) and post-signup project retrieval for the newly created user.
 */
public class RegistrationController extends BaseController<RegistrationController> {

    private String randomToken13() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 13);
    }

    @Step("Submit email on registration form for {email}")
    public Response registerEmail(String email, SessionFilter session) {
        var aToken = randomToken13();
        var aJsToken = randomToken13();
        return given()
                .spec(RequestSpecFactory.getWebSpec())
                .filter(session)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("is_ajax", "true")
                .formParam("time_on_page", "12")
                .formParam("a_token", aToken)
                .formParam("agree_terms", "1")
                .formParam("email", email)
                .formParam("a_js_token", aJsToken)
                .when()
                .post("/register")
                .then()
                .extract()
                .response();
    }

    @Step("Complete account settings for new user and capture session")
    public UserSession completeAccountSettings(String name, String password, String timezone, SessionFilter session) {
        Response resp = given()
                .spec(RequestSpecFactory.getWebSpec())
                .filter(session)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("name", name)
                .formParam("password", password)
                .formParam("f_subscribe_news", "0")
                .formParam("timezone", timezone)
                .when()
                .post("/register/account_settings")
                .then()
                .extract()
                .response();

        // Build cookie map for subsequent authenticated API calls (focus on ci_session + remember tokens if present)
        var restAssuredCookies = resp.detailedCookies();
        java.util.Map<String,String> cookieMap = new java.util.HashMap<>();
        restAssuredCookies.asList().forEach(c -> cookieMap.put(c.getName(), c.getValue()));
        return new UserSession(cookieMap);
    }

    @Step("Get projects for newly registered user")
    public Response getProjects(UserSession session) {
        return given()
                .spec(RequestSpecFactory.getSpecWithCookies(session.cookies()))
                .when()
                .get("/projects")
                .then()
                .extract()
                .response();
    }
}
