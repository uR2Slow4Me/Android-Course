package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.xml.validation.Validator;

public class LoginActivity extends AppCompatActivity {
private EditText Name;
private EditText Password;
private TextView Info;
private Button Login;
private int Counter=3;
private TextView userRegistration;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);

        Info.setText("Numbers of attemps is 3");

        Login.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        validate(Name.getText().toString(), Password.getText().toString());
        }
        });
        userRegistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                }
        });

        }
private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        }
        else{
        Counter--;
        Info.setText("Number of attemps remaining: " + String.valueOf(Counter));
        if(Counter == 0){
        Login.setEnabled(false);
        }
        }
        }
        }