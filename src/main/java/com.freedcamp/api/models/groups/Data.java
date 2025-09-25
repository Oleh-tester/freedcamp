package com.freedcamp.api.models.groups;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freedcamp.api.models.UsersItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

	@JsonProperty("projects")
	private List<Object> projects;

	@JsonProperty("teams")
	private List<Object> teams;

	@JsonProperty("groups")
	private List<GroupsItem> groups;

	@JsonProperty("users")
	private List<UsersItem> users;
}