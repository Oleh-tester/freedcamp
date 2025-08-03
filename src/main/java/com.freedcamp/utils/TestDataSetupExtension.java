package com.freedcamp.utils;

import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresProject;
import common.annotations.RequiresProjectFromTemplate;
import common.annotations.RequiresTask;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.lang.reflect.Method;

public class TestDataSetupExtension implements BeforeEachCallback, ParameterResolver {

    private static final Namespace NAMESPACE = Namespace.create(TestDataSetupExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        var projectController = new ProjectController();
        var taskController = new TaskController();
        TestDataProvider testDataProvider = new TestDataProvider(
                projectController,
                taskController,
                new ProjectCreationWaiter(projectController)
        );

        Method testMethod = context.getRequiredTestMethod();
        ExtensionContext.Store store = context.getStore(NAMESPACE);

        if (testMethod.isAnnotationPresent(RequiresProject.class)) {
            var createdProject = testDataProvider.createProject();
            store.put("createdProject", createdProject);
        }

        if (testMethod.isAnnotationPresent(RequiresProjectFromTemplate.class)) {
            var createdProject = testDataProvider.createdProjectFromTemplate();
            store.put("createdProjectFromTemplate", createdProject);
        }

        if (testMethod.isAnnotationPresent(RequiresTask.class)) {
            var createdProject = store.get("createdProject", CreatedProject.class);
            if (createdProject == null) {
                createdProject = testDataProvider.createProject();
                store.put("createdProject", createdProject);
            }

            var projectId = createdProject.createdProjectResponseDto().getData()
                    .getProjects().get(0).getId();

            var createdTask = testDataProvider.createTask(projectId);
            store.put("createdTask", createdTask);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
        Class<?> type = paramCtx.getParameter().getType();
        return type == CreatedTask.class ||
                type == CreatedProject.class || type == CreatedProjectFromTemplate.class;
    }

    @Override
    public Object resolveParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
        Class<?> type = paramCtx.getParameter().getType();
        ExtensionContext.Store store = extCtx.getStore(NAMESPACE);

        if (type == CreatedTask.class) {
            return store.get("createdTask", CreatedTask.class);
        }

        if (type == CreatedProject.class) {
            return store.get("createdProject", CreatedProject.class);
        }

        if (type == CreatedProjectFromTemplate.class) {
            return store.get("createdProjectFromTemplate", CreatedProjectFromTemplate.class);
        }

        throw new ParameterResolutionException("Unsupported type: " + type);
    }
}