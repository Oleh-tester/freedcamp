package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.LoginController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.freedcamp.assertions.AuthAssertions.verifyFailedLogin;
import static com.freedcamp.assertions.AuthAssertions.verifySuccessfulLogin;

public class AuthTests extends BaseApiTest {
    private static LoginController loginController;

    @BeforeAll
    public static void setup() {
        loginController = new LoginController();
    }

    @Test
    @Tag("API")
    @Tag("Smoke")
    public void testSuccessfulLogin() {
        var response = loginController.login(CONFIG.email(), CONFIG.password());

        verifySuccessfulLogin(response);
    }

    @Test
    @Tag("API")
    public void testLoginWithInvalidCredentials() {
        var response = loginController.login(CONFIG.email(), CONFIG.password() + "1");

        verifyFailedLogin(response);
    }
}