package com.freedcamp.api.models.tasks;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data{

	@JsonProperty("tasks")
	private List<TasksItem> tasks;
}