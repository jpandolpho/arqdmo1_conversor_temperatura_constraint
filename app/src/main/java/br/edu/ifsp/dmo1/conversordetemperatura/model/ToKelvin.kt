package br.edu.ifsp.dmo1.conversordetemperatura.model

interface ToKelvin:TemperatureConverter {
    override fun getScale()="K"
}