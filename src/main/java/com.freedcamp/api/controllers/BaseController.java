package com.freedcamp.api.controllers;

import com.freedcamp.api.helpers.RequestSpecFactory;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController<T extends BaseController<T>> {

    private static final ThreadLocal<RequestSpecification> OVERRIDE = new ThreadLocal<>();

    protected RequestSpecification spec() {
        RequestSpecification s = OVERRIDE.get();
        return (s != null) ? s : RequestSpecFactory.getSpec();
    }

    @SuppressWarnings("unchecked")
    public T with(RequestSpecification customSpec) {
        OVERRIDE.set(customSpec);
        return (T) this;
    }

    public void clearOverride() {
        OVERRIDE.remove();
    }
}