package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import common.annotations.RequiresProjectFromTemplate;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TimeRecordsTests extends BaseUiTest {

    @Test
    @RequiresProjectFromTemplate
    @Tag("UI")
    @Description("Test for creating a new time record on a project")
    void verifyLoggingTimeOnProject(CreatedProjectFromTemplate targetProject) {
        var projectName = targetProject.projectName();
        var timeRecord = "Test record " + faker.number().digits(4);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openTimeRecordsPage()
                .verifyTimeRecordsPageIsLoaded()
                .openAddTimeRecordSidebar()
                .selectProjectFromDropdown(projectName)
                .enterRecordTitleQuickAdd(timeRecord)
                .clickQuickAddButton()
                .verifyTimeRecordIsDisplayedByDescriptionAndProject(timeRecord, projectName);
    }
}