package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresProject;
import common.annotations.RequiresTask;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
public class TasksTests extends BaseUiTest {

    @Test
    @RequiresProject
    @Tag("Smoke")
    @Description("Verify creating a new task.")
    void createNewTask(CreatedProject project) {
        var projectName = project.originalDto().getProjectName();
        var taskName = "New Task" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .verifyTasksPageIsLoaded()
                .clickAddTaskButtonInHeader()
                .openProjectDropdown()
                .selectProjectFromDropdown(projectName)
                .enterTaskName(taskName)
                .clickQuickAddTaskButton()
                .verifyTaskIsDisplayedInTasksPage(taskName);
    }

    @Test
    @RequiresTask
    @Tag("Smoke")
    @Description("Verify editing task.")
    void editTask(CreatedTask createdTask) {
        var taskToEdit = createdTask.originalDto().getTitle();
        var newStatus = "In Progress";
        var newDescription = "Updated description for the task";
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .openEditTaskDrawerFormByTaskName(taskToEdit)
                .verifyEditTaskDrawerIsOpened()
                .changeStatusTo(newStatus)
                .verifyNewStatusIsDisplayed(newStatus)
                .changeDescription(newDescription)
                .verifyDescriptionIsUpdated(newDescription);
    }

    @Test
    @RequiresTask
    @Description("Verify deleting task.")
    void deleteTask(CreatedTask createdTask) {
        var taskToDelete = createdTask.originalDto().getTitle();
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .openEditTaskDrawerFormByTaskName(taskToDelete)
                .openMoreActionsMenu()
                .clickDeleteTaskButton()
                .confirmDeleteTask()
                .verifyTaskIsNotDisplayedInTasksPage(taskToDelete);
    }
}