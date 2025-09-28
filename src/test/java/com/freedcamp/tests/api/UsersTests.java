package com.freedcamp.tests.api;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.models.TestDataFactory;
import common.annotations.RequiresProjectGroup;
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
    @RequiresProjectGroup
    @DisplayName("Verify adding user to the project group.")
    void verifyAddingUserToTheProjectGroup(String groupId) {
        var targetEmail = faker.internet().safeEmailAddress();
        var addUserToGroupDto = TestDataFactory.addUserToGroupDto(targetEmail);

        var addUserResponse = groupController.addUserToProjectGroup(groupId, addUserToGroupDto);
        verifyUserAddedToGroupResponse(addUserResponse, addUserToGroupDto);
    }
}