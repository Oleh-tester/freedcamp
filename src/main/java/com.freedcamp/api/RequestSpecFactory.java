package com.freedcamp.api;

import com.freedcamp.api.auth.AuthSignatureUtil;
import com.freedcamp.utils.FreedcampConfig;
import com.freedcamp.utils.LogRequestFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.time.Instant;

public class RequestSpecFactory {

    private static final FreedcampConfig config = ConfigFactory.create(FreedcampConfig.class);

    public static RequestSpecification getSpec() {
        long timestamp = Instant.now().getEpochSecond();
        String rawData = config.apiKey() + timestamp;
        String hash = AuthSignatureUtil.generateHmacSHA1Hex(config.secretKey(), rawData);

        return new RequestSpecBuilder()
                .setBaseUri(config.apiUri())
                .addQueryParam("api_key", config.apiKey())
                .addQueryParam("timestamp", String.valueOf(timestamp))
                .addQueryParam("hash", hash)
                .addFilter(new LogRequestFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }
}