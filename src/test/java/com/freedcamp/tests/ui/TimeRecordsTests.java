package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedProject;
import common.annotations.RequiresProject;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
public class TimeRecordsTests extends BaseUiTest {

    @Test
    @RequiresProject
    @Tag("Smoke")
    @Description("Test for creating a new time record on a project")
    void verifyLoggingTimeOnProject(CreatedProject targetProject) {
        var targetProjectName = targetProject.originalDto().getProjectName();
        var timeRecord = "Test record " + faker.number().digits(4);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTimeRecordsPage()
                .verifyTimeRecordsPageIsLoaded()
                .openAddTimeRecordSidebar()
                .selectProjectFromDropdown(targetProjectName)
                .enterRecordTitleQuickAdd(timeRecord)
                .clickQuickAddButton()
                .verifyTimeRecordIsDisplayedByDescriptionAndProject(timeRecord, targetProjectName);
    }
}