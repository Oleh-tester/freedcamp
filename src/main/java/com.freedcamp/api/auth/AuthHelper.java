package com.freedcamp.api.auth;

import com.freedcamp.api.controllers.LoginController;
import com.freedcamp.utils.FreedcampConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.logging.Level.CONFIG;

public class AuthHelper {
    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    private static final LoginController loginController = new LoginController();

    private static final Map<String, Map<String, String>> CACHE = new ConcurrentHashMap<>();
    private static final Duration TTL = Duration.ofHours(8);
    private static final Map<String, Instant> EXPIRES = new ConcurrentHashMap<>();

    public static Map<String, String> getOwnerSessionCookie() {
        return getOrLogin("OWNER", CONFIG.email(), CONFIG.password());
    }

    public static Map<String, String> getMemberSessionCookie() {
        return getOrLogin("MEMBER", CONFIG.memberEmail(), CONFIG.memberPassword());
    }

    public static Map<String, String> getSessionCookie(String key, String email, String password) {
        return getOrLogin(key, email, password);
    }

    private static Map<String, String> getOrLogin(String key, String email, String password) {
        if (!CACHE.containsKey(key) || isExpired(key)) {
            CACHE.put(key, login(email, password));
            EXPIRES.put(key, Instant.now().plus(TTL));
        }
        return CACHE.get(key);
    }

    private static boolean isExpired(String key) {
        Instant e = EXPIRES.get(key);
        return e == null || Instant.now().isAfter(e.minusSeconds(30));
    }

    private static Map<String, String> login(String email, String password) {
        var aToken = UUID.randomUUID().toString().replace("-", "").substring(0, 13);
        var loginResponse = loginController.login(email, password, aToken);
        loginResponse.then().statusCode(200);
        return loginResponse.getCookies();
    }
}