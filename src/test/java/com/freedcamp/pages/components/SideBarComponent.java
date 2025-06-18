package com.freedcamp.pages.components;

import com.codeborne.selenide.Condition;
import com.freedcamp.pages.CalendarPage;
import com.freedcamp.pages.HomePage;
import com.freedcamp.pages.ProjectsPage;
import com.freedcamp.pages.TasksPage;
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
    public CalendarPage openCalendarPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/calendar']")
                .shouldBe(Condition.visible)
                .click();
        return new CalendarPage();
    }
}
