package com.sofka.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {

    String resource = "Usuario/login";

    private final String userInfo;

    public static Performable withInfo(String userInfo) {
        return instrumented(Login.class, userInfo);
    }

    public Login(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                request ->
                                        request.header("Content-Type", "application/json")
                                                .body(userInfo)
                        )
        );
        String token = SerenityRest.lastResponse().jsonPath().getString("resultado.token");
        actor.remember("token", token);
    }
}
