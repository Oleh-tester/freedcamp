package com.freedcamp.api.models.groups.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freedcamp.api.models.groups.Features;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsItem {

	@JsonProperty("f_public")
	private boolean fPublic;

	@JsonProperty("cf_tpl_ids")
	private List<Object> cfTplIds;

	@JsonProperty("cf_tpl_id")
	private String cfTplId;

	@JsonProperty("project_name")
	private String projectName;

	@JsonProperty("archived_by_id")
	private String archivedById;

	@JsonProperty("tag_titles")
	private List<Object> tagTitles;

	@JsonProperty("features")
	private Features features;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("created_ts")
	private int createdTs;

	@JsonProperty("project_color")
	private String projectColor;

	@JsonProperty("f_template_project")
	private boolean fTemplateProject;

	@JsonProperty("id")
	private String id;

	@JsonProperty("f_can_manage")
	private boolean fCanManage;

	@JsonProperty("order")
	private int order;

	@JsonProperty("f_favorite")
	private boolean fFavorite;

	@JsonProperty("f_can_delete")
	private boolean fCanDelete;

	@JsonProperty("role_type")
	private String roleType;

	@JsonProperty("cs_tpl_id")
	private String csTplId;

	@JsonProperty("group_name")
	private String groupName;

	@JsonProperty("user_ids")
	private List<String> userIds;

	@JsonProperty("project_unique_name")
	private String projectUniqueName;

	@JsonProperty("f_active")
	private boolean fActive;

	@JsonProperty("archived_ts")
	private Object archivedTs;

	@JsonProperty("users")
	private List<String> users;

	@JsonProperty("group_unique_name")
	private String groupUniqueName;

	@JsonProperty("role_name")
	private String roleName;

	@JsonProperty("f_no_cft_option")
	private boolean fNoCftOption;

	@JsonProperty("project_description")
	private String projectDescription;

	@JsonProperty("group_id")
	private String groupId;

	@JsonProperty("it_prefix")
	private String itPrefix;

	@JsonProperty("notifications_count")
	private int notificationsCount;

	@JsonProperty("created_by_id")
	private String createdById;

	@JsonProperty("f_can_leave")
	private boolean fCanLeave;

	@JsonProperty("applications")
	private List<String> applications;

	@JsonProperty("f_no_cfi_option")
	private boolean fNoCfiOption;
}