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
public class RolesItem {

	@JsonProperty("global_team_ids")
	private List<Object> globalTeamIds;

	@JsonProperty("role_type")
	private String roleType;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("user_ids")
	private List<Object> userIds;

	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;
}