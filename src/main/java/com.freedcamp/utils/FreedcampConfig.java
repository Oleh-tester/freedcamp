package com.freedcamp.utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:${credsFile}",
        "classpath:freedcamp.properties"
})
public interface FreedcampConfig extends Config {
    @Key("baseUrl")
    String baseUrl();

    @Key("ui.browser")
    String browser();

    @Key("ui.headless")
    boolean headless();

    @Key("owner.email")
    String email();

    @Key("owner.password")
    String password();

    @Key("testUserId")
    String testUserId();

    @Key("projectTemplateId")
    String projectTemplateId();
}