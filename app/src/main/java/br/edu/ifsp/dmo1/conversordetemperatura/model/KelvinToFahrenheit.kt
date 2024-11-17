package br.edu.ifsp.dmo1.conversordetemperatura.model

object KelvinToFahrenheit:ToFahrenheit {
    override fun converter(temperature: Double): Double {
        return CelsiusToFahrenheit.converter(temperature-273)
    }
}