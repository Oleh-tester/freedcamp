package com.freedcamp.utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",          // 1) те, що прийшло як -D...
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

    @Key("testGroupId")
    String testGroupId();

    @Key("projectTemplateId")
    String projectTemplateId();
}