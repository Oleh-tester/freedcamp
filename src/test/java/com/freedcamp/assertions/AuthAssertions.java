package com.freedcamp.assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthAssertions {

    @Step("Verify successful login response.")
    public static void verifySuccessfulLogin(Response loginResponse) {
        loginResponse.then().statusCode(200);
        var body = loginResponse.getBody().asString();
        assertThat(body).contains("\"status\":\"success\"");
        assertThat(body).contains("\"redirect_to\"");
        assertThat(loginResponse.detailedCookies().hasCookieWithName("remember_identifier")).isTrue();
        assertThat(loginResponse.detailedCookies().hasCookieWithName("remember_code")).isTrue();
        assertThat(loginResponse.detailedCookies().hasCookieWithName("identity")).isTrue();
    }
@Step("Verify failed login response.")
    public static void verifyFailedLogin(Response loginResponse) {
        loginResponse.then().statusCode(200);
        var body = loginResponse.getBody().asString();
        assertThat(body).contains("\"status\":\"error\"");
        assertThat(body).contains("\"message\":\"<p>Sorry, that email\\/password combination does not match our records.<\\/p>\"");
        assertThat(loginResponse.detailedCookies().hasCookieWithName("remember_identifier")).isFalse();
        assertThat(loginResponse.detailedCookies().hasCookieWithName("remember_code")).isFalse();
        assertThat(loginResponse.detailedCookies().hasCookieWithName("identity")).isFalse();
    }
}