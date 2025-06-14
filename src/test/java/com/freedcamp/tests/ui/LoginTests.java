package com.freedcamp.tests.ui;

import common.annotations.SkipSessionInjection;
import com.freedcamp.ui.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends BaseUiTest {

    @Test
    @SkipSessionInjection
    @Tag("UI")
    @Tag("Smoke")
    @DisplayName("Check user successful login")
    void loginSuccessTest() {
        open("");
        new LoginPage().openLoginForm()
                .isLoginFormVisible()
                .enterUsername(CONFIG.email())
                .enterPassword(CONFIG.password())
                .submitLogin()
                .verifyHomePageIsLoaded();
    }
}
