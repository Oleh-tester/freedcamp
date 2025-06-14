package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.components.SideBarComponent;

public abstract class BasePage {

    protected SideBarComponent sidebar = new SideBarComponent();

    public SideBarComponent sidebar() {
        return sidebar;
    }

    protected void waitUntilVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }
}
