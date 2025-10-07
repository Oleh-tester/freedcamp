package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage extends BasePage {

    @Step("Verify Projects page is loaded")
    public ProjectsPage verifyProjectPageIsLoaded() {
        waitUntilVisible($x("//*[@class='font-header-3' and text()='Projects']"));
        return this;
    }

    @Step("Verify project {projectName} is displayed in Projects page")
    public ProjectsPage verifyProjectIsDisplayedInProjectsPage(String projectName) {
        $x("//*[contains(@class, 'ProjectsSection')]//*[text()='" + projectName + "']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Delete project {projectName} from Projects page")
    public void deleteProjectFromProjectsPage(String projectName) {
        $x("//div[contains(@class,'ListProject')][contains(.,'" + projectName + "')]//*[contains(@name,'AgMenuDots')]")
                .shouldBe(Condition.visible).click();
        $x("//*[text()='Delete project']").shouldBe(Condition.visible).click();
        $x("//*[contains(@class,'Delete')]/*[text()='Yes']").shouldBe(Condition.visible).click();
    }
}
