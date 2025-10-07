package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ProjectTests extends BaseUiTest {

    @Test
    @Tag("UI")
    @Tag("Smoke")
    @Description("Verify creating a new project")
    void createNewProject() {
        var projectName = "New Project" + faker.number().digits(3);
        new HomePage()
                .verifyHomePageIsLoaded()
                .clickAddProjectButton()
                .selectProjectFromScratch()
                .enterProjectName(projectName)
                .enterProjectDescription("This is a test project")
                .clickCreateProjectButton()
                .sidebar().openProjectsPage()
                .verifyProjectPageIsLoaded()
                .verifyProjectIsDisplayedInProjectsPage(projectName)
                .deleteProjectFromProjectsPage(projectName);
    }
}