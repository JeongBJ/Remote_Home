package com.example.remote_home.api

import android.util.Log
import com.example.remote_home.WEATHER
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class WeatherAPI {

    companion object{
        val instance = WeatherAPI()
    }


}


interface WeatherInterface {
    @GET("getVilageFcst?serviceKey=서비스키")
    fun GetWeather(
        @Query("dataType") data_type : String,
        @Query("numOfRows") num_of_rows : Int,
        @Query("pageNo") page_no : Int,
        @Query("base_date") base_date : Int,
        @Query("base_time") base_time : Int,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ): Call<WEATHER> // WEATHER는 DATA CLASS
}


private val retrofit = Retrofit.Builder()
    .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiObject {
    val retrofitService: WeatherInterface by lazy {
        retrofit.create(WeatherInterface::class.java)
    }

    fun getWeather(baseURL : String):Retrofit?{

        val num_of_rows = 10
        val page_no = 1
        val data_type = "JSON"
        val base_time = 1100
        val base_data = 20200808
        val nx = "55"
        val ny = "127"

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
                Log.d("api fail : ", "d")
            }
        })


        return retrofitClient;
    }
}


private var retrofitClient: Retrofit? = null

