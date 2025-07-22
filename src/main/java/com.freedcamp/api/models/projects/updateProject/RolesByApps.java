package com.freedcamp.api.models.projects.updateProject;

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
public class RolesByApps {

	@JsonProperty("2")
	private List<String> jsonMember2;

	@JsonProperty("3")
	private List<String> jsonMember3;

	@JsonProperty("6")
	private List<String> jsonMember6;

	@JsonProperty("19")
	private List<String> jsonMember19;
}