package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.helpers.TestDataCreator;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.freedcamp.assertions.ProjectAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllureJunit5.class)
public class ProjectTests extends BaseApiTest {
    private static ProjectController projectController;

    @BeforeAll
    public static void setup() {
        projectController = new ProjectController();
    }

    @Test
    @DisplayName("Should create project and return correct response")
    void shouldCreateProjectSuccessfully() {
        var projectDto = TestDataFactory.validProjectDto();

        var createProjectResponse = projectController.createProject(projectDto);
        verifyProjectCreatedCorrectly(createProjectResponse, projectDto);
    }

    @Test
    @DisplayName("Verify project is displayed in all projects after creation")
    void shouldDisplayProjectInAllProjectsAfterCreation() {
        var createdProject = createTestProject();

        var getAllProjectsResponse = projectController.getAllProjects();
        verifyProjectDisplayedInAllProjects(getAllProjectsResponse, createdProject);
    }

    @Test
    @DisplayName("Verify deleting a project")
    void shouldDeleteProjectSuccessfully() {
        var targetProjectId = TestDataCreator.createRandomProjectAndGetId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        verifyProjectDeletedResponse(deleteProjectResponse);
    }

    @Test
    @DisplayName("Verify project is deleted and not found after deletion")
    void shouldNotFindDeletedProject() {
        var targetProjectId = createAndThenDeleteTestProject();

        var getProjectResponse = projectController.getProjectById(targetProjectId);
        assertEquals(400, getProjectResponse.statusCode(), "Project should be deleted and not found");
    }

    private CreateProjectResponseDto createTestProject() {
        var projectDto = TestDataFactory.validProjectDto();

        Response response = projectController.createProject(projectDto);
        assertEquals(200, response.statusCode(), "Project should be created successfully");
        return response.as(CreateProjectResponseDto.class);
    }

    private String createAndThenDeleteTestProject() {
        var targetProjectId = TestDataCreator.createRandomProjectAndGetId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        assertEquals(200, deleteProjectResponse.statusCode(), "Project should be deleted successfully");
        return targetProjectId;
    }
}
