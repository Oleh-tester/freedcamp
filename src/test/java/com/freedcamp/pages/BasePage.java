package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.components.SideBarComponent;

import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {

    public final SelenideElement headerMenu = $x("//*[contains(@class, 'AppHeader')]");
    public final String DROPDOWN_OPTION_BY_TEXT_XPATH = "//div[contains(@class,'SelectOption')" +
            " and contains(text(),'%s')]";
    protected SideBarComponent sidebar = new SideBarComponent();

    public SideBarComponent sidebar() {
        return sidebar;
    }

    protected void waitUntilVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }

    public void refreshPage() {
        refresh();
    }

    protected void clearJs(SelenideElement xpath) {
        xpath.shouldBe(Condition.visible);
        executeJavaScript("arguments[0].value=''", xpath);
        executeJavaScript("arguments[0].dispatchEvent(new Event('input'))", xpath);
    }
}
