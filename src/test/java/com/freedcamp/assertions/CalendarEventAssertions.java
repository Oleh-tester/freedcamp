package com.freedcamp.assertions;

import com.freedcamp.api.models.calendarEvents.CreateCalendarEventDto;
import com.freedcamp.api.models.calendarEvents.CreateCalendarEventResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarEventAssertions {

    @Step("Verify calendar event creation response.")
    public static void verifyCalendarEventCreatedCorrectly(Response response, CreateCalendarEventDto calendarEventDto) {
        var createCalendarEventResponseDto = (response.as(CreateCalendarEventResponseDto.class)).getData().getEvents().get(0);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(createCalendarEventResponseDto.getId())
                .isNotNull()
                .isNotEmpty();
        assertThat(createCalendarEventResponseDto.getProjectId()).isEqualTo(calendarEventDto.getProjectId());
        assertThat(createCalendarEventResponseDto.getTitle()).isEqualTo(calendarEventDto.getTitle());
        var dateStart = convertToLocalDate(createCalendarEventResponseDto.getDateStart());
        var dateTo = convertToLocalDate(createCalendarEventResponseDto.getDateEnd());
        assertThat(dateStart).isEqualTo(calendarEventDto.getDateStart());
        assertThat(dateTo).isEqualTo(calendarEventDto.getDateEnd());
    }

    private static String convertToLocalDate(long epochSeconds) {
        return Instant.ofEpochSecond(epochSeconds)
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .toString();
    }
}