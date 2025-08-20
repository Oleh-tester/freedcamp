package com.freedcamp.api.models.calendarEvents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCalendarEventDto {

	@JsonProperty("date_start")
	private String dateStart;

	@JsonProperty("time_to")
	private String timeTo;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("time_from")
	private String timeFrom;

	@JsonProperty("date_end")
	private String dateEnd;

	@JsonProperty("title")
	private String title;

	@JsonProperty("f_all_day")
	private int fAllDay;
}