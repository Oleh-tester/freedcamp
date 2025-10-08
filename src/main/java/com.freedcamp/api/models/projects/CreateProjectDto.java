package com.freedcamp.api.models.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDto {

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("project_description")
    private String projectDescription;

    @JsonProperty("project_color")
    private String projectColor;

    @JsonProperty("todo_view_type")
    private String todoViewType;

    @JsonProperty("usage_type")
    private String usageType;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("app_ids")
    private java.util.List<String> appIds;
}