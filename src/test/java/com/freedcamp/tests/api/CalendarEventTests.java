package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.EventController;
import com.freedcamp.testdata.utils.TestDataFactory;
import com.freedcamp.testdata.CreatedProject;
import common.annotations.RequiresProject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.freedcamp.assertions.CalendarEventAssertions.verifyCalendarEventCreatedCorrectly;

public class CalendarEventTests extends BaseApiTest {

    private static EventController eventController;

    @BeforeAll
    public static void setup() {
        eventController = new EventController();
    }

    @Test
    @RequiresProject
    @DisplayName("Verify create calendar event in a project.")
    void verifyCreateCalendarEventInProject(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();
        var calendarEventDto = TestDataFactory.validCalendarEventDto(targetProjectId);

        var response = eventController.createCalendarEvent(calendarEventDto);
        verifyCalendarEventCreatedCorrectly(response, calendarEventDto);
    }
}