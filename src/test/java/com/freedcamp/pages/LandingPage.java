package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LandingPage extends BasePage {

    @Step("Open login form.")
    public LandingPage openLoginForm() {
        $x("//a[@href='#']").click();
        return this;
    }

    @Step("Check if login form is visible.")
    public LandingPage isLoginFormVisible() {
        $x("//*[@id='login_form']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Enter username: {username}")
    public LandingPage enterUsername(String username) {
        $x("//*[@id='login_username']").setValue(username);
        return this;
    }

    @Step("Enter password")
    public LandingPage enterPassword(String password) {
        $x("//*[@id='login_password']").setValue(password);
        return this;
    }

    @Step("Submit login form")
    public HomePage submitLogin() {
        $x("//input[@value='Login']").click();
        return new HomePage();
    }

    @Step("Login into system.")
    public HomePage login(String username, String password) {
        openLoginForm();
        isLoginFormVisible();
        enterUsername(username);
        enterPassword(password);
        return submitLogin();
    }

    @Step("Enter email address: {email}")
    public LandingPage enterEmailAddress(String email) {
        $x("//*[@id='register']//input[@class='input email']").setValue(email);
        return this;
    }

    @Step("Submit registration form")
    public LandingPage submitRegistration() {
        $x("//*[@id='register']//*[text()='Start Now!']").click();
        return this;
    }

    @Step("Submit invite form")
    public SetupAccountPage submitInvite() {
        waitUntilVisible($x("//*[@type='submit']/*[text()='Next']"));
        $x("//*[@type='submit']/*[text()='Next']").click();
        return new SetupAccountPage();
    }

    @Step("Verify landing page is loaded")
    public LandingPage verifyLandingPageIsLoaded() throws InterruptedException {
        waitUntilVisible($x("//*[.='Freedcamp']"));
        Thread.sleep(2500);
        return this;
    }
}
