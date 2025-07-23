package com.freedcamp.tests.api;

import com.freedcamp.utils.TestDataSetupExtension;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class, TestDataSetupExtension.class})
public abstract class BaseApiTest {
}
