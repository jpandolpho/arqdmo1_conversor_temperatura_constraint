package br.edu.ifsp.dmo1.conversordetemperatura.model

object CelsiusToFahrenheit:ToFahrenheit {
    override fun converter(temperature: Double): Double {
        return 1.8*temperature+32
    }

}