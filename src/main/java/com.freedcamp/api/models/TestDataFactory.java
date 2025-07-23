package com.freedcamp.api.models;

import com.freedcamp.api.controllers.ListController;
import com.freedcamp.api.models.projects.CreateProjectDto;
import com.freedcamp.api.models.projects.updateProject.UpdateProjectDto;
import com.freedcamp.api.models.tasks.TaskDto;
import com.freedcamp.api.models.tasks.TaskConditions;
import com.freedcamp.utils.FreedcampConfig;
import net.datafaker.Faker;
import org.aeonbits.owner.ConfigFactory;

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

    public static UpdateProjectDto updateProjectDto() {
        return UpdateProjectDto.builder()
                .projectName("Promo_" + faker.company().name() + "_" + faker.number().digits(4))
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex())
                .groupId(CONFIG.testGroupId())
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
}