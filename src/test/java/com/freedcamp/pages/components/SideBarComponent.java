package com.freedcamp.pages.components;

import com.freedcamp.pages.CalendarPage;
import com.freedcamp.pages.HomePage;
import com.freedcamp.pages.ProjectsPage;
import com.freedcamp.pages.TasksPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SideBarComponent {

    @Step("Open Projects page")
    public ProjectsPage openProjectsPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard']").click();
        return new ProjectsPage();
    }

    @Step("Open Home page")
    public HomePage openHomePage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/home']").click();
        return new HomePage();
    }

    @Step("Open Tasks page")
    public TasksPage openTasksPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/tasks']").click();
        return new TasksPage();
    }

    @Step("Open Calendar page")
    public CalendarPage openCalendarPage() {
        $x("//*[contains(@class, 'Sidebar')]//a[@href='/dashboard/calendar']").click();
        return new CalendarPage();
    }
}
