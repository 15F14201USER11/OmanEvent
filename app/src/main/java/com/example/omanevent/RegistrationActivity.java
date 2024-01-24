package com.example.omanevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omanevent.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth FAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText full_name = findViewById(R.id.fullname_texbox_reg);
        EditText mobile_number = findViewById(R.id.mobileno_texbox_reg);
        EditText email = findViewById(R.id.email_texbox_reg);
        EditText password = findViewById(R.id.passwword_textbox_reg);
        EditText confirm_password = findViewById(R.id.confirm_passwword_textbox_reg);

        Button create_user_button = findViewById(R.id.signup_button);

        create_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname_value = full_name.getText().toString().trim();
                int mobile_number_value = Integer.parseInt(mobile_number.getText().toString().trim());
                String email_value = email.getText().toString().trim();
                String password_value = password.getText().toString().trim();
                String confirm_password_value = confirm_password.getText().toString().trim();
                if(password_value.compareTo(confirm_password_value) == 0){
                    FAuth.createUserWithEmailAndPassword(email_value, password_value).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();

                                // saving user's Data
//                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
////                                User user = new User(email_value, fullname_value,mobile_number_value);
////                                databaseReference.child(userID).setValue(user);
                                saveUsersData(userID, fullname_value, email_value, mobile_number_value);


                                Toast.makeText(getApplicationContext(), "The account is created successfully!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Exception exception = task.getException();
                                Toast.makeText(getApplicationContext(), "Can\'t Create Account Now Please try again later! " + exception.getMessage(), Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Error: The passwords are Not matched! please check", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public  void onLoginTextClick(View view){
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void saveUsersData(String userID, String fullname, String email, int mobile_no){
        // saving user's Data

        try{
            User user = new User(email, fullname, mobile_no);
            CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users");
            collectionReference.document(userID).set(user);
        }

        catch (Exception e){
            System.out.println("The Error -> " + e.getMessage());
        }


    }
}