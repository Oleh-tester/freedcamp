package com.freedcamp.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.ui.pages.BasePage;
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
        editTaskDrawer.$x(".//div[*[text()='Status']]//*[contains(@class,'action_trigger')]")
                .shouldBe(Condition.visible).click();
        $x("//*[contains(@class,'SelectOption')]//*[text()='" + status + "']").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Verify new status {status} is displayed")
    public EditTaskDrawerForm verifyNewStatusIsDisplayed(String status) {
        editTaskDrawer.$x(".//div[.='" + status + "']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Change task description to: {desc}")
    public EditTaskDrawerForm changeDescription(String desc) {
        editTaskDrawer.$x(".//div[*[local-name()='svg' and @name='AgDescription']]//*[@name='AgEdit']")
                .shouldBe(Condition.visible).click();
        switchTo().frame($("iframe").shouldHave(attributeMatching("title", ".*Rich Text Editor.*")));
        $x("//body").shouldBe(Condition.visible).setValue(desc);
        switchTo().defaultContent();
        editTaskDrawer.$x(".//button[.='Save']").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Verify task description is updated to: {updatedDescriptionForTheTask}")
    public void verifyDescriptionIsUpdated(String updatedDescriptionForTheTask) {
        editTaskDrawer.$x(".//div[contains(@class,'Section-Children')]//*[contains(@class,'ItemDescription')]//p")
                .shouldHave(Condition.text(updatedDescriptionForTheTask));
    }
}
