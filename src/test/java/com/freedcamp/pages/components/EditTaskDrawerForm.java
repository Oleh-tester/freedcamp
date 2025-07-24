package com.freedcamp.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.BasePage;
import com.freedcamp.pages.TasksPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.*;

public class EditTaskDrawerForm extends BasePage {

    private final SelenideElement editTaskDrawer = $x("//*[contains(@class,'Drawer-Open')]");

    @Step("Verify Edit Task form is loaded")
    public EditTaskDrawerForm verifyEditTaskDrawerIsOpened() {
        waitUntilVisible(editTaskDrawer);
        return this;
    }

    @Step("Change task status to: {status}")
    public EditTaskDrawerForm changeStatusTo(String status) {
        editTaskDrawer.$x(".//div[*[text()='Status']]//*[contains(@class,'action_trigger')]").click();
        $x("//*[contains(@class,'SelectOption')]//*[text()='" + status + "']").click();
        return this;
    }

    @Step("Verify new status {status} is displayed")
    public EditTaskDrawerForm verifyNewStatusIsDisplayed(String status) {
        editTaskDrawer.$x(".//div[.='" + status + "']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Change task description to: {desc}")
    public EditTaskDrawerForm changeDescription(String desc) {
        editTaskDrawer.$x(".//div[*[local-name()='svg' and @name='AgDescription']]//*[@name='AgEdit']").click();
        switchTo().frame($("iframe").shouldHave(attributeMatching("title", ".*Rich Text Editor.*")));
        $x("//body").shouldBe(Condition.visible).setValue(desc);
        switchTo().defaultContent();
        editTaskDrawer.$x(".//button[.='Save']").click();
        return this;
    }

    @Step("Verify task description is updated to: {updatedDescriptionForTheTask}")
    public void verifyDescriptionIsUpdated(String updatedDescriptionForTheTask) {
        editTaskDrawer.$x(".//div[contains(@class,'Section-Children')]//*[contains(@class,'ItemDescription')]//p")
                .shouldHave(Condition.text(updatedDescriptionForTheTask));
    }

    @Step("Open more actions menu for task in edit drawer")
    public EditTaskDrawerForm openMoreActionsMenu() {
        editTaskDrawer.$x(".//*[contains(@name,'MenuDots')]").click();
        return this;
    }

    @Step("Click Delete Task button in edit drawer")
    public EditTaskDrawerForm clickDeleteTaskButton() {
        $x("//*[contains(@class, 'DropdownItem') and contains(text(), 'Delete')]").click();
        return this;
    }

    @Step("Confirm delete task in edit drawer")
    public TasksPage confirmDeleteTask() {
        $x("//*[contains(@class,'Confirm-Actions')]//*[text()='Yes']").click();
        editTaskDrawer.shouldNotBe(Condition.visible);
        return new TasksPage();
    }}
