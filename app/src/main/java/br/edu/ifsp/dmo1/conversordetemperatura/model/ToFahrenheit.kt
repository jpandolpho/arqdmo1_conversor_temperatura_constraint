package br.edu.ifsp.dmo1.conversordetemperatura.model

interface ToFahrenheit:TemperatureConverter {
    override fun getScale()="°F"
}