package com.sofka.stepdefinitions;

import com.sofka.Setup;
import com.sofka.questions.ResultadoSuma;
import com.sofka.tasks.Suma;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SumarEnterosRunnerTest extends Setup {

    @Given("el actor tiene acceso al sistema")
    public void el_actor_tiene_acceso_al_sistema() {
        Setup.actorSetupTheBrowser(false);
    }

    @When("el actor declara los numeros enteros {int} y {int}")
    public void el_actor_declara_los_numeros_enteros_y(Integer int1, Integer int2) {
        theActorInTheSpotlight().attemptsTo(
                Suma.suma(int1, int2)
        );
    }

    @Then("deberia obtener la suma {int}")
    public void deberia_obtener_la_suma(Integer int1) {
        theActorInTheSpotlight().should(seeThat(ResultadoSuma.is(int1)));
    }
}
