package com.freedcamp.steps;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.testdata.utils.TestDataFactory;
import com.freedcamp.utils.ProjectCreationWaiter;

import static org.assertj.core.api.Assertions.assertThat;

public class E2eSteps {

    private final ProjectController projectController = new ProjectController();
    private final GroupController groupController = new GroupController();
    private final ProjectCreationWaiter projectWaiter = new ProjectCreationWaiter(projectController);

    public void waitUntilProjectFromTemplateIsCreated(String name) {
        projectWaiter.waitUntilProjectAppears(name);
    }

    public void inviteUserToProject(String groupId, String targetEmail) {
        var inviteDto = TestDataFactory.addUserToGroupDto(targetEmail);
        var inviteResponse = groupController.addUserToProjectGroup(groupId, inviteDto);
        assertThat(inviteResponse.statusCode()).as("Invite user response code").isEqualTo(200);
    }
}