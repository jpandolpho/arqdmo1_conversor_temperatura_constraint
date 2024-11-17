package br.edu.ifsp.dmo1.conversordetemperatura.model

interface ToCelsius: TemperatureConverter {
    override fun getScale()="°C"
}