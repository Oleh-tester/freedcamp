package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.testdata.TestDataSetupExtension;
import common.annotations.DeletesOwnData;
import common.annotations.RequiresProject;
import common.annotations.RequiresProjectGroup;
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
    @RequiresProjectGroup
    @Tag("Smoke")
    @DisplayName("Verify create project and return correct response")
    void shouldCreateProjectSuccessfully(String createdGroupId) {
        var projectDto = TestDataFactory.validProjectDtoWithGroup(createdGroupId);

        var createProjectResponse = projectController.createProject(projectDto);
        verifyProjectCreatedCorrectly(createProjectResponse, projectDto);
    }

    @Test
    @RequiresProject
    @DisplayName("Verify project is displayed in all projects after creation")
    void shouldDisplayProjectInAllProjectsAfterCreation(CreatedProject createdProject) {

        var getAllProjectsResponse = projectController.getAllProjects();
        verifyProjectDisplayedInAllProjects(getAllProjectsResponse, createdProject.createdProjectResponseDto());
    }

    @Test
    @RequiresProject
    @DeletesOwnData
    @DisplayName("Verify deleting a project")
    void shouldDeleteProjectSuccessfully(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        verifyProjectDeletedResponse(deleteProjectResponse);
    }

    @Test
    @DeletesOwnData
    @RequiresProject
    @DisplayName("Verify project is deleted and not found after deletion")
    void shouldNotFindDeletedProject(CreatedProject createdProject) {
        var targetProjectId = deleteTestProject(createdProject);

        var getProjectResponse = projectController.getProjectById(targetProjectId);
        assertEquals(400, getProjectResponse.statusCode(), "Project should be deleted and not found");
    }

    @Test
    @RequiresProject
    @DisplayName("Verify update project and validate response")
    void verifyUpdateProjectAndValidateResponse(CreatedProject createdProject) {
        var testProject = createdProject.createdProjectResponseDto().getData().getProjects().get(0);
        var updatedProjectDto = TestDataFactory.updateProjectDto(testProject.getGroupId());

        var updateProjectResponse = projectController.updateProject(updatedProjectDto, testProject.getProjectId());
        verifyProjectUpdatedCorrectly(updateProjectResponse, updatedProjectDto);
    }

    private String deleteTestProject(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();

        var deleteProjectResponse = projectController.deleteProject(targetProjectId);
        assertEquals(200, deleteProjectResponse.statusCode(), "Project should be deleted successfully");
        return targetProjectId;
    }
}
