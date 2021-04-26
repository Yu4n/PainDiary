package com.example.paindiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "login";
    Button loginBtn = null;
    EditText useridEt = null;
    EditText passEt = null;
    TextView promptText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        useridEt = (EditText) findViewById(R.id.login_et_email);
        passEt = (EditText) findViewById(R.id.login_et_password);
        promptText = (TextView) findViewById(R.id.promptText);

    }


    @Override
    public void onClick(View v) {
        String userid = useridEt.getText().toString().trim();
        String pass = passEt.getText().toString().trim();
        if (userid.equals("")) {
            promptText.setText(R.string.userIdError);
            return;
        }
        if (pass.equals("")) {
            promptText.setText(R.string.passError);
            return;
        }


        if (userid.equals("admin") && pass.equals("admin")) {
            Toast.makeText(this, R.string.loginSuccess, Toast.LENGTH_LONG).show();
            Intent intent_hello = new Intent(this, HomeActivity.class); // todo create a post-login class
            startActivity(intent_hello);
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, R.string.loginError, Toast.LENGTH_LONG).show();
        }

    }
}