package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import common.annotations.RequiresProject;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
public class CalendarTests extends BaseUiTest {

    @Test
    @RequiresProject
    @Description("Verify creating a new calendar event")
    void createNewCalendarEvent() {
        var meetName = "Meeting" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openCalendarPage()
                .verifyCalendarPageIsLoaded()
                .clickAddItemButtonOnCalendarPage()
                .selectCalendarEventOptionInPopover()
                .verifyAddCalendarEventFormIsOpened()
                .selectRandomProjectFromDropdown()
                .enterEventTitle(meetName)
                .clickQuickAddButton()
                .verifyEventIsDisplayedInCalendar(meetName);
    }
}