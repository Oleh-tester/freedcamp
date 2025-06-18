package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.assertions.ProjectAssertions;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllureJunit5.class)
public class ProjectTests extends BaseApiTest{
    private static ProjectController projectController;

    @BeforeAll
    public static void setup() {
        projectController = new ProjectController();
    }

    @Test
    @DisplayName("Should return 200 when getting all projects")
    public void testGetAllProjects() {
        Response response = projectController.getAllProjects();
        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Should return 200 ")
    void shouldCreateProjectSuccessfully() {
        var request = TestDataFactory.validProject();
        Response response = projectController.createProject(request);

        ProjectAssertions.verifyProjectCreatedCorrectly(response, request);
    }

}
