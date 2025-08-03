package com.freedcamp.pages.components;

import com.freedcamp.pages.ManageSystemPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class UserMenuPopover {

    @Step("Open Manage system page")
    public ManageSystemPage openManageSystemPage() {
        $x("//*[@href='/manage_system']").click();
        return new ManageSystemPage();
    }
}