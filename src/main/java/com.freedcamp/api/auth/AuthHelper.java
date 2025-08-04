package com.freedcamp.api.auth;

import com.freedcamp.api.controllers.LoginController;
import com.freedcamp.utils.FreedcampConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

public class AuthHelper {

    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final LoginController loginController = new LoginController();

    public static Map<String, String> getSessionCookie() {
        String aToken = extractAToken();

        Response loginResponse = loginController.login(
                CONFIG.email(),
                CONFIG.password(),
                aToken
        );

        loginResponse.then().statusCode(200);

        return loginResponse.getCookies();
    }

    public static String extractAToken() {
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