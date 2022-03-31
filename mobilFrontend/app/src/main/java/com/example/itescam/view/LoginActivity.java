package com.example.itescam.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.itescam.R;

public class LoginActivity extends AppCompatActivity {

    LinearLayout linear;
    Button btn;
    CheckBox remember;
    ImageView logoLogin;
    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linear = findViewById(R.id.linearEdt);
        btn = findViewById(R.id.btnLogin);
        //logoLogin = findViewById(R.id.logoLogin);
        user = findViewById(R.id.userEt);
        password = findViewById(R.id.passwordEt);
        remember = findViewById(R.id.checkRemember);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput()) login();
            }
        });

        funAnimation(linear);
        funAnimation(btn);
        funAnimation(remember);
        //funAnimation(logoLogin);
    }

    public void funAnimation(View view){
        view.animate().alpha(1).setDuration(1500).translationY(0);
    }

    public boolean validateInput(){

        boolean isEmpty = true;

        if(password.getText().toString().isEmpty()) {
            password.setError("Campo requerido");
            isEmpty = false;
        }

        if(user.getText().toString().isEmpty()){
            user.setError("Campo requerido");
            isEmpty = false;
        }else {
            if (user.getText().length() < 4) {
                user.setError("Matricula no valida");
                isEmpty = false;
            }
        }

        /*if(user.getText().toString().isEmpty()){
            user.setError("Campo requerido");
            isEmpty = false;
        }else {
            if(user.getText().length()) {
                user.setError("Correo NO válido");
                isEmpty = false;
            }
        }*/

        return isEmpty;
    }

    public void login(){
        Intent intent = new Intent(LoginActivity.this, dashboard.class);
        startActivity(intent);
        finish();
    }
}