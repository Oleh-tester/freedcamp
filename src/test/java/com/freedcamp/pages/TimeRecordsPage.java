package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.testdata.TimeRecord;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TimeRecordsPage extends BasePage {


    @Step("Open Add Time Record sidebar")
    public TimeRecordsPage openAddTimeRecordSidebar() {
        headerMenu.$x(".//button[.='Add Time Record']").click();
        return this;
    }

    @Step("Verify Add Time Record sidebar is opened")
    public TimeRecordsPage verifyAddTimeRecordSidebarIsOpened() {
        $x("//div[contains(@class,'QuickAddDrawer')]//input").shouldBe(Condition.visible);
        return this;
    }

    @Step("Open Add Time Record full form")
    public TimeRecordsPage openAddTimeRecordFullForm() {
        $x("//button[.='Full Add Form']").click();
        return this;
    }

    @Step("Open Project dropdown")
    public TimeRecordsPage openProjectDropdown() {
        if (!$x("//div[contains(@class,'SelectOption')]").isDisplayed()) {
            $x("//div[label[contains(text(), 'Project')]]//*[contains(@class,'DropdownArrow')]").
                    shouldBe(Condition.interactable).click();
        }
        return this;
    }

    @Step("Select project from dropdown: {s}")
    public TimeRecordsPage selectProjectFromDropdown(String s) {
        $x(String.format(DROPDOWN_OPTION_BY_TEXT_XPATH, s)).click();
        return this;
    }

    @Step("Enter worked hours: {workedHours}")
    public TimeRecordsPage enterWorkedHours(String workedHours) {
        $x("//input[@placeholder='HH']").setValue(workedHours);
        return this;
    }

    @Step("Enter whats doing: {whatsDoing}")
    public TimeRecordsPage enterWhatsDoing(String whatsDoing) {
        $x("//textarea[@id]").setValue(whatsDoing);
        return this;
    }

    @Step("Change status to {status}")
    public TimeRecordsPage changeStatusTo(String status) {
        SelenideElement statusDropdown = $x("//div[label[contains(text(), 'Status')]]//*[contains(@class,'DropdownArrow')]");
        statusDropdown.click();
        $x(String.format(DROPDOWN_OPTION_BY_TEXT_XPATH, status)).click();
        return this;
    }

    @Step("Submit time record creation")
    public TimeRecordsPage submitTimeRecordCreation() {
        $x("//button[.='Create']").click();
        return this;
    }

    @Step("Verify Time Records page is loaded")
    public TimeRecordsPage verifyTimeRecordsPageIsLoaded() {
        waitUntilVisible(headerMenu.$x(".//button[div[text()='Add Time Record']]"));
        return this;
    }

    @Step("Verify time record is displayed by description, duration and status")
    public void verifyTimeRecordIsDisplayedByDescriptionDurationAndStatusLinkedToProject(TimeRecord timeRecord) {
        var xpath = String.format(
                "//div[contains(@class, 'TimeRecord-Row') and .//*[contains(text(), '%s')] " +
                        "and .//*[contains(text(), '%s')] " +
                        "and .//*[contains(@name, '%s')]" +
                        "and .//*[contains(text(), '%s')]]",
                timeRecord.description(), timeRecord.duration(), timeRecord.status(), timeRecord.project());
        $x(xpath).shouldBe(Condition.visible);
    }

    @Step("Verify time record is displayed by description and project")
    public void verifyTimeRecordIsDisplayedByDescriptionAndProject(String timeRecord, String project) {
        var xpath = String.format(
                "//div[contains(@class, 'TimeRecord-Row') and .//*[contains(text(), '%s')]" +
                        "and .//*[contains(text(), '%s')]]",
                timeRecord, project);
        $x(xpath).shouldBe(Condition.visible);
    }

    @Step("Enter record title in Quick Add Form")
    public TimeRecordsPage enterRecordTitleQuickAdd(String title) {
        $x("//*[label[text()='Item title']]//input").setValue(title);
        return this;
    }

    @Step("Click Quick Add record button")
    public TimeRecordsPage clickQuickAddButton() {
        $x("//button[.='Quick Add']").shouldBe(Condition.visible).click();
        return this;
    }
}