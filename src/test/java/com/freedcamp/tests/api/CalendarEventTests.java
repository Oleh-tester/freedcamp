package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.EventController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.TestDataSetupExtension;
import common.annotations.RequiresProject;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.freedcamp.assertions.CalendarEventAssertions.verifyCalendarEventCreatedCorrectly;

@ExtendWith({AllureJunit5.class, TestDataSetupExtension.class})
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