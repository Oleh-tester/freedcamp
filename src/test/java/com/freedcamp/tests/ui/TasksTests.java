package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedTask;
import common.annotations.RequiresTask;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TasksTests extends BaseUiTest {

    @Test
    @Tag("Smoke")
    @Tag("UI")
    @Description("Verify creating a new task.")
    void createNewTask() {
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .verifyTasksPageIsLoaded()
                .clickAddTaskButtonInTasksList()
                .enterTaskName("New Task")
                .submitTaskCreation()
                .verifyTaskIsDisplayedInTasksPage("New Task");
    }

    @Test
    @RequiresTask
    @Tag("Smoke")
    @Tag("UI")
    @Description("Verify editing task.")
    void editTask(CreatedTask createdTask) {
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .openEditTaskDrawerForm(createdTask.originalDto().getTitle())
                .verifyEditTaskDrawerIsOpened()
                .changeStatusTo("In Progress")
                .verifyNewStatusIsDisplayed("In Progress")
                .changeDescription("Updated description for the task")
                .verifyDescriptionIsUpdated("Updated description for the task");
    }

    @Test
    @RequiresTask
    @Tag("UI")
    @Description("Verify deleting task.")
    void deleteTask(CreatedTask createdTask) {
        var taskToDelete = createdTask.originalDto().getTitle();
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .openEditTaskDrawerForm(taskToDelete)
                .openMoreActionsMenu()
                .clickDeleteTaskButton()
                .confirmDeleteTask()
                .verifyTaskIsNotDisplayedInTasksPage(taskToDelete);
    }
}