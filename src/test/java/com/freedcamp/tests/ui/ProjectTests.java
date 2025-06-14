package com.freedcamp.tests.ui;

import com.freedcamp.pages.LoginPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ProjectTests extends BaseUiTest {

    @Test
    @Description("Test for creating a new project")
    void createNewProject() {
        open("");
        new LoginPage().login(CONFIG.email(), CONFIG.password())
                .verifyHomePageIsLoaded()
                .clickAddProjectButton()
                .selectProjectFromScratch()
                .enterProjectName("New Project")
                .enterProjectDescription("This is a test project")
                .clickCreateProjectButton()
                .sidebar().openProjectsPage()
                .verifyProjectPageIsLoaded()
                .verifyProjectIsDisplayedInProjectsPage("New Project");
    }
}
