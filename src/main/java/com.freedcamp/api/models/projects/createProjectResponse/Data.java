package com.freedcamp.api.models.projects.createProjectResponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("users")
	private List<UsersItem> users;
}