package com.freedcamp.utils;

import org.aeonbits.owner.Config;

@FreedcampConfig.Sources({"classpath:freedcamp.properties"})
public interface FreedcampConfig extends Config {
    @Key("baseUrl")
    String baseUrl();

    @Key("ui.browser")
    String browser();

    @Key("ui.headless")
    boolean headless();

    @Key("ui.email")
    String email();

    @Key("ui.password")
    String password();

    @Key("testUserId")
    String testUserId();

    @Key("testGroupId")
    String testGroupId();

    @Key("projectTemplateId")
    String projectTemplateId();
}

