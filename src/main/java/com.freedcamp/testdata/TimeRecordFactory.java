package com.freedcamp.testdata;

import net.datafaker.Faker;

public class TimeRecordFactory {
    static Faker faker = new Faker();

    public record TimeRecord(String description, int duration, String status, String project) {}

    public static TimeRecord validCompleted(String projectName) {
        return new TimeRecord("Worked on Task" + faker.number().digits(4), faker.number().numberBetween(0,12),
                "Completed", projectName);
    }
}