package com.freedcamp.testdata;

import com.freedcamp.api.models.tasks.TaskDto;

/**
 * Represents created task data for testing (ID + original payload).
 */
public record CreatedTask(String taskId, TaskDto originalDto) {}