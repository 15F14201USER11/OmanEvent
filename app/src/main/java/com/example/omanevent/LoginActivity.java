package com.example.omanevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth FAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
    }

    public  void onLoginButtonClick(View view){
        EditText emailtextfeild = findViewById(R.id.email_texbox);
        EditText passwordfield = findViewById(R.id.passwword_textbox);
        String email= emailtextfeild.getText().toString();
        String password = passwordfield.getText().toString();
        singIn(email, password);
    }


    public  void onLoginSignUpClick(View view){
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }



    public void singIn(String email, String password){

        FAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Exception exception = task.getException();
                    Toast.makeText(getApplicationContext(), "Login faield! : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
//                    System.out.println("Login faield" + exception.getMessage().toString());
                }
            }
        });

    }
}
