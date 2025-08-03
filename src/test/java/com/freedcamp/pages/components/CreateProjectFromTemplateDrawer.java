package com.freedcamp.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.ManageSystemPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CreateProjectFromTemplateDrawer extends ManageSystemPage {

    private final SelenideElement projectNameField = $x("//div[label[contains(text(),'Project Name')]]//input");

    @Step("Verify Create Project from Template drawer is opened")
    public CreateProjectFromTemplateDrawer verifyCreateProjectFromTemplateDrawerIsOpened() {
        $x("//*[@role='dialog']//*[contains(text(),'Create project from template')]")
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Clear project name field")
    public void clearProjectNameField() {
       clearJs(projectNameField);
        projectNameField.shouldBe(Condition.empty);
    }

    @Step("Fill in project name field {projectName}")
    public CreateProjectFromTemplateDrawer fillInProjectName(String projectName) {
        clearProjectNameField();
        projectNameField.setValue(projectName);
        return this;
    }

    @Step("Submit project creation")
    public void submitProjectCreation() {
        $x("//*[@role='dialog']//*[contains(text(),'Create Project')]").click();
    }
}