package com.freedcamp.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.ui.components.SideBarComponent;

public abstract class BasePage {

    protected SideBarComponent sidebar = new SideBarComponent();

    public SideBarComponent sidebar() {
        return sidebar;
    }

    protected void waitUntilVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }
}
