package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.models.TestDataFactory;
import com.freedcamp.testdata.CreatedProject;
import common.annotations.RequiresProject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.freedcamp.assertions.GroupAssertions.verifyUserAddedToGroupResponse;

public class UsersTests extends BaseApiTest {

    private static GroupController groupController;

    @BeforeAll
    public static void setup() {
        groupController = new GroupController();
    }

    @Test
    @Tag("Smoke")
    @RequiresProject
    @DisplayName("Verify adding user to the project group.")
    void verifyAddingUserToTheProjectGroup(CreatedProject createdProject) {
        var addUserToGroupDto = TestDataFactory.addUserToGroupDto();
        var addUserResponse = groupController.addUserToProjectGroup(createdProject.createdProjectResponseDto().getData().getGroups().get(0).getGroupId(),
                addUserToGroupDto);
        verifyUserAddedToGroupResponse(addUserResponse, addUserToGroupDto);
    }
}