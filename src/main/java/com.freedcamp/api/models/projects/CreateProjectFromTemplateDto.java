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
public class CreateProjectFromTemplateDto {

	@JsonProperty("src_template_id")
	private String srcTemplateId;

	@JsonProperty("project_description")
	private String projectDescription;

	@JsonProperty("group_id")
	private String groupId;

	@JsonProperty("project_color")
	private String projectColor;

	@JsonProperty("app_ids")
	private List<String> appIds;

	@JsonProperty("project_name")
	private String projectName;
}