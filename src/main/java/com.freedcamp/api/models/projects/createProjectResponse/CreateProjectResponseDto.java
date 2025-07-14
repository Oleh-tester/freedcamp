package com.freedcamp.api.models.projects.createProjectResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResponseDto{

	@JsonProperty("msg")
	private String msg;

	@JsonProperty("http_code")
	private int httpCode;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("error_id")
	private int errorId;

	@JsonProperty("fc_code")
	private int fcCode;
}