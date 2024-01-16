package com.sofka.stepdefinitions;

import com.sofka.Setup;
import com.sofka.questions.ExtractedGetUserData;
import com.sofka.questions.ExtractedUserData;
import com.sofka.tasks.GetUser;
import com.sofka.tasks.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetUserStepDefinitions extends Setup {

    @Given("que soy un usuario autenticado")
    public void que_soy_un_usuario_autenticado() {
        Setup.actorSetupTheBrowser(true);
        String loginUserInfo = "{\n" +
                "  \"correo\": \"e@e.com\",\n" +
                "  \"contrasena\": \"123\"\n" +
                "}";

        theActorInTheSpotlight().attemptsTo(
                Login.withInfo(loginUserInfo)
        );
    }

    @When("hago una solicitud GET para obtener detalles de usuarios")
    public void hago_una_solicitud_get_para_obtener_detalles_de_usuarios() {
        theActorInTheSpotlight().wasAbleTo(
                GetUser.getUser()
        );
    }

    @Then("deberia recibir una respuesta exitosa")
    public void deberia_recibir_una_respuesta_exitosa() {
        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    }

    @And("la respuesta deberia contener detalles de usuarios")
    public void la_respuesta_deberia_contener_detalles_de_usuarios() {
        boolean containsUsers = theActorInTheSpotlight().asksFor(ExtractedGetUserData.userDetails(lastResponse()));
        assertThat(containsUsers).isTrue();
    }
}