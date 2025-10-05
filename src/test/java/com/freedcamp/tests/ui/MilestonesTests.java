package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import common.annotations.RequiresProjectFromTemplate;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MilestonesTests extends BaseUiTest {

    @Test
    @RequiresProjectFromTemplate
    @Tag("UI")
    @Description("Verify creating a new milestone")
    void createNewMilestone(CreatedProjectFromTemplate project) {
        var targetProject = project.createdProject().getProjectName();
        var milestoneName = "Milestone" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openMilestonesPage()
                .clickAddMilestoneButton()
                .selectProject(targetProject)
                .enterMilestoneTitle(milestoneName)
                .clickQuickAddButton()
                .verifyMilestoneIsDisplayedInListByProjectAndName(targetProject, milestoneName);
    }
}