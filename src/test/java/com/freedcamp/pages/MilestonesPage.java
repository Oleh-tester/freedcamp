package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MilestonesPage extends BasePage {

    @Step("Click on Add Milestone button")
    public MilestonesPage clickAddMilestoneButton() {
        headerMenu.$x(".//button[.='Add Milestone']").click();
        return this;
    }

    @Step("Open Project dropdown")
    public MilestonesPage openProjectDropdown() {
        $x("//div[label[contains(text(), 'Project')]]//*[contains(@class,'DropdownArrow')]").click();
        return this;
    }

    @Step("Select project {string}.")
    public MilestonesPage selectProject(String projectName) {
        if (!$x(String.format(DROPDOWN_OPTION_BY_TEXT_XPATH, projectName)).exists()) {
            $x(".//div[label[contains(text(), 'Project')]]//div[contains(@class, 'FormField')]").click();
        }
        $x(String.format(DROPDOWN_OPTION_BY_TEXT_XPATH, projectName)).click();
        return this;
    }

    @Step("Enter milestone title: {title}")
    public MilestonesPage enterMilestoneTitle(String title) {
        $x("//input[contains(@class,'Input')]").setValue(title);
        return this;
    }

    @Step("Click Quick Add button")
    public MilestonesPage clickQuickAddButton() {
        $x("//button[.='Quick Add']").click();
        return this;
    }

    @Step("Verify Milestones page is loaded")
    public void verifyMilestoneIsDisplayedInListByProjectAndName(String project, String milestoneName) {
        String xpath = String.format(
                "//div[contains(@class, 'MilestoneRow')][.//div[contains(@class, 'AgText') and text()='%s'] " +
                        "and .//*[contains(@class, 'ItemEditableTitle') and text()='%s']]",
                project, milestoneName);
        $x(xpath).shouldBe(Condition.visible);
    }
}