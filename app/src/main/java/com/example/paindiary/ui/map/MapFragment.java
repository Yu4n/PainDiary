package com.example.paindiary.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiary.R;
import android.os.Bundle;

import com.example.paindiary.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MapFragment extends Fragment {
    private MapView mapView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        Mapbox.getInstance(getActivity().getApplicationContext(), getString(R.string.mapbox_access_token));
        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);




        mapView = (MapView) rootView.findViewById(R.id.mapView);
        //mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });
        return root;
    }
}