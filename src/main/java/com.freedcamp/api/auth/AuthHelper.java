package com.freedcamp.api.auth;

import com.freedcamp.utils.FreedcampConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static io.restassured.RestAssured.given;

public class AuthHelper {

    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final String LOGIN_ENDPOINT = "/login";

    public static String getSessionCookie() {

        Response homePageResponse = given()
                .baseUri(CONFIG.baseUrl())
                .get("/");

        String html = homePageResponse.getBody().asString();
        String aToken = extractAToken(html);

        Response loginResponse = given()
                .baseUri(CONFIG.baseUrl())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0")
                .header("X-Requested-With", "XMLHttpRequest")
                .formParam("is_ajax", "true")
                .formParam("username", CONFIG.email())
                .formParam("password", CONFIG.password())
                .formParam("remember", "1")
                .formParam("a_token", aToken)
                .formParam("f_ajax_login", "1")
                .post(LOGIN_ENDPOINT);

        loginResponse.then().statusCode(200);

        return loginResponse.getCookie("ci_session");
    }

    private static String extractAToken(String html) {
        Document doc = Jsoup.parse(html);
        Element tokenInput = doc.selectFirst("input[name=a_token]");
        if (tokenInput == null) {
            throw new IllegalStateException("a_token not found on the homepage.");
        }
        return tokenInput.attr("value");
    }
}

