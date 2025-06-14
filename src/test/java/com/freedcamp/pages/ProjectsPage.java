package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage extends BasePage {

    @Step("Verify Projects page is loaded")
    public ProjectsPage verifyProjectPageIsLoaded() {
        waitUntilVisible($x("//*[@class='font-header-3' and text()='Projects']"));
        return this;
    }

    @Step("Verify project {projectName} is displayed in Projects page")
    public void verifyProjectIsDisplayedInProjectsPage(String projectName) {
        $x("//*[contains(@class, 'ProjectsSection')]//*[text()='" + projectName + "']").shouldBe(Condition.visible);
    }
}
