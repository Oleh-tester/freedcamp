package com.freedcamp.api.models;

import net.datafaker.Faker;

import java.util.UUID;

public class TestDataFactory {

    static final Faker faker = new Faker();

    public static CreateProjectDto validProject() {
        return CreateProjectDto.builder()
                .projectName("PromoProject_" + UUID.randomUUID())
                .projectDescription(faker.lorem().sentence(5))
                .projectColor(faker.color().hex().toString())
                .todoViewType("default")
                .usageType("personal")
                .groupId("1445404")
                .build();
    }
}