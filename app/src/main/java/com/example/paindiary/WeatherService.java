package com.example.paindiary;

import com.example.paindiary.ui.home.WeatherResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    //Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    Call<WeatherResponse> getCurrentWeatherData(@Query("id") String cityId, @Query("appid") String appsecret);
}
