package com.freedcamp.utils;

import org.aeonbits.owner.Config;

@FreedcampConfig.Sources({"classpath:freedcamp.properties"})
public interface FreedcampConfig extends Config {
    @Key("api.apiUri")
    String apiUri();

    @Key("api.apiKey")
    String apiKey();

    @Key("api.secretKey")
    String secretKey();

    @Key("ui.baseUrl")
    String baseUrl();

    @Key("ui.browser")
    String browser();

    @Key("ui.headless")
    boolean headless();

    @Key("ui.email")
    String email();

    @Key("ui.password")
    String password();
}

