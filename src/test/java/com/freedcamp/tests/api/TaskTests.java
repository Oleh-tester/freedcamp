package com.freedcamp.tests.api;

import com.freedcamp.api.RequestSpecFactory;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.api.helpers.TestDataCreator;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.freedcamp.api.models.tasks.TaskDto;

import static com.freedcamp.assertions.TasksAssertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllureJunit5.class)
public class TaskTests extends BaseApiTest {
    private static TaskController taskController;

    @BeforeAll
    public static void setup() {
        taskController = new TaskController();
    }

    @Test
    @DisplayName("Verify create task in a project.")
    void verifyCreateTaskInProject() {
        var targetProjectId = TestDataCreator.createRandomProjectAndGetId();
        var taskDto = TestDataFactory.validTaskDto(targetProjectId);

        var createTaskResponse = taskController.createTask(taskDto);

        verifyTaskCreatedCorrectly(createTaskResponse, taskDto);
    }

    @Test
    @DisplayName("Verify return 400 when title is missing.")
    void verify400whenTitleIsMissing() {
        var targetProjectId = TestDataCreator.createRandomProjectAndGetId();

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
    @DisplayName("Verify task update and response.")
    void verifyTaskUpdate() {
        var targetTask = createProjectAndTask();

        var updateTaskDto = TestDataFactory.updateTaskDto();
        var updateTaskResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        verifyTaskUpdatedCorrectly(updateTaskResponse, updateTaskDto);
    }

    @Test
    @DisplayName("Verify ignore invalid values during task update")
    void verifyTaskUpdateIgnoresInvalidValues() {
        var targetTask = createProjectAndTask();

        var updateTaskDto = TestDataFactory.getInvalidUpdateDto();
        var updateResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        verifyTaskIsUpdatedIgnoringInvalidValues(updateResponse, targetTask.originalDto());
    }

    @Test
    @DisplayName("Verify invalid priority during task update")
    void verifyUpdateTaskInvalidPriority() {
        var targetTask = createProjectAndTask();

        var updateTaskDto = TestDataFactory.updateTaskDto();
        updateTaskDto.setPriority(999);
        var updateResponse = taskController.updateTask(Long.parseLong(targetTask.taskId()), updateTaskDto);

        assertEquals(400, updateResponse.statusCode(), "Should return 400 for invalid priority value");
    }

    @Test
    @DisplayName("Delete task and verify response")
    void deleteTask() {
        var targetTask = createProjectAndTask();

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
    @DisplayName("Verify getting tasks by iD and its content")
    void verifyGetTaskById() {
        var targetTask = createProjectAndTask();

        var getTaskResponse = taskController.getTaskById(Long.parseLong(targetTask.taskId()));

        verifyGetTaskByIdContent(getTaskResponse, targetTask.originalDto());
    }

    private CreatedTask createProjectAndTask() {
        var targetProjectId = TestDataCreator.createRandomProjectAndGetId();
        var taskDto = TestDataFactory.validTaskDto(targetProjectId);
        var createResponse = taskController.createTask(taskDto);

        assertEquals(200, createResponse.statusCode(), "Task should be created successfully");

        var taskId = createResponse.as(CreateTaskResponseDto.class)
                .getData().getTasks().get(0).getId();

        return new CreatedTask(taskId, taskDto);
    }

    public record CreatedTask(String taskId, TaskDto originalDto) {
    }
}