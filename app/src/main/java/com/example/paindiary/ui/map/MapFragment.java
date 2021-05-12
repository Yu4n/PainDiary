package com.example.paindiary.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiary.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.paindiary.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MapFragment extends Fragment {
    private MapView mapView;
    public LatLng getLocationFromAddress(String strAddress,double lat,double lon){

        Geocoder coder = new Geocoder(getContext());
        List<Address> address;
        LatLng  p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            lat = location.getLatitude();
            lon = location.getLongitude();

            p1 = new LatLng ((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        Mapbox.getInstance(getActivity().getApplicationContext(), getString(R.string.mapbox_access_token));
        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        double lat = -37.876823, lon = 145.045837;
        String strAddress = "suzhou";
        //final LatLng latLng= new LatLng(-37.876823, 145.045837);


        Geocoder coder = new Geocoder(getContext());
        List<Address> address;


        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            lat = location.getLatitude();
            lon = location.getLongitude();


        } catch (IOException e) {
            e.printStackTrace();
        }



        final LatLng latLng= new LatLng(lat, lon);

        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                        CameraPosition position = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(13)
                                .build();
                        mapboxMap.setCameraPosition(position);

                    }
                });

            }
        });


        Button btn_map_search = root.findViewById(R.id.btn_map_search);
        EditText edit_map_search = root.findViewById(R.id.edit_map_search);
        // edit_map_search.getText();
        btn_map_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAddress = edit_map_search.getText().toString();


                //final LatLng latLng= new LatLng(-37.876823, 145.045837);


                Geocoder coder = new Geocoder(getContext());
                List<Address> address;

                double lat = -37.876823, lon = 145.045837;
                try {
                    address = coder.getFromLocationName(strAddress,5);
                    if (address==null) {
                        address = null;
                    }
                    assert address != null;
                    Address location=address.get(0);
                    lat = location.getLatitude();
                    lon = location.getLongitude();


                } catch (IOException e) {
                    e.printStackTrace();
                }



                final LatLng latLng= new LatLng(lat, lon);

                mapView = (MapView) root.findViewById(R.id.mapView);
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull MapboxMap mapboxMap) {

                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {

                                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                                CameraPosition position = new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(13)
                                        .build();
                                mapboxMap.setCameraPosition(position);

                            }
                        });

                    }
                });


            }
        });


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}