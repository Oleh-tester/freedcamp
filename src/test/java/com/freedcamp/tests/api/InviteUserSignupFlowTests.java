package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.RegistrationController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.api.models.projects.getAllProjects.GetAllProjectsDto;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.TestDataSetupExtension;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import common.annotations.RequiresProject;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestDataSetupExtension.class)
@Epic("User Management")
@Feature("Invitation and Signup")
public class InviteUserSignupFlowTests {

    private final GroupController groupController = new GroupController();
    private final RegistrationController registrationController = new RegistrationController();
    private final ProjectController projectController = new ProjectController();

    @Test
    @Tag("Invite")
    @RequiresProject
    @DisplayName("Invite user -> signup -> complete account settings -> verify group project visible")
    void inviteSignupAndSeeProjects(CreatedProject createdProject, String createdGroup) {
        var projectName = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getProjectName();
        var groupId = createdProject.createdProjectResponseDto().getData().getGroups().get(0).getGroupId();

        // Step 1: Invite user to group (project association via group membership)
        var inviteDto = TestDataFactory.addUserToGroupDto();
        var inviteResponse = groupController.addUserToProjectGroup(groupId, inviteDto);
        assertThat(inviteResponse.statusCode()).as("Invite user response code").isEqualTo(200);

        // Step 2: User signs up via public registration endpoint capturing session
        var sessionFilter = new io.restassured.filter.session.SessionFilter();
        var registerResponse = registrationController.registerEmail(inviteDto.getUsers().get(0).getEmail(), sessionFilter);
        assertThat(registerResponse.statusCode()).as("Register email response").isEqualTo(200);

        // Step 3: Complete account settings
        var userSession = registrationController.completeAccountSettings("Test User", "Test123.", "Europe/Athens", sessionFilter);
        // Optionally assert session contains ci_session cookie
        assertThat(userSession.cookies()).containsKey("ci_session");

        // Step 4: Fetch projects as the newly registered user
        var projectsResponse = registrationController.getProjects(userSession);
        assertThat(projectsResponse.statusCode()).as("Fetch projects response").isEqualTo(200);

        var projectsDto = projectsResponse.as(GetAllProjectsDto.class);
        var projectNames = projectsDto.getData().getProjects().stream().map(p -> p.getProjectName()).toList();

        // Assert: Invited project's name is present
        assertThat(projectNames)
                .as("Invited user's visible projects should contain the original project")
                .contains(projectName);
    }
}
