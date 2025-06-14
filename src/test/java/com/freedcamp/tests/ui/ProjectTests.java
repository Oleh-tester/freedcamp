package com.freedcamp.tests.ui;

import com.freedcamp.ui.pages.LoginPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
