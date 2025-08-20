package com.freedcamp.api.helpers;

import com.freedcamp.api.auth.AuthHelper;
import com.freedcamp.utils.FreedcampConfig;
import com.freedcamp.utils.LogRequestFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class RequestSpecFactory {

    private static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);

    public static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(CONFIG.baseUrl())
                .setBasePath("/iapi")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addCookies(AuthHelper.getSessionCookie())
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification getSpecWithoutAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(CONFIG.baseUrl())
                .setBasePath("/iapi")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification getWebSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(CONFIG.baseUrl())
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }
}