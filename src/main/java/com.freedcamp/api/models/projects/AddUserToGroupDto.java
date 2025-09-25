package com.freedcamp.api.models.projects;

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
public class AddUserToGroupDto{

	@JsonProperty("invited_global_teams")
	private List<Object> invitedGlobalTeams;

	@JsonProperty("users")
	private List<UsersItem> users;
}