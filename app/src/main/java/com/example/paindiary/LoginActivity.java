package com.example.paindiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "login";
    Button loginBtn = null;
    EditText useridEt = null;
    EditText passEt = null;
    TextView promptText = null;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        TextView email_view = (TextView) findViewById(R.id.login_et_email);
        TextView psw_view = (TextView) findViewById(R.id.login_et_password);
        email_view.setText("test@gmail.com");
        psw_view.setText("12345678");
        String login_email = email_view.getText().toString();
        String login_password = psw_view.getText().toString();
        mAuth.signInWithEmailAndPassword(login_email,login_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,getString(R.string.loginSuccess),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this,getString(R.string.loginError),Toast.LENGTH_SHORT).show();
                }
            }
        });
//
//        String userid = useridEt.getText().toString().trim();
//        String pass = passEt.getText().toString().trim();
//        if (userid.equals("")) {
//            promptText.setText(R.string.userIdError);
//            return;
//        }
//        if (pass.equals("")) {
//            promptText.setText(R.string.passError);
//            return;
//        }
//
//
//        if (userid.equals("admin") && pass.equals("admin")) {
//            Toast.makeText(this, R.string.loginSuccess, Toast.LENGTH_LONG).show();
//            Intent intent_hello = new Intent(this, HomeActivity.class);
//            startActivity(intent_hello);
//            LoginActivity.this.finish();
//        } else {
//            Toast.makeText(this, R.string.loginError, Toast.LENGTH_LONG).show();
//        }

    }
}