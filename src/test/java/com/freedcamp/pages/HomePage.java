package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.freedcamp.pages.components.AddProjectForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {
    @Step("Verify that the home page is loaded.")
    public HomePage verifyHomePageIsLoaded() {
        waitUntilVisible($x("//*[@id='web-app-root']"));
        return this;
    }

    @Step("Click Add project button.")
    public HomePage clickAddProjectButton() {
        $x("//*[.='Add Project']").click();
        return this;
    }

    @Step("Select project from scratch option in Add project dropdown.")
    public AddProjectForm selectProjectFromScratch() {
        if ($x("//*[.='From scratch']").isDisplayed()) {
            $x("//*[.='From scratch']").click();
        }
        return new AddProjectForm();
    }

    @Step("Verify Project is displayed in side bar.")
    public void verifyProjectIsDisplayedInSideBar(String newProject) {
        $x("//*[contains(@class, 'Sidebar')]//*[text()='" + newProject + "']").shouldBe(Condition.visible);
    }
}
