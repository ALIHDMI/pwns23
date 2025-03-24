package com.example.exchangeapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.text


import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    //Определение listView, adapter и кнопки городов
    private lateinit var listView: ListView
    private lateinit var adapter: ExchangeRateAdapter
    private lateinit var buttonMinsk: MaterialButton
    private lateinit var buttonBrest: MaterialButton
    private lateinit var buttonMogilev: MaterialButton
    private lateinit var buttonVitebsk: MaterialButton
    private lateinit var buttonGrodno: MaterialButton
    private lateinit var buttonGomel: MaterialButton
    private lateinit var choiceButton: MaterialButton

    //Переменные выбора города
    private var boolMinsk: Boolean = false
    private var boolBrest: Boolean = false
    private var boolMogilev: Boolean = false
    private var boolVitebsk: Boolean = false
    private var boolGrodno: Boolean = false
    private var boolGomel: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ExchangeRateAdapter(this, null)
        listView = findViewById(R.id.listView)
        buttonMinsk = findViewById(R.id.buttonMinsk)
        buttonBrest = findViewById(R.id.buttonBrest)
        buttonMogilev = findViewById(R.id.buttonMogilev)
        buttonVitebsk = findViewById(R.id.buttonVitebsk)
        buttonGrodno = findViewById(R.id.buttonGrodno)
        buttonGomel = findViewById(R.id.buttonGomel)
        choiceButton = findViewById(R.id.choiceButton)

        //Кнопка поиска курсов валют
        choiceButton.setOnClickListener {
            if (!boolMinsk and !boolBrest and !boolMogilev and !boolVitebsk and !boolGrodno and !boolGomel) {
                //Если ничего не выбрано
                Toast.makeText(this, "Выберите город)", Toast.LENGTH_SHORT).show()
            } else {
                //Если выбран один город
                Toast.makeText(this, "Пожалуйста подождите)", Toast.LENGTH_SHORT).show()
                callApi()

            }
        }
        buttonMinsk.setOnClickListener {
            boolMinsk = true
            boolBrest = false
            boolMogilev = false
            boolVitebsk = false
            boolGrodno = false
            boolGomel = false
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonBrest.setBackgroundColor(resources.getColor(R.color.white))
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.white))
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.white))
            buttonGomel.setBackgroundColor(resources.getColor(R.color.white))
        }
        buttonBrest.setOnClickListener {
            boolBrest = true
            boolMinsk = false
            boolMogilev = false
            boolVitebsk = false
            boolGrodno = false
            boolGomel = false
            buttonBrest.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.white))
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.white))
            buttonGomel.setBackgroundColor(resources.getColor(R.color.white))
        }
        buttonMogilev.setOnClickListener {
            boolMogilev = true
            boolBrest = false
            boolMinsk = false
            boolVitebsk = false
            boolGrodno = false
            boolGomel = false
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonBrest.setBackgroundColor(resources.getColor(R.color.white))
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.white))
            buttonGomel.setBackgroundColor(resources.getColor(R.color.white))
        }
        buttonVitebsk.setOnClickListener {
            boolVitebsk = true
            boolBrest = false
            boolMogilev = false
            boolMinsk = false
            boolGrodno = false
            boolGomel = false
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonBrest.setBackgroundColor(resources.getColor(R.color.white))
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.white))
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.white))
            buttonGomel.setBackgroundColor(resources.getColor(R.color.white))
        }
        buttonGrodno.setOnClickListener {
            boolGrodno = true
            boolBrest = false
            boolMogilev = false
            boolVitebsk = false
            boolMinsk = false
            boolGomel = false
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonBrest.setBackgroundColor(resources.getColor(R.color.white))
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.white))
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGomel.setBackgroundColor(resources.getColor(R.color.white))
        }
        buttonGomel.setOnClickListener {
            boolGomel = true
            boolBrest = false
            boolMogilev = false
            boolVitebsk = false
            boolGrodno = false
            boolMinsk = false
            buttonGomel.setBackgroundColor(resources.getColor(R.color.buttonColor))
            buttonBrest.setBackgroundColor(resources.getColor(R.color.white))
            buttonMogilev.setBackgroundColor(resources.getColor(R.color.white))
            buttonVitebsk.setBackgroundColor(resources.getColor(R.color.white))
            buttonGrodno.setBackgroundColor(resources.getColor(R.color.white))
            buttonMinsk.setBackgroundColor(resources.getColor(R.color.white))
        }
    }
    //Функция обращения к Api
    private fun callApi() {
        val call: Call<List<ExchangeRate>> = RetrofitClient.apiService.getExchangeRates()
        call.enqueue(object : Callback<List<ExchangeRate>> {

            override fun onResponse(call: Call<List<ExchangeRate>>, response: Response<List<ExchangeRate>>) {
                if (response.isSuccessful) {
                    val rates: List<ExchangeRate>? = response.body()
                    rates?.let { displayExchangeRates(it) }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ExchangeRate>>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }

    //Вывод данных относительно города на listView
    private fun displayExchangeRates(rates: List<ExchangeRate>) {
        var rates2: MutableList<ExchangeRate> = mutableListOf()
        val chosenCity = when {
            boolMinsk -> "Минск"
            boolBrest -> "Брест"
            boolMogilev -> "Могилев"
            boolVitebsk -> "Витебск"
            boolGrodno -> "Гродно"
            boolGomel -> "Гомель"
            else -> "Минск"
        }
        rates?.let { rates ->
            for (rate in rates) {
                if (rate.name == chosenCity) {
                    rates2.add(rate)
                }
            }
        }
        adapter = ExchangeRateAdapter(this, rates2)
        listView.adapter = adapter
    }
}

