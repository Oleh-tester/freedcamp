package com.freedcamp.utils;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.api.models.projects.createProjectResponse.CreateProjectResponseDto;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedTask;
import io.restassured.response.Response;

import static com.freedcamp.api.models.TestDataFactory.validTaskDto;
import static org.assertj.core.api.Assertions.assertThat;


public class TestDataProvider {

    private static final ProjectController projectController = new ProjectController();
    private static final TaskController taskController = new TaskController();

    /**
     * Creates a random project and returns responseDto.
     */
    public static CreatedProject createProject() {
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
    public static CreatedTask createTask(String projectId) {
        var taskDto = validTaskDto(projectId);
        var response = taskController.createTask(taskDto);

        assertThat(response.statusCode()).isEqualTo(200);

        var taskId = response.as(CreateTaskResponseDto.class)
                .getData().getTasks().get(0).getId();

        return new CreatedTask(taskId, taskDto);
    }

}
