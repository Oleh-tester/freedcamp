package com.freedcamp.api.models;

import com.freedcamp.api.controllers.ListController;
import com.freedcamp.api.models.calendarEvents.CreateCalendarEventDto;
import com.freedcamp.api.models.groups.CreateGroupDto;
import com.freedcamp.api.models.projects.AddUserToGroupDto;
import com.freedcamp.api.models.projects.CreateProjectDto;
import com.freedcamp.api.models.projects.CreateProjectFromTemplateDto;
import com.freedcamp.api.models.projects.UsersItem;
import com.freedcamp.api.models.projects.updateProject.UpdateProjectDto;
import com.freedcamp.api.models.tasks.TaskDto;
import com.freedcamp.api.models.tasks.TaskConditions;
import com.freedcamp.utils.FreedcampConfig;
import net.datafaker.Faker;
import org.aeonbits.owner.ConfigFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestDataFactory {

    static final Faker faker = new Faker();
    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final ListController listController = new ListController();

    public static CreateProjectDto validProjectDto() {
        return CreateProjectDto.builder()
                .projectName("Promo_" + faker.company().name() + "_" + faker.number().digits(4))
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex())
                .todoViewType("default")
                .usageType("personal")
                .groupId(CONFIG.testGroupId())
                .build();
    }

    public static CreateProjectDto validProjectDtoWithGroup(String groupId) {
        var projectDto = validProjectDto();
        projectDto.setGroupId(groupId);
        return projectDto;
    }

    public static CreateProjectFromTemplateDto validProjectFromTemplateDto() {
        return CreateProjectFromTemplateDto.builder()
                .projectName("Promo_" + faker.company().name() + "_" + faker.number().digits(4))
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex())
                .appIds(List.of("2", "3", "4", "5", "19", "6"))
                .groupId(CONFIG.testGroupId())
                .srcTemplateId(CONFIG.projectTemplateId())
                .build();
    }

    public static UpdateProjectDto updateProjectDto(String groupId) {
        return UpdateProjectDto.builder()
                .projectName("Promo_" + faker.company().name() + "_" + faker.number().digits(4))
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex())
                .groupId(groupId)
                .build();
    }

    public static TaskDto validTaskDto(String targetProjectId) {
        var listId = listController.getAllListsByProject(targetProjectId)
                .path("data.lists[0].id").toString();
        return TaskDto.builder()
                .title("Task_" + faker.number().digits(4))
                .description(faker.lorem().sentence(5))
                .taskGroupId(listId)
                .projectId(targetProjectId)
                .assignedToId(CONFIG.testUserId())
                .timeTo("00:00")
                .timeFrom("00:00")
                .status(0)
                .priority(faker.number().numberBetween(0, 3))
                .rRule("")
                .fTop(false)
                .conditions(TaskConditions.builder()
                        .filter(null)
                        .fUseAnd("0")
                        .substring("")
                        .order(null)
                        .build())
                .build();
    }

    public static TaskDto updateTaskDto() {
        return TaskDto.builder()
                .title("Updated Task_" + faker.number().digits(4))
                .description(faker.lorem().sentence(5))
                .priority(faker.number().numberBetween(0, 3))
                .conditions(TaskConditions.builder()
                        .filter(null)
                        .fUseAnd("0")
                        .substring("")
                        .order(null)
                        .build())
                .build();
    }

    public static TaskDto getInvalidUpdateDto() {
        var validTaskDto = updateTaskDto();
        validTaskDto.setProjectId("invalid_project_id");
        validTaskDto.setAssignedToId("invalid_user_id");
        validTaskDto.setStatus(999);
        validTaskDto.setTitle("");
        return validTaskDto;
    }

    public static CreateCalendarEventDto validCalendarEventDto(String projectId) {
        var dateFrom = LocalDate.now();
        var dateTo = dateFrom.plusDays(20);
        return CreateCalendarEventDto
                .builder()
                .title("Event_" + faker.number().digits(4))
                .projectId(projectId)
                .dateStart(dateFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .dateEnd(dateTo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .timeFrom("00:00")
                .timeTo("00:00")
                .build();
    }

    public static CreateGroupDto validCreateGroupDto() {
        return CreateGroupDto.builder()
                .name("Group_" + faker.company().name() + "_" + faker.number().digits(4))
                .description(faker.lorem().sentence(5))
                .fSms(true)
                .build();
    }

    public static AddUserToGroupDto addUserToGroupDto(String email) {
        return AddUserToGroupDto.builder()
                .invitedGlobalTeams(List.of())
                .users(List.of(
                        UsersItem.builder()
                                .email(email)
                                .name("User_" + faker.name().firstName() + "_" + faker.number().digits(4))
                                .contextRoleId("-1")
                                .build())).build();
    }
}