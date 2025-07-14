package com.freedcamp.api.models.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto{

	@JsonProperty("r_rule")
	private String rRule;

	@JsonProperty("assigned_to_id")
	private String assignedToId;

	@JsonProperty("time_to")
	private String timeTo;

	@JsonProperty("task_group_id")
	private String taskGroupId;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("time_from")
	private String timeFrom;

	@JsonProperty("f_top")
	private boolean fTop;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("priority")
	private int priority;

	@JsonProperty("conditions")
	private TaskConditions conditions;

	@JsonProperty("status")
	private int status;
}