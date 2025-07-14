package com.freedcamp.api;

import com.freedcamp.api.auth.AuthHelper;
import com.freedcamp.utils.FreedcampConfig;
import com.freedcamp.utils.LogRequestFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class RequestSpecFactory {

    private static final FreedcampConfig config = ConfigFactory.create(FreedcampConfig.class);

    public static RequestSpecification getSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setBasePath("/iapi")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addCookies(AuthHelper.getSessionCookie())
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification getSpecWithoutAuth() {

        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setBasePath("/iapi")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }
}