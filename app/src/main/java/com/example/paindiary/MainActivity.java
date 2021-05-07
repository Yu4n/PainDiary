package com.example.paindiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginBtn = (Button) findViewById(R.id.signin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        Button regBtn = (Button) findViewById(R.id.signup);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }
}