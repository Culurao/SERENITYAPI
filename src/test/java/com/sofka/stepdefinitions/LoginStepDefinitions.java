package com.sofka.stepdefinitions;

import com.sofka.Setup;
import com.sofka.questions.ExtractedUserData;
import com.sofka.tasks.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginStepDefinitions extends Setup {

    @Given("que soy un usuario registrado")
    public void que_soy_un_usuario_registrado() {
        Setup.actorSetupTheBrowser(true);
    }

    @When("ingreso mis credenciales de inicio de sesion")
    public void ingreso_mis_credenciales_de_inicio_de_sesion() {
    String loginUserInfo = "{\n" +
            "  \"correo\": \"e@e.com\",\n" +
            "  \"contrasena\": \"123\"\n" +
            "}";

        theActorInTheSpotlight().attemptsTo(
                Login.withInfo(loginUserInfo)
        );
    }

    @Then("deberia recibir un token de autenticacion")
    public void deberiaRecibirUnTokenDeAutenticacion() {
        boolean containsToken = theActorInTheSpotlight().asksFor(ExtractedUserData.userDetails(lastResponse()));

        assertThat(containsToken).isTrue();
    }
}
