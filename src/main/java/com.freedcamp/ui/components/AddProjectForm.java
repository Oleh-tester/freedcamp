package com.freedcamp.ui.components;

import com.codeborne.selenide.Condition;
import com.freedcamp.ui.pages.BasePage;
import com.freedcamp.ui.pages.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AddProjectForm extends BasePage {

    @Step("Verify that the Add Project form is opened.")
    public AddProjectForm verifyAddProjectFormIsOpened() {
        waitUntilVisible($x("//*[.='Add Project']"));
        return this;
    }

    @Step("Enter project name: {projectName}")
    public AddProjectForm enterProjectName(String projectName) {
        $x("//div[label[text()='Project Name']]//input").shouldBe(Condition.visible).setValue(projectName);
        return this;
    }

    @Step("Enter Project Description.")
    public AddProjectForm enterProjectDescription(String description) {
        $x("//div[label[text()='Project Description']]//textarea").shouldBe(Condition.visible).setValue(description);
        return this;
    }

    @Step("Click Create Project button.")
    public HomePage clickCreateProjectButton() {
        $x("//button[.='Create Project']").shouldBe(Condition.visible).click();
        return new HomePage();
    }
}
