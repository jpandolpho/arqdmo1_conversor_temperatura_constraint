package br.edu.ifsp.dmo1.conversordetemperatura.model

object FahrenheitToKelvin:ToKelvin {
    override fun converter(temperature: Double): Double {
        return 273.15 + FahrenheitToCelsius.converter(temperature)
    }
}