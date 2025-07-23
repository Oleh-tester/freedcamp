package com.freedcamp.api.models.projects.updateProject;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freedcamp.api.models.projects.createProjectResponse.GroupsItem;
import com.freedcamp.api.models.projects.createProjectResponse.UsersItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

	@JsonProperty("projects")
	private List<ProjectsItem> projects;

	@JsonProperty("groups")
	private List<GroupsItem> groups;

	@JsonProperty("global_teams")
	private List<Object> globalTeams;

	@JsonProperty("users")
	private List<UsersItem> users;
}