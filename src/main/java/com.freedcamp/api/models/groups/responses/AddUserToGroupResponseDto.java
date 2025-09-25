package com.freedcamp.api.models.groups.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freedcamp.api.models.groups.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToGroupResponseDto {

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