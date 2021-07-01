package com.example.remote_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.remote_home.api.ApiObject
import com.example.remote_home.api.WeatherAPI
import com.example.remote_home.api.WeatherInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import com.example.remote_home.api

class MainActivity : AppCompatActivity() {

    val num_of_rows = 10
    val page_no = 1
    val data_type = "JSON"
    val base_time = 1100
    val base_data = 20200808
    val nx = "55"
    val ny = "127"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test: WeatherInterface? = ApiObject.getWeather(API.WEATHER_URL)?.create(WeatherInterface::class.java)

        val call = ApiObject.retrofitService.GetWeather(data_type, num_of_rows, page_no, base_data, base_time, nx, ny)
        call.enqueue(object : retrofit2.Callback<WEATHER>{
            override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                if (response.isSuccessful){
                    Log.d("api", response.body().toString())
                    Log.d("api", response.body()!!.response.body.items.item.toString())
                    Log.d("api", response.body()!!.response.body.items.item[0].category)
                }
            }
            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                //Log.d("api fail : ", t.message)
            }
        })


    }
}