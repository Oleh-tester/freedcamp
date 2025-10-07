package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresProject;
import common.annotations.RequiresTask;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TasksTests extends BaseUiTest {

    @Test
    @RequiresProject
    @Tag("smoke")
    @Tag("ui")
    @Description("Verify creating a new task.")
    void createNewTask() {
        var taskName = "New Task" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .verifyTasksPageIsLoaded()
                .clickAddTaskButtonInTasksList()
                .enterTaskName(taskName)
                .submitTaskCreation()
                .verifyTaskIsDisplayedInTasksPage(taskName);
    }

    @Test
    @RequiresTask
    @Tag("smoke")
    @Tag("ui")
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
    @Tag("ui")
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