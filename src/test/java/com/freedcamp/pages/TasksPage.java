package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.components.EditTaskDrawerForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage extends BasePage {

    private final SelenideElement tasksSection = $x("//*[contains(@class,'NewTasksList')]");


    @Step("Verify Tasks page is loaded")
    public TasksPage verifyTasksPageIsLoaded() {
        waitUntilVisible($x("//*[contains(@class,'AppHeader')]//button[div[text()='Add Task']]"));
        return this;
    }

    @Step("Click Add Task button in Tasks list.")
    public TasksPage clickAddTaskButtonInTasksList() {
        tasksSection.$x(".//button").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Verify task {taskName} is displayed in Tasks page")
    public void verifyTaskIsDisplayedInTasksPage(String taskName) {
        tasksSection.$x(".//*[text()='" + taskName + "']").shouldBe(Condition.visible);
    }

    @Step("Enter task name: {newTask}")
    public TasksPage enterTaskName(String newTask) {
        tasksSection.$x(".//input[not(@type='hidden')]").shouldBe(Condition.visible).setValue(newTask);
        return this;
    }

    @Step("Submit task creation")
    public TasksPage submitTaskCreation() {
        tasksSection.$x(".//input[not(@type='hidden')]").pressEnter();
        return this;
    }

    @Step("Create new task: {newTask}")
    public TasksPage createNewTask(String newTask) {
        clickAddTaskButtonInTasksList();
        enterTaskName(newTask);
        submitTaskCreation();
        verifyTaskIsDisplayedInTasksPage(newTask);
        return this;
    }

    @Step("Open edit task form for task: {taskToEdit}")
    public EditTaskDrawerForm openEditTaskDrawerForm(String taskToEdit) {
        $x("//*[text()='" + taskToEdit + "']").shouldBe(Condition.visible).click();
        return new EditTaskDrawerForm();
    }
}
