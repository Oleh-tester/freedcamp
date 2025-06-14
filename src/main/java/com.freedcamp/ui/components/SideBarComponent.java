package com.freedcamp.ui.components;

import com.codeborne.selenide.Condition;
import com.freedcamp.ui.pages.HomePage;
import com.freedcamp.ui.pages.ProjectsPage;
import com.freedcamp.ui.pages.TasksPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SideBarComponent {

    @Step("Open Projects page")
    public ProjectsPage openProjectsPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard']")
                .shouldBe(Condition.visible)
                .click();
        return new ProjectsPage();
    }

    @Step("Open Home page")
    public HomePage openHomePage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/home']")
                .shouldBe(Condition.visible)
                .click();
        return new HomePage();
    }

    @Step("Open Tasks page")
    public TasksPage openTasksPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/tasks']")
                .shouldBe(Condition.visible)
                .click();
        return new TasksPage();
    }

    @Step("Open Calendar page")
    public void openCalendarPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/calendar']")
                .shouldBe(Condition.visible)
                .click();
    }
}
