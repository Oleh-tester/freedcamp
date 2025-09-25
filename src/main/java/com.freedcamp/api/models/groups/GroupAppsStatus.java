package com.freedcamp.api.models.groups;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAppsStatus {

	@JsonProperty("16")
	private String jsonMember16;

	@JsonProperty("17")
	private String jsonMember17;

	@JsonProperty("20")
	private String jsonMember20;
}