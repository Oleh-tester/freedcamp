package com.freedcamp.tests;

import com.freedcamp.pages.LoginPage;
import com.freedcamp.steps.E2eSteps;
import com.freedcamp.testdata.CreatedProjectFromTemplate;
import com.freedcamp.testdata.TimeRecordFactory;
import com.freedcamp.tests.ui.BaseUiTest;
import common.annotations.RequiresProjectFromTemplate;
import common.annotations.SkipSessionInjection;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class EndToEndTests extends BaseUiTest {

    private final E2eSteps e2eSteps = new E2eSteps();

    @Test
    @SkipSessionInjection
    @RequiresProjectFromTemplate
    @Tag("E2e")
    @Description("Verify logging time on project.")
    void verifyLoggingTimeOnProject(CreatedProjectFromTemplate createdProject) {
        var targetProject = createdProject.projectName();
        var timeRecord = TimeRecordFactory.validCompleted(targetProject);
        open("");
        new LoginPage()
                .login(CONFIG.email(), CONFIG.password())
                .verifyHomePageIsLoaded()
                .sidebar().openTimeRecordsPage()
                .verifyTimeRecordsPageIsLoaded()
                .openAddTimeRecordSidebar()
                .selectProjectFromDropdown(targetProject)
                .openAddTimeRecordFullForm()
                .enterWorkedHours(String.valueOf(timeRecord.duration()))
                .enterWhatsDoing(timeRecord.description())
                .changeStatusTo(timeRecord.status())
                .submitTimeRecordCreation()
                .verifyTimeRecordIsDisplayedByDescriptionDurationAndStatusLinkedToProject(timeRecord);
    }

    @Test
    @SkipSessionInjection
    @Tag("E2e")
    @Description("Verify creating project from template.")
    void verifyCreatingProjectFromTemplate() {
        String projectTemplateName = "Test Project Template" + faker.number().digits(4);
        open("");
        new LoginPage()
                .login(CONFIG.email(), CONFIG.password())
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
}