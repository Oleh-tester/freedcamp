package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.testdata.CreatedProject;
import common.annotations.RequiresProject;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MilestonesTests extends BaseUiTest {

    @Test
    @RequiresProject
    @Description("Verify creating a new milestone")
    void createNewMilestone(CreatedProject project) {
        var targetProject = project.originalDto().getProjectName();
        var milestoneName = "Milestone" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openMilestonesPage()
                .clickAddMilestoneButton()
                .openProjectDropdown()
                .selectProject(targetProject)
                .enterMilestoneTitle(milestoneName)
                .clickQuickAddButton()
                .verifyMilestoneIsDisplayedInListByProjectAndName(targetProject, milestoneName);
    }
}