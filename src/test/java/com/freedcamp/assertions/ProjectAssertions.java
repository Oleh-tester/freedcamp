package com.freedcamp.assertions;

import com.freedcamp.api.models.projects.CreateProjectDto;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;
import com.freedcamp.api.models.projects.getAllProjects.GetAllProjectsDto;
import com.freedcamp.api.models.projects.updateProject.UpdateProjectDto;
import com.freedcamp.api.models.projects.updateProject.UpdateProjectResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectAssertions {
    @Step("Verify project creation response.")
    public static void verifyProjectCreatedCorrectly(Response response, CreateProjectDto expectedData) {
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.path("msg"), "Response message should be OK");
        var createdProject = response.as(CreateProjectResponseDto.class).getData().getProjects().get(0);
        assertTrue(createdProject.getId() != null && !createdProject.getId().isEmpty(), "Project ID should not be null or empty");
        assertEquals(expectedData.getProjectName(), createdProject.getProjectName(), "Project name should match");
        assertEquals(expectedData.getGroupId(), createdProject.getGroupId(), "Project group ID should match");
        assertEquals(expectedData.getProjectDescription(), createdProject.getProjectDescription(), "Project description should match");
        assertEquals(expectedData.getProjectColor(), "#" + createdProject.getProjectColor(), "Project color should match");
        assertTrue(createdProject.isFActive(), "Project should be active");
    }

    @Step("Verify project update response.")
    public static void verifyProjectUpdatedCorrectly(Response response, UpdateProjectDto expectedData) {
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.path("msg"), "Response message should be OK");
        var createdProject = response.as(UpdateProjectResponseDto.class).getData().getProjects().get(0);
        assertTrue(createdProject.getId() != null && !createdProject.getId().isEmpty(), "Project ID should not be null or empty");
        assertEquals(expectedData.getProjectName(), createdProject.getProjectName(), "Project name should match");
        assertEquals(expectedData.getGroupId(), createdProject.getGroupId(), "Project group ID should match");
        assertEquals(expectedData.getProjectDescription(), createdProject.getProjectDescription(), "Project description should match");
        assertEquals(expectedData.getProjectColor(), createdProject.getProjectColor(), "Project color should match");
        assertTrue(createdProject.isFActive(), "Project should be active");
    }

    @Step("Verify project is displayed in all projects response.")
    public static void verifyProjectDisplayedInAllProjects(Response getAllProjectsResponse, CreateProjectResponseDto projectDto) {
        assertEquals(200, getAllProjectsResponse.getStatusCode(), "Should return 200 when getting all projects");
        var createdProject = projectDto.getData().getProjects().get(0);
        var projects = getAllProjectsResponse.as(GetAllProjectsDto.class).getData().getProjects();
        assertTrue(projects.stream()
                .anyMatch(project -> project.getProjectName().equals(createdProject.getProjectName())
                        && project.getGroupId().equals(createdProject.getGroupId())
                        && !project.getId().isEmpty()), "Project should be displayed in all projects list");
    }

    @Step("Verify project deletion response.")
    public static void verifyProjectDeletedResponse(Response deleteResponse) {
        assertEquals(200, deleteResponse.statusCode());
        assertEquals("OK", deleteResponse.path("msg"));
    }
}