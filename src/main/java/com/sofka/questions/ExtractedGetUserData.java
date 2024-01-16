package com.sofka.questions;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Question;

public class ExtractedGetUserData implements Question<Boolean> {

    private final Response response;

    public ExtractedGetUserData(Response response) {
        this.response = response;
    }

    @Override
    public Boolean answeredBy(net.serenitybdd.screenplay.Actor actor) {
        return response.path("resultado") != null;

    }

    public static ExtractedGetUserData userDetails(Response response) {
        return new ExtractedGetUserData(response);
    }
}
