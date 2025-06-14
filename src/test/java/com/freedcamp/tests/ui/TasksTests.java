package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

public class TasksTests extends BaseUiTest {

    @Test
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
    @Description("Verify editing task.")
    void editTask() {
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTasksPage()
                .createNewTask("Task to Edit")
                .openEditTaskDrawerForm("Task to Edit")
                .verifyEditTaskDrawerIsOpened()
                .changeStatusTo("In Progress")
                .verifyNewStatusIsDisplayed("In Progress")
                .changeDescription("Updated description for the task")
                .verifyDescriptionIsUpdated("Updated description for the task");
    }
}
