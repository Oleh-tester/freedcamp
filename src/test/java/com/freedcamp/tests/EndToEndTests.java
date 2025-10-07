package com.freedcamp.tests;

import com.freedcamp.pages.HomePage;
import com.freedcamp.pages.LandingPage;
import com.freedcamp.steps.E2eSteps;
import com.freedcamp.testdata.CreatedProject;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.utils.TestDataFactory;
import com.freedcamp.tests.ui.BaseUiTest;
import common.annotations.RequiresProject;
import common.annotations.RequiresProjectFromTemplate;
import common.annotations.SkipSessionInjection;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class EndToEndTests extends BaseUiTest {

    private final E2eSteps e2eSteps = new E2eSteps();

    @Test
    @SkipSessionInjection
    @RequiresProjectFromTemplate
    @Tag("e2e")
    @Description("Verify logging time on project.")
    void verifyLoggingTimeOnProject(CreatedProjectFromTemplate createdProject) {
        var targetProject = createdProject.createdProject().getProjectName();
        var timeRecord = TestDataFactory.validCompleted(targetProject);
        open("");
        new LandingPage()
                .login(CONFIG.email(), CONFIG.password())
                .verifyHomePageIsLoaded()
                .sidebar().openTimeRecordsPage()
                .verifyTimeRecordsPageIsLoaded()
                .openAddTimeRecordSidebar()
                .openProjectDropdown()
                .selectProjectFromDropdown(targetProject)
                .openAddTimeRecordFullForm()
                .enterWorkedHours(String.valueOf(timeRecord.duration()))
                .enterWhatsDoing(timeRecord.description())
                .changeStatusTo(timeRecord.status())
                .submitTimeRecordCreation()
                .verifyTimeRecordIsDisplayedByDescriptionDurationAndStatusLinkedToProject(timeRecord);
    }

    @Test
    @Tag("e2e")
    @Description("Verify creating project from template.")
    void verifyCreatingProjectFromTemplate() {
        String projectTemplateName = "Test Project Template" + faker.number().digits(4);
        open("");
        new HomePage()
                .verifyHomePageIsLoaded()
                .sidebar().openUserMenuPopover()
                .openManageSystemPage()
                .verifyManageSystemPageIsLoaded()
                .switchToTemplatesTab()
                .openProjectTemplatePopover()
                .selectCreateProjectFromTemplateOption()
                .verifyCreateProjectFromTemplateDrawerIsOpened()
                .fillInProjectName(projectTemplateName)
                .submitProjectCreation();

        e2eSteps.waitUntilProjectFromTemplateIsCreated(projectTemplateName);
    }

    @Test
    @Tag("e2e")
    @SkipSessionInjection
    @RequiresProject
    @DisplayName("Invite user -> signup -> complete account settings -> verify project is visible")
    void inviteSignupAndSeeProjects(CreatedProject createdProject) throws InterruptedException {
        var projectName = createdProject.createdProjectResponseDto().getData().getProjects().get(0).getProjectName();
        var groupId = createdProject.createdProjectResponseDto().getData().getGroups().get(0).getGroupId();
        var targetEmail = faker.internet().safeEmailAddress();

        e2eSteps.inviteUserToProject(groupId, targetEmail);
        open("");
        new LandingPage()
                .verifyLandingPageIsLoaded()
                .enterEmailAddress(targetEmail)
                .submitRegistration()
                .submitInvite()
                .verifySetupAccountPageIsLoaded()
                .enterFullName("Test User")
                .enterPassword("Test123.")
                .uncheckSendMeProductUpdatesCheckbox()
                .clickUpdateAccountButton()
                .verifyHomePageIsLoaded()
                .verifyProjectIsDisplayedInSideBar(projectName);
    }
}