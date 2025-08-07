package com.freedcamp.testdata;

import com.freedcamp.api.models.projects.CreateProjectFromTemplateDto;
import com.freedcamp.api.models.projects.createProjectResponse.ProjectsItem;

/**
 * Represents created project from template data for testing (created Project + original payload).
 */
public record CreatedProjectFromTemplate(ProjectsItem createdProject, CreateProjectFromTemplateDto originalDto) {
}