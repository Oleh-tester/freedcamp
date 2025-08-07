package com.freedcamp.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SideBarComponent {

    private final SelenideElement sidebarMenu = $x("//*[contains(@class, 'Sidebar')]");

    @Step("Open Projects page")
    public ProjectsPage openProjectsPage() {
        sidebarMenu.$x(".//a[@href='/dashboard']").click();
        return new ProjectsPage();
    }

    @Step("Open Home page")
    public HomePage openHomePage() {
        sidebarMenu.$x(".//a[@href='/dashboard/home']").click();
        return new HomePage();
    }

    @Step("Open Tasks page")
    public TasksPage openTasksPage() {
        sidebarMenu.$x(".//a[@href='/dashboard/tasks']").click();
        return new TasksPage();
    }

    @Step("Open Calendar page")
    public CalendarPage openCalendarPage() {
        sidebarMenu.$x(".//a[@href='/dashboard/calendar']").click();
        return new CalendarPage();
    }

    @Step("Open Time record page")
    public TimeRecordsPage openTimeRecordsPage() {
        $x("//*[contains(@class,'justifyContent-start')]").hover();
        sidebarMenu.$x(".//a[@href='/dashboard/time']").click();
        return new TimeRecordsPage();
    }

    @Step("Open User Menu Popover")
    public UserMenuPopover openUserMenuPopover() {
        sidebarMenu.$x(".//*[contains(@class, 'UserAvatar-Interactive')]").click();
        return new UserMenuPopover();
    }

    @Step("Open Milestones page")
    public MilestonesPage openMilestonesPage() {
        $x("//*[contains(@class,'justifyContent-start')]").hover();
        sidebarMenu.$x(".//a[@href='/dashboard/milestones']").click();
        return new MilestonesPage();
    }
}
