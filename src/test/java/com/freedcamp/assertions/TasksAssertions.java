package com.freedcamp.assertions;

import com.freedcamp.api.models.tasks.CreateTaskDto;
import com.freedcamp.api.models.tasks.CreateTaskResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TasksAssertions {

    @Step("Verify task creation response.")
    public static void verifyTaskCreatedCorrectly(Response response, CreateTaskDto targetTask) {
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
}
