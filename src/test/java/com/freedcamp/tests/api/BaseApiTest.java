package com.freedcamp.tests.api;

import com.freedcamp.testdata.utils.TestDataSetupExtension;
import com.freedcamp.utils.FreedcampConfig;
import com.freedcamp.utils.logging.LoggingExtension;
import io.qameta.allure.junit5.AllureJunit5;
import net.datafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("API")
@ExtendWith({AllureJunit5.class, TestDataSetupExtension.class, LoggingExtension.class})
public abstract class BaseApiTest {
    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    Faker faker = new Faker();
}