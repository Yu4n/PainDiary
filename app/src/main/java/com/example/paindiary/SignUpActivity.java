package com.example.paindiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public EditText et_email,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signBtn = (Button) findViewById(R.id.tosignup);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getApplicationContext(), MainActivity.class));

                mAuth = FirebaseAuth.getInstance();
                TextView email_view = (TextView) findViewById(R.id.signup_et_email);
                TextView psw_view = (TextView) findViewById(R.id.signup_et_password);
                String email = email_view.getText().toString();
                String password = psw_view.getText().toString();
                //final AlertDialog dlg= UIUtils.createDialog(getString(R.string.wait),getString(R.string.wait), this);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(dlg!=null){
//                            dlg.dismiss();
//                        }
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,getString(R.string.loginSuccess),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
                            SignUpActivity.this.finish();
                        }else{
                            Toast.makeText(SignUpActivity.this,getString(R.string.loginError),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        et_email= (EditText) findViewById(R.id.signup_et_email);
        et_password= (EditText) findViewById(R.id.signup_et_password);
        mAuth = FirebaseAuth.getInstance();
    }
//
//    private void init(){
//        et_email= (EditText) findViewById(R.id.login_et_email);
//        et_password= (EditText) findViewById(R.id.login_et_password);
//        mAuth = FirebaseAuth.getInstance();
//    }

    public void login(View v){

    }

    public void reg(View v){

    }

}