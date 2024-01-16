package com.sofka.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class GetUser implements Task {

    String resource = "Usuario";
    public static Performable getUser() {
        return instrumented(GetUser.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = actor.recall("token");
        actor.attemptsTo(
                Get.resource(resource).
                        with(request -> request.header("Authorization", "Bearer " + token))
        );
       // String responseAsString = SerenityRest.lastResponse().asString();
      //  System.out.println("Response: " + responseAsString);
    }

}
