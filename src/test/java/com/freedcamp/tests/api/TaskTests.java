package com.freedcamp.tests.api;

import com.freedcamp.api.RequestSpecFactory;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.api.helpers.TestDataCreator;
import com.freedcamp.api.models.TestDataFactory;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.freedcamp.assertions.TasksAssertions.verifyTaskCreatedCorrectly;
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
}