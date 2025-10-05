package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CalendarTests extends BaseUiTest {

    @Test
    @Tag("UI")
    @Description("Verify creating a new calendar event")
    void createNewCalendarEvent() {
        var meetName = "Meeting" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openCalendarPage()
                .verifyCalendarPageIsLoaded()
                .clickAddItemButtonOnCalendarPage()
                .selectCalendarEventOptionInPopover()
                .selectRandomProjectFromDropdown()
                .enterEventTitle(meetName)
                .clickQuickAddButton()
                .verifyEventIsDisplayedInCalendar(meetName);
    }
}