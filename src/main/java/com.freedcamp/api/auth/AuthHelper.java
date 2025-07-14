package com.freedcamp.api.auth;

import com.freedcamp.utils.FreedcampConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthHelper {

    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final String LOGIN_ENDPOINT = "/login";

    public static Map<String, String> getSessionCookie() {
        String aToken = extractAToken();

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

        return loginResponse.getCookies();
    }

    private static String extractAToken() {
        try {
            String html = RestAssured
                    .given()
                    .baseUri(CONFIG.baseUrl())
                    .queryParam("f_lgt", "1")
                    .when()
                    .get("/")
                    .then()
                    .statusCode(200)
                    .extract()
                    .asString();

            Document doc = Jsoup.parse(html);
            Element tokenInput = doc.selectFirst("input[name=a_token]");

            if (tokenInput != null) {
                return tokenInput.attr("value");
            } else {
                throw new RuntimeException("a_token not found in <input>");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract a_token", e);
        }
    }
}

