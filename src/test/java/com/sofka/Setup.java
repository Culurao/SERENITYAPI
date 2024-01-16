package com.sofka;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.core.environment.WebDriverConfiguredEnvironment.getEnvironmentVariables;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Setup {
    private static String restApiUrl;
    private static String soapApiUrl;
    private static void setupUser(boolean isRestService) {
        restApiUrl = getEnvironmentVariables().getProperty("rest.url");
        soapApiUrl = getEnvironmentVariables().getProperty("soap.url");
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("ACTOR").can(CallAnApi.at(isRestService ? restApiUrl : soapApiUrl));
    }



    public static void actorSetupTheBrowser(boolean isRestService) {
        setupUser(isRestService);
    }

}
