package com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ResultadoSuma implements Question<Boolean> {

    private final Integer sumaEsperada;

    public ResultadoSuma(Integer sumaEsperada) {
        this.sumaEsperada = sumaEsperada;
    }

    public static ResultadoSuma is(Integer sumaEsperada) {
        return new ResultadoSuma(sumaEsperada);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String respuestaSOAP = LastResponse.received().answeredBy(actor).asString();

        int sumaActual = extraerResultadoSuma(respuestaSOAP);

        return sumaEsperada.equals(sumaActual);
    }

    private int extraerResultadoSuma(String respuestaSOAP) {
        String etiquetaInicio = "<AddIntegerResult>";
        String etiquetaFin = "</AddIntegerResult>";

        int indiceInicio = respuestaSOAP.indexOf(etiquetaInicio) + etiquetaInicio.length();
        int indiceFin = respuestaSOAP.indexOf(etiquetaFin);

        String valorResultadoSuma = respuestaSOAP.substring(indiceInicio, indiceFin);

        return Integer.parseInt(valorResultadoSuma);
    }
}