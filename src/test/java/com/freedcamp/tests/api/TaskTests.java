package com.freedcamp.tests.api;

import com.freedcamp.api.helpers.RequestSpecFactory;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresProject;
import common.annotations.RequiresTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.freedcamp.assertions.TasksAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests extends BaseApiTest {
    private static TaskController taskController;

    @BeforeAll
    public static void setup() {
        taskController = new TaskController();
    }

    @Test
    @Tag("Smoke")
    @RequiresProject
    @DisplayName("Verify create task in a project.")
    void verifyCreateTaskInProject(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();
        var taskDto = TestDataFactory.validTaskDto(targetProjectId);

        var createTaskResponse = taskController.createTask(taskDto);

        verifyTaskCreatedCorrectly(createTaskResponse, taskDto);
    }

    @Test
    @RequiresProject
    @DisplayName("Verify return 400 when title is missing.")
    void verify400whenTitleIsMissing(CreatedProject createdProject) {
        var targetProjectId = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getId();

        var taskDtoWithoutTitle = TestDataFactory.validTaskDto(targetProjectId);
        taskDtoWithoutTitle.setTitle(null);
        var responseWithoutTitle = taskController.createTask(taskDtoWithoutTitle);

        assertEquals(400, responseWithoutTitle.statusCode(), "Should return 400 for missing title");
    }

    @Test
    @DisplayName("Verify return 400 when project iD is invalid.")
    void verify400WhenProjectIdIsInvalid() {
        var taskDtoWithInvalidProjectId = TestDataFactory.validTaskDto("invalid_project_id");

        var responseWithInvalidProjectId = taskController.createTask(taskDtoWithInvalidProjectId);

        assertEquals(400, responseWithInvalidProjectId.statusCode(), "Should return 400 for invalid project ID");
    }

    @Test
    @DisplayName("Verify authorization error when get tasks.")
    void verifyAuthorizationErrorWhenGetTasks() {
        var getTasksUnauthorized = taskController.getAllTasks(RequestSpecFactory.getSpecWithoutAuth());

        assertEquals(401, getTasksUnauthorized.statusCode(), "Should return 401 Unauthorized for unauthorized access");
    }

    @Test
    @RequiresTask
    @DisplayName("Verify task update and response.")
    void verifyTaskUpdate(CreatedTask targetTask) {
        var updateTaskDto = TestDataFactory.updateTaskDto();
        var updateTaskResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        verifyTaskUpdatedCorrectly(updateTaskResponse, updateTaskDto);
    }

    @Test
    @RequiresTask
    @DisplayName("Verify ignore invalid values during task update")
    void verifyTaskUpdateIgnoresInvalidValues(CreatedTask targetTask) {
        var updateTaskDto = TestDataFactory.getInvalidUpdateDto();
        var updateResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        verifyTaskIsUpdatedIgnoringInvalidValues(updateResponse, targetTask.originalDto());
    }

    @Test
    @RequiresTask
    @DisplayName("Verify invalid priority during task update")
    void verifyUpdateTaskInvalidPriority(CreatedTask targetTask) {
        var updateTaskDto = TestDataFactory.updateTaskDto();
        updateTaskDto.setPriority(999);
        var updateResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        assertEquals(400, updateResponse.statusCode(), "Should return 400 for invalid priority value");
    }

    @Test
    @RequiresTask
    @DisplayName("Delete task and verify response")
    void deleteTask(CreatedTask targetTask) {
        var deleteResponse = taskController.deleteTask(Long.parseLong(targetTask.taskId()));
        verifyTaskDeletionResponse(deleteResponse);

        var getAllTasksResponse = taskController.getAllTasks();
        verifyTaskNotFoundInAllTasks(getAllTasksResponse, targetTask.taskId());
    }

    @Test
    @DisplayName("Should fetch all tasks and verify each task has project_id")
    void shouldVerifyProjectIdAssociationInAllTasks() {
        var getAllTasksResponse = taskController.getAllTasks();

        verifyTasksProjectIdAssociation(getAllTasksResponse);
    }

    @Test
    @RequiresTask
    @DisplayName("Verify getting tasks by iD and its content")
    void verifyGetTaskById(CreatedTask targetTask) {
        var getTaskResponse = taskController.getTaskById(Long.parseLong(targetTask.taskId()));

        verifyGetTaskByIdContent(getTaskResponse, targetTask.originalDto());
    }
}