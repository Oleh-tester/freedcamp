package com.freedcamp.testdata;

import com.freedcamp.api.models.projects.CreateProjectFromTemplateDto;

/**
 * Represents created project from template data for testing (project name + original payload).
 */
public record CreatedProjectFromTemplate(String projectName, CreateProjectFromTemplateDto originalDto) {
}