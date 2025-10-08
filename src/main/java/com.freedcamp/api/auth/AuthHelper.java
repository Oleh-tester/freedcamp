package com.freedcamp.api.auth;

import com.freedcamp.api.controllers.LoginController;
import com.freedcamp.utils.FreedcampConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AuthHelper {

    private static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);

    private static LoginController newLoginController() {
        return new LoginController();
    }

    private record Session(Map<String, String> cookies) {
            private Session(Map<String, String> cookies) {
                this.cookies = Collections.unmodifiableMap(new HashMap<>(cookies));
            }
        }

    private static final ConcurrentHashMap<String, Session> CACHE = new ConcurrentHashMap<>();

    public static Map<String, String> getOwnerSessionCookie() {
        return getSharedSession("OWNER", CONFIG.email(), CONFIG.password());
    }

    public static Map<String, String> getSharedSession(String key, String email, String password) {
        Session s = CACHE.compute(key, (k, old) -> {
            if (old == null ) {
                return loginNewSession(email, password);
            }
            return old;
        });
        return new HashMap<>(s.cookies);
    }

    private static Session loginNewSession(String email, String password) {
        var loginResponse = newLoginController().login(email, password);
        loginResponse.then().statusCode(200);
        Map<String, String> cookies = loginResponse.getCookies();
        return new Session(cookies);
    }

    public static Map<String, String> getFreshSession(String email, String password) {
        return new HashMap<>(loginNewSession(email, password).cookies);
    }

    public static void invalidate(String key) {
        CACHE.remove(key);
    }
}