package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.freedcamp.pages.components.AddCalendarEventForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CalendarPage extends BasePage {
    @Step("Select date {day} in the calendar")
    public CalendarPage selectDate(int day) {
        $x("//*[@class='k-scheduler-table']//*[contains(text(),'" + day + "')]")
                .shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Select Calendar event option in Add item popover")
    public AddCalendarEventForm selectCalendarEventOptionInPopover() {
        $x("//*[.='Calendar Event']")
                .shouldBe(Condition.visible).click();
        return new AddCalendarEventForm();
    }

    @Step("Verify that the event is displayed in the calendar with title {title}")
    public void verifyEventIsDisplayedInCalendar(String title) {
        $x("//div[contains(@class,'custom-event fc-calendar')]//*[.='" + title + "']")
                .shouldBe(Condition.visible);
    }

    @Step("Verify that the Calendar page is loaded")
    public CalendarPage verifyCalendarPageIsLoaded() {
        $x("//div[@id='scheduler']").shouldBe(Condition.visible);
        $x("//*[text()='Loading...']").shouldBe(Condition.disappear);
        return this;
    }

    @Step("Click Add item button on the calendar page")
    public CalendarPage clickAddItemButtonOnCalendarPage() {
        $x("//*[.='Add Item']").shouldBe(Condition.visible).click();
        return this;
    }
}