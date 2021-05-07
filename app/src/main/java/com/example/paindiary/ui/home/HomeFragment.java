package com.example.paindiary.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiary.R;
import com.example.paindiary.WeatherService;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    public String wea = "city: suzhou\ntemp: 25\nHumidity:82\nPressure: 1020";

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String cityId = "1886760";
    public static String appsecret = "d7c33b70b706590fe2eb5d2a1274a71b";



    private HomeViewModel HomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText(wea);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(cityId, appsecret);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    String weatherData = "City: " +
                            weatherResponse.name +
                            "\n" +
                            "Temp: " +
                            (weatherResponse.main.temp - 273) +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main.pressure;
                    textView.setText(weatherData);
                }
            }
            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                textView.setText(wea);
            }
        });

        //textView.setText(HomeViewModel.getCurrentData());
        HomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}