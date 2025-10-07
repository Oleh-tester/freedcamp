package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.testdata.TestDataFactory;
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
    @Tag("smoke")
    @RequiresProject
    @DisplayName("Verify adding user to the project group.")
    void verifyAddingUserToTheProjectGroup(String groupId) {
        var targetEmail = faker.internet().safeEmailAddress();
        var addUserToGroupDto = TestDataFactory.addUserToGroupDto(targetEmail);

        var addUserResponse = groupController.addUserToProjectGroup(groupId, addUserToGroupDto);
        verifyUserAddedToGroupResponse(addUserResponse, addUserToGroupDto);
    }
}