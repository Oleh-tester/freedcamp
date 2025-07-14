package com.freedcamp.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.annotations.SkipSessionInjection;
import com.freedcamp.api.auth.AuthHelper;
import com.freedcamp.utils.FreedcampConfig;
import io.qameta.allure.selenide.AllureSelenide;
import net.datafaker.Faker;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Cookie;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseUiTest {

    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = CONFIG.baseUrl();
        Configuration.browser = CONFIG.browser();
        Configuration.headless = CONFIG.headless();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.pageLoadTimeout = 10000;
        Configuration.reportsFolder = "build/reports/tests";
    }

    @BeforeEach
    void setupAllureListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );
    }

    @BeforeEach
    void injectSession(TestInfo testInfo) {
        boolean skip = testInfo.getTestMethod()
                .map(method -> method.isAnnotationPresent(SkipSessionInjection.class))
                .orElse(false);

        if (skip) {
            return;
        }

        var session = AuthHelper.getSessionCookie();
        open("/");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("ci_session", session.get("ci_session")));
        open("/dashboard/home");
    }
}