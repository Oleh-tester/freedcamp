package com.freedcamp.api.helpers;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.models.TestDataFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestDataCreator {

    static ProjectController projectController = new ProjectController();

    public static String createRandomProjectAndGetId() {
        var projectDto = TestDataFactory.validProjectDto();
        var createProjectResponse = projectController.createProject(projectDto);
        assertThat(createProjectResponse.getStatusCode()).isEqualTo(200);
         return createProjectResponse.path("data.projects[0].id").toString();
    }
}
