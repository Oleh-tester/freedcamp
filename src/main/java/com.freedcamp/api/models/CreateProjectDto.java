package com.freedcamp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
}