package com.freedcamp.api.models.projects.updateProject;

import java.util.ArrayList;
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
public class UpdateProjectDto {

	@JsonProperty("project_description")
	private String projectDescription;

	@JsonProperty("group_id")
	private String groupId;

	@JsonProperty("project_color")
	private String projectColor;

	@JsonProperty("project_name")
	private String projectName;

	@JsonProperty("cf_confirmed_unlink_ids")
	@Builder.Default
	private List<Object> cfConfirmedUnlinkIds = new ArrayList<>();

	@JsonProperty("cf_tpl_ids")
	@Builder.Default
	private List<Object> cfTplIds = new ArrayList<>();
}