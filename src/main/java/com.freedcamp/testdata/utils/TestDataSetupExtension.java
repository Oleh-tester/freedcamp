package com.freedcamp.testdata.utils;

import com.freedcamp.api.controllers.GroupController;
import com.freedcamp.api.controllers.ProjectController;
import com.freedcamp.api.controllers.TaskController;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.CreatedTask;
import com.freedcamp.utils.ProjectCreationWaiter;
import common.annotations.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.lang.reflect.Method;

public class TestDataSetupExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private static final Namespace NAMESPACE = Namespace.create(TestDataSetupExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        var projectController = new ProjectController();
        var taskController = new TaskController();
        var groupController = new GroupController();
        TestDataProvider testDataProvider = new TestDataProvider(
                projectController,
                taskController,
                groupController,
                new ProjectCreationWaiter(projectController));

        Method testMethod = context.getRequiredTestMethod();
        ExtensionContext.Store store = context.getStore(NAMESPACE);

        boolean needsGroup = testMethod.isAnnotationPresent(RequiresProject.class)
                || testMethod.isAnnotationPresent(RequiresProjectFromTemplate.class)
                || testMethod.isAnnotationPresent(RequiresTask.class);

        String createdGroupId = null;
        if (needsGroup || testMethod.isAnnotationPresent(RequiresProjectGroup.class)) {
            createdGroupId = testDataProvider.createGroup();
            store.put("createdGroupId", createdGroupId);
        }

        if (testMethod.isAnnotationPresent(RequiresProject.class)) {
            var createdProject = testDataProvider.createProjectInGroup(createdGroupId);
            store.put("createdProject", createdProject);
        }

        if (testMethod.isAnnotationPresent(RequiresProjectFromTemplate.class)) {
            var createdProject = testDataProvider.createdProjectFromTemplateInGroup(createdGroupId);
            store.put("createdProjectFromTemplate", createdProject);
        }

        if (testMethod.isAnnotationPresent(RequiresTask.class)) {
            var createdProject = store.get("createdProject", CreatedProject.class);
            if (createdProject == null) {
                if (createdGroupId == null) {
                    createdGroupId = testDataProvider.createGroup();
                    store.put("createdGroupId", createdGroupId);
                }
                createdProject = testDataProvider.createProjectInGroup(createdGroupId);
                store.put("createdProject", createdProject);
            }

            var projectId = createdProject.createdProjectResponseDto().getData()
                    .getProjects().get(0).getId();

            var createdTask = testDataProvider.createTask(projectId);
            store.put("createdTask", createdTask);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        ExtensionContext.Store store = context.getStore(NAMESPACE);
        Method testMethod = context.getRequiredTestMethod();

        if (testMethod.isAnnotationPresent(DeletesOwnData.class)) {
            return;
        }

        var testDataProvider = new TestDataProvider(
                new ProjectController(),
                new TaskController(),
                new GroupController(),
                new ProjectCreationWaiter(new ProjectController())
        );

        if (testMethod.isAnnotationPresent(RequiresProject.class) || testMethod.isAnnotationPresent(RequiresTask.class)
                || testMethod.isAnnotationPresent(RequiresProjectFromTemplate.class) || testMethod.isAnnotationPresent(RequiresProjectGroup.class)) {

            var createdGroupId = store.get("createdGroupId", String.class);
            if (createdGroupId != null) {
                testDataProvider.deleteGroup(createdGroupId);
            }
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
        Class<?> type = paramCtx.getParameter().getType();
        return type == CreatedTask.class ||
                type == CreatedProject.class || type == CreatedProjectFromTemplate.class || type == String.class;
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

        if (type == String.class) {
            return store.get("createdGroupId", String.class);
        }

        throw new ParameterResolutionException("Unsupported type: " + type);
    }
}