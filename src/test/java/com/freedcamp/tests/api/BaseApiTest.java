package com.freedcamp.tests.api;

import com.freedcamp.utils.FreedcampConfig;
import com.freedcamp.testdata.TestDataSetupExtension;
import io.qameta.allure.junit5.AllureJunit5;
import net.datafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class, TestDataSetupExtension.class})
public abstract class BaseApiTest {
    protected static final FreedcampConfig CONFIG = ConfigFactory.create(FreedcampConfig.class);
    Faker faker = new Faker();
}