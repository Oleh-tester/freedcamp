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
public class MsData {

	@JsonProperty("project_apps_status")
	private ProjectAppsStatus projectAppsStatus;

	@JsonProperty("roles")
	private List<RolesItem> roles;

	@JsonProperty("access_requests")
	private List<Object> accessRequests;

	@JsonProperty("roles_by_apps")
	private RolesByApps rolesByApps;

	@JsonProperty("pending_user_ids")
	private List<Object> pendingUserIds;
}