package com.sofka.questions;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Question;

public class ExtractedUserData implements Question<Boolean> {

    private final Response response;

    public ExtractedUserData(Response response) {
        this.response = response;
    }

    @Override
    public Boolean answeredBy(net.serenitybdd.screenplay.Actor actor) {
        return response.path("resultado.token") != null;

    }

    public static ExtractedUserData userDetails(Response response) {
        return new ExtractedUserData(response);
    }
}
