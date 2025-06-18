package com.freedcamp.assertions;

import com.freedcamp.api.models.CreateProjectDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectAssertions {
    @Step("Verify project creation response.")
    public static void verifyProjectCreatedCorrectly(Response response, CreateProjectDto expectedData) {
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.path("msg"));
        assertEquals(expectedData.getProjectName(), response.path("data.projects[0].project_name"));
        assertEquals(expectedData.getGroupId(), response.path("data.projects[0].group_id"));
        assertTrue((Boolean) response.path("data.projects[0].f_active"));
    }
}