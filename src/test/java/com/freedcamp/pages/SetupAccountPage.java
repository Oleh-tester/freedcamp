package com.freedcamp.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SetupAccountPage extends BasePage {

    @Step("Verify that the setup account page is loaded.")
    public SetupAccountPage verifySetupAccountPageIsLoaded() {
        waitUntilVisible($x("//*[@id='account_settings_form']"));
        return this;
    }

    @Step("Enter fullName: {fullName}")
    public SetupAccountPage enterFullName(String fullName) {
        waitUntilVisible($x("//*[@id='name']"));
        $x("//*[@id='name']").setValue(fullName);
        return this;
    }

    @Step("Enter password")
    public SetupAccountPage enterPassword(String password) {
        $x("//*[@id='password']").setValue(password);
        return this;
    }

    @Step("Uncheck 'Send me product updates and offers from Freedcamp' checkbox")
    public SetupAccountPage uncheckSendMeProductUpdatesCheckbox() {
        $x("//*[@type='checkbox'][@ng-click='f_subscribe_news = !f_subscribe_news']").click();
        return this;
    }

    @Step("Click Update account button" )
    public HomePage clickUpdateAccountButton() {
        $x("//*[@ng-click='submitAccountSettingsForm()']").click();
        return new HomePage();
    }
}