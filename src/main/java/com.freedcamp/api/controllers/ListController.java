package com.freedcamp.api.controllers;

import com.freedcamp.utils.RequestSpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ListController extends BaseController {

     @Step("Get project lists")
     public Response getAllListsByProject(String projectId) {
         return given()
                 .spec(RequestSpecFactory.getSpec())
                 .when()
                 .get("/lists/2?project_id=" + projectId)
                 .then()
                 .extract()
                 .response();
     }
}
