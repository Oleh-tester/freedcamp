package com.freedcamp.api.models.calendarEvents;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventsItem {

	@JsonProperty("start_ts")
	private int startTs;

	@JsonProperty("occurrences")
	private List<Integer> occurrences;

	@JsonProperty("rsvp_emails")
	private List<Object> rsvpEmails;

	@JsonProperty("description_processed")
	private String descriptionProcessed;

	@JsonProperty("description")
	private String description;

	@JsonProperty("root_start_ts")
	private int rootStartTs;

	@JsonProperty("title")
	private String title;

	@JsonProperty("duration")
	private int duration;

	@JsonProperty("r_rule")
	private String rRule;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("created_ts")
	private int createdTs;

	@JsonProperty("id")
	private String id;

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("f_all_day")
	private boolean fAllDay;

	@JsonProperty("r_next_ts")
	private Object rNextTs;

	@JsonProperty("updated_ts")
	private int updatedTs;

	@JsonProperty("comments")
	private List<Object> comments;

	@JsonProperty("rsvp_data")
	private List<Object> rsvpData;

	@JsonProperty("can_edit")
	private boolean canEdit;

	@JsonProperty("date_end")
	private int dateEnd;

	@JsonProperty("f_r_ended")
	private boolean fREnded;

	@JsonProperty("due_ts")
	private int dueTs;

	@JsonProperty("url")
	private String url;

	@JsonProperty("tags")
	private List<Object> tags;

	@JsonProperty("f_response_notify")
	private boolean fResponseNotify;

	@JsonProperty("date_start")
	private int dateStart;

	@JsonProperty("followers")
	private List<String> followers;

	@JsonProperty("end_ts")
	private int endTs;

	@JsonProperty("comments_count")
	private int commentsCount;

	@JsonProperty("files")
	private List<Object> files;

	@JsonProperty("created_by_id")
	private String createdById;

	@JsonProperty("root_due_ts")
	private int rootDueTs;
}