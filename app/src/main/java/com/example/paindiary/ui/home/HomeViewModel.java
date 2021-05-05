package com.example.paindiary.ui.home;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paindiary.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
//    public static String BaseUrl = "http://api.openweathermap.org/";
//    public static String AppId = "d7c33b70b706590fe2eb5d2a1274a71b";
//    public static int cityid = 1886760;
//    public static String lon = "120.6181";
    public static String BaseUrl = "https://v0.yiketianqi.com/";
    public static String version = "v61";
    public static String appid = "79129227";
    public static String appsecret = "2eI2wUTi";
    public String weatherData;

//    void getCurrentData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        WeatherService service = retrofit.create(WeatherService.class);
//        Call<WeatherResponse> call = service.getCurrentWeatherData(version, appid, appsecret);
//        call.enqueue(new Callback<WeatherResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
//                if (response.code() == 200) {
//                    WeatherResponse weatherResponse = response.body();
//                    assert weatherResponse != null;
//                    weatherData = "City: Suzhou" +
//                            "\n" +
//                            "Temp: " +
//                            (weatherResponse.tem - 273) +
//                            "\n" +
//                            "Humidity: " +
//                            weatherResponse.humidity +
//                            "\n" +
//                            "Pressure: " +
//                            weatherResponse.pressure;
//                    mText.setValue(weatherData);
//                }
//            }
//            @Override
//            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
//                mText.setValue(t.getMessage());
//            }
//        });
//    }

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        //getCurrentData();
//        String ss = "City: Suzhou" +
//                "\n" +
//                "Temp: " +
//                (int)(288.94 - 273) +
//                "\n" +
//                "Humidity: " +
//                82 +
//                "\n" +
//                "Pressure: " +
//                1014;
//        mText.setValue(ss);
    }

    public LiveData<String> getText() {
        return mText;
    }
}