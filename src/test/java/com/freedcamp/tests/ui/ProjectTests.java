package com.freedcamp.tests.ui;

import com.freedcamp.pages.HomePage;
import com.freedcamp.pages.LoginPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ProjectTests extends BaseUiTest {

    @Test
    @Tag("UI")
    @Description("Test for creating a new project")
    void createNewProject() {
        new HomePage()
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
