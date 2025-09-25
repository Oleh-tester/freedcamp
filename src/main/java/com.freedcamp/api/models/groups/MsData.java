package com.freedcamp.api.models.groups;

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
public class MsData{

	@JsonProperty("group_users")
	private List<Object> groupUsers;

	@JsonProperty("group_app_users")
	private List<Object> groupAppUsers;

	@JsonProperty("group_apps_status")
	private GroupAppsStatus groupAppsStatus;

	@JsonProperty("user_ids")
	private List<String> userIds;

	@JsonProperty("project_apps_status")
	private ProjectAppsStatus projectAppsStatus;

	@JsonProperty("owner_plan_title")
	private String ownerPlanTitle;

	@JsonProperty("group_global_teams")
	private List<Object> groupGlobalTeams;

	@JsonProperty("applications_installed")
	private List<Object> applicationsInstalled;

	@JsonProperty("pending_user_ids")
	private List<Object> pendingUserIds;
}