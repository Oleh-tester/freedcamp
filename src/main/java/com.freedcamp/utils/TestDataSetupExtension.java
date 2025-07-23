package com.freedcamp.utils;

import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresProject;
import common.annotations.RequiresTask;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class TestDataSetupExtension implements BeforeEachCallback, ParameterResolver {

    private CreatedTask createdTask;
    private CreatedProject createdProject;

    @Override
    public void beforeEach(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();

        if (testMethod.isAnnotationPresent(RequiresProject.class)) {
            createdProject = TestDataProvider.createProject();
        }

        if (testMethod.isAnnotationPresent(RequiresTask.class)) {
            if (createdProject == null) {
                createdProject = TestDataProvider.createProject();
            }
            var projectId = createdProject.createdProjectResponseDto().getData()
                    .getProjects().get(0).getId();
            createdTask = TestDataProvider.createTask(projectId);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
        Class<?> type = paramCtx.getParameter().getType();
        return type == CreatedTask.class ||
                type == CreatedProject.class;
    }

    @Override
    public Object resolveParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
        Class<?> type = paramCtx.getParameter().getType();

        if (type == CreatedTask.class) return createdTask;
        if (type == CreatedProject.class) return createdProject;

        throw new ParameterResolutionException("Unsupported type: " + type);
    }
}