package com.freedcamp.pages;

import com.freedcamp.pages.components.CreateProjectFromTemplateDrawer;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ManageSystemPage extends BasePage {

    @Step("Verify Manage System page is loaded")
    public ManageSystemPage verifyManageSystemPageIsLoaded() {
        $x(".//button[div[text()='Add Project']]");
        return this;
    }

    @Step("Switch to Templates tab")
    public ManageSystemPage switchToTemplatesTab() {
        $x("//*[.='Templates'][@id]").click();
        return this;
    }

    @Step("Open Project Template Popover")
    public ManageSystemPage openProjectTemplatePopover() {
        $x("//*[contains(@class,'project_template')]//*[contains(@class,'popover')]").click();
        return this;
    }

    @Step("Select 'Create Project from Template' option")
    public CreateProjectFromTemplateDrawer selectCreateProjectFromTemplateOption() {
        $x("//*[contains(@class,'DropdownItem')]//*[contains(text(),'Create project from template')]").click();
        return new CreateProjectFromTemplateDrawer();
    }
}