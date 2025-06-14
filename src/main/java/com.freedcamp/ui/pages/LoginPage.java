package com.freedcamp.ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    @Step("Open login form.")
    public LoginPage openLoginForm() {
        $x("//a[@href='#']").click();
        return this;
    }

    @Step("Check if login form is visible.")
    public LoginPage isLoginFormVisible() {
        $x("//*[@id='login_form']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        $x("//*[@id='login_username']").setValue(username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
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
}
