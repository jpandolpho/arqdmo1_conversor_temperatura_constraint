package br.edu.ifsp.dmo1.conversordetemperatura.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.conversordetemperatura.R
import br.edu.ifsp.dmo1.conversordetemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.conversordetemperatura.model.CelsiusToFahrenheit
import br.edu.ifsp.dmo1.conversordetemperatura.model.CelsiusToKelvin
import br.edu.ifsp.dmo1.conversordetemperatura.model.FahrenheitToCelsius
import br.edu.ifsp.dmo1.conversordetemperatura.model.FahrenheitToKelvin
import br.edu.ifsp.dmo1.conversordetemperatura.model.KelvinToCelsius
import br.edu.ifsp.dmo1.conversordetemperatura.model.KelvinToFahrenheit
import br.edu.ifsp.dmo1.conversordetemperatura.model.ToCelsius
import br.edu.ifsp.dmo1.conversordetemperatura.model.ToFahrenheit
import br.edu.ifsp.dmo1.conversordetemperatura.model.TemperatureConverter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnCtoF.setOnClickListener{
            handleConversion(CelsiusToFahrenheit)
        }

        binding.btnCtoK.setOnClickListener(View.OnClickListener {
            handleConversion(CelsiusToKelvin)
        })

        binding.btnFtoC.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitToCelsius)
        })

        binding.btnFtoK.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitToKelvin)
        })

        binding.btnKtoC.setOnClickListener(View.OnClickListener {
            handleConversion(KelvinToCelsius)
        })

        binding.btnKtoF.setOnClickListener(View.OnClickListener {
            handleConversion(KelvinToFahrenheit)
        })
    }

    private fun readTemperature(): Double {
        return try {
            binding.edittextTemperature.text.toString().toDouble()
        }catch (e: NumberFormatException){
            throw NumberFormatException("Input Error")
        }
    }

    private fun handleConversion(strategy: TemperatureConverter) {
        converterStrategy = strategy

        try{
            val inputValue = readTemperature()
            binding.textviewResultNumber.text = String.format(
                "%.2f %s",
                converterStrategy.converter(inputValue),
                converterStrategy.getScale()
            )
            binding.textviewResultMessage.text = when {
                this.converterStrategy is CelsiusToKelvin -> getString(R.string.msgCtoK)
                this.converterStrategy is CelsiusToFahrenheit -> getString(R.string.msgCtoF)
                this.converterStrategy is FahrenheitToKelvin -> getString(R.string.msgFtoK)
                this.converterStrategy is FahrenheitToCelsius -> getString(R.string.msgFtoC)
                this.converterStrategy is KelvinToCelsius -> getString(R.string.msgKtoC)
                this.converterStrategy is KelvinToFahrenheit -> getString(R.string.msgKtoF)
                else -> getString(R.string.error_popup_notify)
            }
        }catch (e: Exception){
            Toast.makeText(
                this,
                getString(R.string.error_popup_notify),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }
    }


}