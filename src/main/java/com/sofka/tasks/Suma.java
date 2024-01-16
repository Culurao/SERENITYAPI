package com.sofka.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Suma implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Suma.class);
    private final Integer int1;
    private final Integer int2;

    public Suma(Integer int1, Integer int2) {
        this.int1 = int1;
        this.int2 = int2;
    }

    public static Performable suma(Integer int1, Integer int2) {
        return instrumented(Suma.class, int1, int2);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String resource = "/csp/samples/SOAP.Demo.cls";

        String bodyRequest = String.format(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <tem:AddInteger>\n" +
                        "         <!--Optional:-->\n" +
                        "         <tem:Arg1>%d</tem:Arg1>\n" +
                        "         <!--Optional:-->\n" +
                        "         <tem:Arg2>%d</tem:Arg2>\n" +
                        "      </tem:AddInteger>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>",
                int1, int2
        );

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-type","text/xml;charset=UTF-8");
        headers.put("SOAPAction", "http://tempuri.org/SOAP.Demo.AddInteger");

        actor.attemptsTo(
                Post.to(resource)
                        .with(
                            req ->
                                    req.headers(headers)
                                    .body(bodyRequest)

                        )
        );
        LOGGER.info("Respuesta SOAP: {}", LastResponse.received().answeredBy(actor).asString());
    }
}
