package com.freedcamp.tests.ui;

import common.annotations.SkipSessionInjection;
import com.freedcamp.pages.LandingPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Tag("UI")
public class AuthTests extends BaseUiTest {

    @Test
    @SkipSessionInjection
    @Tag("Smoke")
    @DisplayName("Check user successful login")
    void loginSuccessTest() {
        open("");
        new LandingPage().openLoginForm()
                .isLoginFormVisible()
                .enterUsername(CONFIG.email())
                .enterPassword(CONFIG.password())
                .submitLogin()
                .verifyHomePageIsLoaded();
    }

    @Test
    @SkipSessionInjection
    @DisplayName("Check user logout")
    void logoutSuccessfulTest() throws InterruptedException {
        open("");
        new LandingPage().openLoginForm()
                .isLoginFormVisible()
                .enterUsername(CONFIG.email())
                .enterPassword(CONFIG.password())
                .submitLogin()
                .verifyHomePageIsLoaded()
                .sidebar().openUserMenuPopover()
                .clickLogout()
                .verifyLandingPageIsLoaded();
    }
}