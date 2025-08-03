package com.freedcamp.steps;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.utils.ProjectCreationWaiter;

public class E2eSteps {

    private final ProjectController projectController = new ProjectController();
    private final ProjectCreationWaiter projectWaiter = new ProjectCreationWaiter(projectController);

    public void waitUntilProjectFromTemplateIsCreated(String name) {
        projectWaiter.waitUntilProjectAppears(name);
    }
}