package com.freedcamp.assertions;

import com.freedcamp.api.models.tasks.TaskDto;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import com.freedcamp.api.models.tasks.GetTasksResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TasksAssertions {

    @Step("Verify task creation response.")
    public static void verifyTaskCreatedCorrectly(Response response, TaskDto targetTask) {
        var taskResponse = response.as(CreateTaskResponseDto.class);
        assertThat(response.statusCode()).isEqualTo(200);
        assertEquals("OK", taskResponse.getMsg(), "Response message should be OK");
        assertFalse(taskResponse.getData().getTasks().isEmpty(), "Task list should not be empty");
        var createdTask = taskResponse.getData().getTasks().get(0);
        assertEquals(targetTask.getTitle(), createdTask.getTitle(), "Task title should match");
        assertEquals(targetTask.getDescription(), createdTask.getDescription(), "Task description should match");
        assertEquals(targetTask.getProjectId(), createdTask.getProjectId(), "Task project ID should match");
        assertEquals(targetTask.getTaskGroupId(), createdTask.getTaskGroupId(), "Task group ID should match");
        assertEquals(targetTask.getAssignedToId(), createdTask.getAssignedToId(), "Assigned user ID should match");
        assertEquals(targetTask.getPriority(), createdTask.getPriority(), "Task priority should match");
        assertEquals(targetTask.getStatus(), createdTask.getStatus(), "Task status should match");
        assertNotNull(createdTask.getId(), "Created task ID should not be null");
    }

    @Step("Verify tasks update.")
    public static void verifyTaskUpdatedCorrectly(Response response, TaskDto updatedTask) {
        var taskResponse = response.as(CreateTaskResponseDto.class);
        assertThat(response.statusCode()).isEqualTo(200);
        assertEquals("OK", taskResponse.getMsg(), "Response message should be OK");
        assertFalse(taskResponse.getData().getTasks().isEmpty(), "Task list should not be empty");
        var updatedTaskResponse = taskResponse.getData().getTasks().get(0);
        assertEquals(updatedTask.getTitle(), updatedTaskResponse.getTitle(), "Updated task title should match");
        assertEquals(updatedTask.getDescription(), updatedTaskResponse.getDescription(), "Updated task description should match");
        assertEquals(updatedTask.getPriority(), updatedTaskResponse.getPriority(), "Updated task priority should match");
    }

    @Step("Verify task deletion response.")
    public static void verifyTaskDeletionResponse(Response deleteResponse) {
        assertEquals(200, deleteResponse.statusCode());
        assertEquals("OK", deleteResponse.path("msg"));
    }

    @Step("Verify task is not found after deletion.")
    public static void verifyTaskNotFoundInAllTasks(Response response, String taskId) {
        assertThat(response.statusCode()).isEqualTo(200);
        var tasks = response.as(GetTasksResponse.class).getData().getTasks();
        assertFalse(tasks.stream().anyMatch(task -> task.getId().equals(taskId)),
                "Task with ID " + taskId + " should not be found in the list of all tasks");
    }

    @Step("Verify tasks project ID association.")
    public static void verifyTasksProjectIdAssociation(Response response) {
        assertThat(response.statusCode()).isEqualTo(200);
        var tasks = response.as(GetTasksResponse.class).getData().getTasks();
        assertFalse(tasks.isEmpty(), "Task list should not be empty");
        for (var task : tasks) {
            assertNotNull(task.getProjectId(), "Task project ID should not be null");
            assertFalse(task.getProjectId().isEmpty(), "Task project ID should not be empty");
        }
    }

    @Step("Verify get task by ID content.")
    public static void verifyGetTaskByIdContent(Response response, TaskDto expectedTask) {
        assertThat(response.statusCode()).isEqualTo(200);
        var taskResponse = response.as(GetTasksResponse.class);
        assertEquals("OK", taskResponse.getMsg(), "Response message should be OK");
        assertFalse(taskResponse.getData().getTasks().isEmpty(), "Task list should not be empty");
        var fetchedTask = taskResponse.getData().getTasks().get(0);
        assertEquals(expectedTask.getTitle(), fetchedTask.getTitle(), "Fetched task title should match");
        assertEquals(expectedTask.getDescription(), fetchedTask.getDescription(), "Fetched task description should match");
        assertEquals(expectedTask.getProjectId(), fetchedTask.getProjectId(), "Fetched task project ID should match");
        assertEquals(expectedTask.getAssignedToId(), fetchedTask.getAssignedToId(), "Fetched task assigned user ID should match");
        assertEquals(expectedTask.getTaskGroupId(), fetchedTask.getTaskGroupId(), "Fetched task group ID should match");
        assertEquals(expectedTask.getPriority(), fetchedTask.getPriority(), "Fetched task priority should match");
        assertEquals(expectedTask.getStatus(), fetchedTask.getStatus(), "Fetched task status should match");
        assertNotNull(fetchedTask.getId(), "Fetched task ID should not be null");
    }

    @Step("Verify task is updated ignoring invalid values.")
    public static void verifyTaskIsUpdatedIgnoringInvalidValues(Response updateResponse, TaskDto originalTask) {
        assertThat(updateResponse.statusCode()).isEqualTo(200);
        var taskResponse = updateResponse.as(CreateTaskResponseDto.class);
        assertEquals("OK", taskResponse.getMsg(), "Response message should be OK");
        assertFalse(taskResponse.getData().getTasks().isEmpty(), "Task list should not be empty");
        var updatedTask = taskResponse.getData().getTasks().get(0);
        assertEquals(originalTask.getTitle(), updatedTask.getTitle(), "Updated task title should match");
        assertEquals(originalTask.getProjectId(), updatedTask.getProjectId(), "Updated task project ID should match");
        assertEquals(originalTask.getStatus(), updatedTask.getStatus(), "Updated task status should match");
        assertEquals(updatedTask.getAssignedToId(), "0", "Assigned user ID should be reset to 0");
    }
}
