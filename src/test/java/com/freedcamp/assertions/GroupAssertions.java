package com.freedcamp.assertions;

import com.freedcamp.api.models.groups.responses.AddUserToGroupResponseDto;
import com.freedcamp.api.models.projects.AddUserToGroupDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupAssertions {
    @Step("Verify user added to group response")
    public static  void verifyUserAddedToGroupResponse(Response response, AddUserToGroupDto addUserToGroupDto) {
        response.then().statusCode(200);
        var responseData = response.as(AddUserToGroupResponseDto.class);
        assertThat(responseData.getData().getGroups().get(0).getMsData().getUserIds().size()).isEqualTo(2);
        assertThat(responseData.getData().getGroups().get(0).getMsData().getPendingUserIds().size()).isEqualTo(1);
        assertThat(responseData.getData().getUsers().size()).isEqualTo(2); //creator + added user
        assertThat(responseData.getData().getUsers().get(1).getUserId()).isNotNull();
        assertThat(responseData.getData().getUsers().get(1).getEmail()).isEqualTo(addUserToGroupDto.getUsers().get(0).getEmail());
        assertThat(responseData.getData().getUsers().get(1).getFullName()).isEqualTo(addUserToGroupDto.getUsers().get(0).getName());
    }
}