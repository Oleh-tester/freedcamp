package com.freedcamp.utils;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;
import com.freedcamp.api.models.projects.getAllProjects.GetAllProjectsDto;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.CreatedTask;
import io.restassured.response.Response;

import static com.freedcamp.api.models.TestDataFactory.validTaskDto;
import static org.assertj.core.api.Assertions.assertThat;


public class TestDataProvider {

    private final ProjectController projectController;
    private final TaskController taskController;
    private final ProjectCreationWaiter projectCreationWaiter;

    public TestDataProvider(ProjectController projectController, TaskController taskController, ProjectCreationWaiter projectCreationWaiter) {
        this.projectController = projectController;
        this.taskController = taskController;
        this.projectCreationWaiter = projectCreationWaiter;
    }
    /**
     * Creates a project and returns the created project and original DTO for later use.
     */
    public CreatedProject createProject() {
        var projectDto = TestDataFactory.validProjectDto();

        Response response = projectController.createProject(projectDto);
        assertThat(response.statusCode()).isEqualTo(200);

        return new CreatedProject(
                response.as(CreateProjectResponseDto.class),
                projectDto);
    }

    /**
     * Creates a project and a task inside it. Returns task ID and original DTO for later use.
     */
    public CreatedTask createTask(String projectId) {
        var taskDto = validTaskDto(projectId);
        var response = taskController.createTask(taskDto);

        assertThat(response.statusCode()).isEqualTo(200);

        var taskId = response.as(CreateTaskResponseDto.class)
                .getData().getTasks().get(0).getId();

        return new CreatedTask(taskId, taskDto);
    }

    /**
     * Creates a project from a template and returns the created project and original DTO for later use.
     */
    public CreatedProjectFromTemplate createdProjectFromTemplate() {
        var projectDto = TestDataFactory.validProjectFromTemplateDto();

        var response = projectController.createProjectFromTemplate(projectDto);
        assertThat(response.statusCode()).isEqualTo(200);

        projectCreationWaiter.waitUntilProjectAppears(projectDto.getProjectName());

        var createdProjectDto = projectController.getAllProjects()
                .as(GetAllProjectsDto.class)
                .getData().getProjects().stream()
                .filter(project -> project.getProjectName().equals(projectDto.getProjectName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Created project not found"));

        return new CreatedProjectFromTemplate(createdProjectDto, projectDto);
    }

    /**
     * Deletes the created project by ID.
     */
    public void deleteCreatedProject(String projectId) {
        Response response = projectController.deleteProject(projectId);
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
