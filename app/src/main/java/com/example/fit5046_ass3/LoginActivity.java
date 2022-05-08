package com.example.fit5046_ass3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        Button registerButton = findViewById(R.id.signUp);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_txt = emailEditText.getText().toString();
                String password_txt = passwordEditText.getText().toString();
                if (TextUtils.isEmpty(email_txt) || TextUtils.isEmpty(password_txt)) {
                    String msg = "Empty Username or Password";
                } else if (password_txt.length() < 6) {
                    String msg = "Password is too short";
                } else registerUser(email_txt, password_txt);
            }
        });

        Button loginButton = findViewById(R.id.signIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_Email = emailEditText.getText().toString();
                String txt_Pwd = passwordEditText.getText().toString();
                loginUser(txt_Email, txt_Pwd);

            }
        });
    }

    private void registerUser(String email_txt, String password_txt) { // To create username and password
        auth.createUserWithEmailAndPassword(email_txt, password_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String msg = "Registration Successful";
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    String msg = "Registration Unsuccessful";
                    toastMsg(msg);
                }
            }
        });
    }

    public void toastMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void loginUser(String txt_email, String txt_pwd) {
        // call the object and provide it with email id and password

        if(txt_email.isEmpty() || txt_pwd.isEmpty()){
            toastMsg("Error: Empty Input!");
        }
        else
        {
            auth.signInWithEmailAndPassword(txt_email, txt_pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String msg = "Login Successful";
                    toastMsg(msg);

                    String inputEmail = txt_email;


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userEmail",inputEmail);

                    System.out.println("@@@@@@@@@@@@@@@@@@@" + inputEmail + " Login");

                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String msg = "Invalid Email OR Password";
                    toastMsg(msg);
                }
            });
        }
    }
}