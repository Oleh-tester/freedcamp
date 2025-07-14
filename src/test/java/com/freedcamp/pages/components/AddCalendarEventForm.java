package com.freedcamp.pages.components;

import com.codeborne.selenide.Condition;
import com.freedcamp.pages.BasePage;
import com.freedcamp.pages.CalendarPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AddCalendarEventForm extends BasePage {


    @Step("Enter event title.")
    public AddCalendarEventForm enterEventTitle(String title) {
        $x("//*[contains(@class,'QuickAddDrawer')]//input")
                .shouldBe(Condition.visible).setValue(title);
        return this;
    }

    @Step("Click Quick Add button.")
    public CalendarPage clickQuickAddButton() {
        $x("//*[contains(@class,'QuickAddButton')]")
                .shouldBe(Condition.visible).click();
        return new CalendarPage();
    }

    @Step("Select project in Calendar Event option in the popover.")
    public AddCalendarEventForm selectRandomProjectFromDropdown() {
        $x("//*[contains(@class,'AgSelectOption-NotSelected')]")
                .shouldBe(Condition.visible).click();
        return this;
    }
}