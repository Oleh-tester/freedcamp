package com.freedcamp.utils;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.models.projects.getAllProjects.GetAllProjectsDto;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.core.ConditionTimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
@Slf4j
public class ProjectCreationWaiter {

    private final ProjectController projectController;

    public ProjectCreationWaiter(ProjectController projectController) {
        this.projectController = projectController;
    }

    public void waitUntilProjectAppears(String projectName) {
        try {
            await()
                    .pollDelay(33, SECONDS)
                    .atMost(99, SECONDS)
                    .pollInterval(3, SECONDS)
                    .ignoreExceptions()
                    .until(() -> isProjectVisible(projectName));

            log.info("Project '{}' successfully created and visible.", projectName);
        } catch (ConditionTimeoutException e) {
            log.error("Project '{}' was NOT created within timeout.", projectName, e);
            throw e;
        }
    }

    private boolean isProjectVisible(String projectName) {
        var allProjectsResponse = projectController.getAllProjects();
        assertThat(allProjectsResponse.getStatusCode()).isEqualTo(200);
        return allProjectsResponse.as(GetAllProjectsDto.class).getData().getProjects().stream()
                .anyMatch(project -> projectName.equals(project.getProjectName()));
    }
}