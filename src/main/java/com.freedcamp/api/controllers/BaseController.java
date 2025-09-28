package com.freedcamp.api.controllers;

import com.freedcamp.utils.RequestSpecFactory;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController {

    private static final ThreadLocal<RequestSpecification> OVERRIDE = new ThreadLocal<>();

    protected RequestSpecification spec() {
        RequestSpecification s = OVERRIDE.get();
        return (s != null) ? s : RequestSpecFactory.getSpec();
    }
}