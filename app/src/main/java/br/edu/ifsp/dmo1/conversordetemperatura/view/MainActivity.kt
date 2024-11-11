package br.edu.ifsp.dmo1.conversordetemperatura.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.conversordetemperatura.R
import br.edu.ifsp.dmo1.conversordetemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.conversordetemperatura.model.CelsiusStrategy
import br.edu.ifsp.dmo1.conversordetemperatura.model.FahrenheitStrategy
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
        binding.btnCelsius.setOnClickListener{
            handleConversion(CelsiusStrategy)
        }

        binding.btnFahrenheit.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitStrategy)
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
            binding.textviewResultMessage.text = if (this.converterStrategy is CelsiusStrategy){
                getString(R.string.msgFtoC)
            }else{
                getString(R.string.msgCtoF)
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