package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.utils.TestDataSetupExtension;
import common.annotations.RequiresProject;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.freedcamp.assertions.ProjectAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({AllureJunit5.class, TestDataSetupExtension.class})
public class ProjectTests extends BaseApiTest {
    private static ProjectController projectController;

    @BeforeAll
    public static void setup() {
        projectController = new ProjectController();
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Verify create project and return correct response")
    void shouldCreateProjectSuccessfully() {
        var projectDto = TestDataFactory.validProjectDto();

        var createProjectResponse = projectController.createProject(projectDto);
        verifyProjectCreatedCorrectly(createProjectResponse, projectDto);
    }

    @Test
    @DisplayName("Verify project is displayed in all projects after creation")
    @RequiresProject
    void shouldDisplayProjectInAllProjectsAfterCreation(CreatedProject createdProject) {

        var getAllProjectsResponse = projectController.getAllProjects();
        verifyProjectDisplayedInAllProjects(getAllProjectsResponse, createdProject.createdProjectResponseDto());
    }

    @Test
    @DisplayName("Verify deleting a project")
    @RequiresProject
    void shouldDeleteProjectSuccessfully(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        verifyProjectDeletedResponse(deleteProjectResponse);
    }

    @Test
    @DisplayName("Verify project is deleted and not found after deletion")
    @RequiresProject
    void shouldNotFindDeletedProject(CreatedProject createdProject) {
        var targetProjectId = createAndThenDeleteTestProject(createdProject);

        var getProjectResponse = projectController.getProjectById(targetProjectId);
        assertEquals(400, getProjectResponse.statusCode(), "Project should be deleted and not found");
    }

    @Test
    @DisplayName("Verify update project and validate response")
    @RequiresProject
    void verifyUpdateProjectAndValidateResponse(CreatedProject createdProject) {
        var testProject = createdProject.createdProjectResponseDto().getData().getProjects().get(0);
        var updatedProjectDto = TestDataFactory.updateProjectDto();

        var updateProjectResponse = projectController.updateProject(updatedProjectDto,
                testProject.getProjectId());
        verifyProjectUpdatedCorrectly(updateProjectResponse, updatedProjectDto);
    }

    private String createAndThenDeleteTestProject(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        assertEquals(200, deleteProjectResponse.statusCode(), "Project should be deleted successfully");
        return targetProjectId;
    }
}
