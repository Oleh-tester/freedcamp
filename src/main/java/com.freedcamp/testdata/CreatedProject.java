package com.freedcamp.testdata;

import com.freedcamp.api.models.projects.CreateProjectDto;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;

/**
 * Represents created project data for testing (ID + original payload).
 */
public record CreatedProject(CreateProjectResponseDto createdProjectResponseDto, CreateProjectDto originalDto) {
}