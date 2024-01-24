package com.example.omanevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omanevent.models.Event;
import com.example.omanevent.models.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;


public class EventDeatailsFormView extends AppCompatActivity {

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_deatails_form);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

//        Get the user ID
        String user_id = firebaseAuth.getUid();

            TextView EventName = findViewById(R.id.event_name_text);
            TextView FromTo = findViewById(R.id.From_To_text);
            TextView place = findViewById(R.id.place_addres_text);
            TextView price = findViewById(R.id.price_text);
            TextView discreption = findViewById(R.id.discreption_text);

        Button reserve_ticket_button = findViewById(R.id.reserve_ticket_button);

            Event event = (Event) getIntent().getSerializableExtra("event_data");
            FromTo.setText(event.getFrom() +"-"+ event.getTo().toString());
            EventName.setText(event.getName());
//            EventName.setText(event.getName());
//            EventName.setText(event.getName());

        String toDate= DateFormat.getDateInstance(DateFormat.FULL).format(event.getTo());
        String fromDate= DateFormat.getDateInstance(DateFormat.FULL).format(event.getFrom());


        FromTo.setText(fromDate +" - "+ toDate);



        reserve_ticket_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ticket ticket = new Ticket(event.getID(), user_id);
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Ticket").add(ticket).addOnSuccessListener(
                        documentReference -> {
                            Toast.makeText(getApplicationContext(), " Ticket is reserved successfully" , Toast.LENGTH_LONG).show();
                            handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(EventDeatailsFormView.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            },3000);

                        }
                ).addOnFailureListener(e ->{
                    Toast.makeText(getApplicationContext(), "Faild to Reserve Ticket: " + e.getMessage() , Toast.LENGTH_LONG).show();

                });

            }

        });





    }

}