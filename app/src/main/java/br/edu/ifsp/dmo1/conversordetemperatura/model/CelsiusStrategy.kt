package br.edu.ifsp.dmo1.conversordetemperatura.model

object CelsiusStrategy: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return (temperature-32)/1.8
    }

    override fun getScale(): String {
        return "°C"
    }
}