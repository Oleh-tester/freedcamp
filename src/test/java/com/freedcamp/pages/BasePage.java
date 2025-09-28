package com.freedcamp.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.freedcamp.pages.components.SideBarComponent;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {

    public final SelenideElement headerMenu = $x("//*[contains(@class, 'AppHeader')]");
    public final String activeDropDownElementByTextXpath = "//div[contains(@class,'SelectOption')" +
            " and contains(text(),'%s')]";
    protected SideBarComponent sidebar = new SideBarComponent();

    public SideBarComponent sidebar() {
        return sidebar;
    }

    protected void waitUntilVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }

    public void refreshPage() {
        refresh();
    }

    protected void clearJs(SelenideElement xpath) {
        xpath.shouldBe(Condition.visible);
        executeJavaScript("arguments[0].value=''", xpath);
        executeJavaScript("arguments[0].dispatchEvent(new Event('input'))", xpath);
    }

    @Step("Wait until HTML template is fully loaded.")
    public void waitHTMLTemplateLoad() {
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until((unused) ->
                "complete".equals(Selenide.executeJavaScript("return document.readyState"))
        );
        Selenide.executeJavaScript(
                "return new Promise(r=>requestAnimationFrame(()=>requestAnimationFrame(()=>r(true))))");
    }

    protected void waitForHeightStable() {
        long end = System.currentTimeMillis() + 4000;
        long prev = -1;
        int ok = 0;
        while (System.currentTimeMillis() < end) {
            long h = ((Number) executeJavaScript(
                    "return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight,"
                            + "document.body.offsetHeight, document.documentElement.offsetHeight,"
                            + "document.body.clientHeight, document.documentElement.clientHeight);"
            )).longValue();
            if (h == prev) ok++;
            else ok = 0;
            if (ok >= 3) return;
            prev = h;
            Selenide.sleep(80);
        }
        throw new AssertionError("Page height did not stabilize in time");
    }

    protected void closeCookieBannerIfPresent() {
        if ($x("//*[contains(.,'Our website uses cookies')]").exists()) {
            SelenideElement dismiss = $x("//*[@id='dismiss_cookies_banner']");
            if (dismiss.exists()) dismiss.click();
        }
    }
}
