package com.freedcamp.api.models;

import com.freedcamp.api.controllers.ListController;
import com.freedcamp.api.models.projects.CreateProjectDto;
import com.freedcamp.api.models.tasks.CreateTaskDto;
import com.freedcamp.api.models.tasks.TaskConditions;
import com.freedcamp.utils.FreedcampConfig;
import net.datafaker.Faker;
import org.aeonbits.owner.ConfigFactory;

import java.util.UUID;

public class TestDataFactory {

    static final Faker faker = new Faker();
    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final ListController listController = new ListController();


    public static CreateProjectDto validProjectDto() {
        return CreateProjectDto.builder()
                .projectName("PromoProject_" + UUID.randomUUID())
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex())
                .todoViewType("default")
                .usageType("personal")
                .groupId(CONFIG.testGroupId())
                .build();
    }

    public static CreateTaskDto validTaskDto(String targetProjectId) {
        var listId = listController.getAllListsByProject(targetProjectId)
                .path("data.lists[0].id").toString();
        return CreateTaskDto.builder()
                .title("Task_" + UUID.randomUUID())
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
}