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
        waitUntilVisible(headerMenu.$x(".//button[div[text()='Add Task']]"));
        return this;
    }

    @Step("Click Add Task button in Tasks list.")
    public void clickAddTaskButtonInTasksList() {
        tasksSection.$x(".//button").click();
    }

    @Step("Click Add Task button in header")
    public TasksPage clickAddTaskButtonInHeader() {
        headerMenu.$x(".//*[text()='Add Task']").click();
        return this;
    }

    @Step("Verify task {taskName} is displayed in Tasks page")
    public void verifyTaskIsDisplayedInTasksPage(String taskName) {
        tasksSection.$x(".//*[text()='" + taskName + "']").shouldBe(Condition.visible);
    }

    @Step("Enter task name: {newTask}")
    public TasksPage enterTaskName(String newTask) {
        $x(".//input[not(@type='hidden')]").shouldBe(Condition.visible).setValue(newTask);
        return this;
    }

    @Step("Submit task creation")
    public void submitTaskCreation() {
        tasksSection.$x(".//input[not(@type='hidden')]").pressEnter();
    }

    @Step("Click Quick Add button in Tasks section")
    public TasksPage clickQuickAddTaskButton() {
        $x("//button[.='Quick Add']").click();
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
    public EditTaskDrawerForm openEditTaskDrawerFormByTaskName(String taskToEdit) {
        $x("//*[text()='" + taskToEdit + "']").click();
        return new EditTaskDrawerForm();
    }

    @Step("Verify task {deletedTask} is not displayed in Tasks page")
    public void verifyTaskIsNotDisplayedInTasksPage(String deletedTask) {
        tasksSection.$x(".//*[text()='" + deletedTask + "']").shouldNotBe(Condition.visible);
    }

    @Step("Select project from dropdown: {targetProject}")
    public TasksPage selectProjectFromDropdown(String targetProject) {
        $x("//*[contains(@class,'SelectOption')]/*[text()='" + targetProject + "']").click();
        return this;
    }

    @Step("Open Project dropdown")
    public TasksPage openProjectDropdown() {
        if (!$x("//div[contains(@class,'SelectOption')]").isDisplayed()) {
            $x("//div[label[contains(text(), 'Project')]]//*[contains(@class,'DropdownArrow')]").
                    shouldBe(Condition.interactable).click();
        }        return this;
    }
}
