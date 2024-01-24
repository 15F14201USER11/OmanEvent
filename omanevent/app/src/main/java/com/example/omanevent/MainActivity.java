package com.example.omanevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omanevent.models.Event;
import com.example.omanevent.models.Ticket;
import com.example.omanevent.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView_upcoming;
    private RecyclerView recyclerView_ongoing;
    private RecyclerView recyclerView_tickets;
    private  EventAdapter eventAdapter;
    private TicketAdabter ticketAdapter;
    private TextView fullnameText;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regular_user);



        fullnameText = findViewById(R.id.fullname_text);



        recyclerView_upcoming = findViewById(R.id.res_upcoming);
        recyclerView_upcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView_ongoing = findViewById(R.id.res_ongoing);
        recyclerView_ongoing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView_tickets = findViewById(R.id.res_tickets);
        recyclerView_tickets.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        eventAdapter = new EventAdapter(this);
        ticketAdapter = new TicketAdabter();

        recyclerView_upcoming.setAdapter(eventAdapter);
        recyclerView_ongoing.setAdapter(eventAdapter);
        recyclerView_tickets.setAdapter(ticketAdapter);


        firebaseFirestore = FirebaseFirestore.getInstance();


        firebaseFirestore.collection("Events").get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<Event> events = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot: task.getResult()){
                            Event event = documentSnapshot.toObject(Event.class);
                            event.setID(documentSnapshot.getId());
                            events.add(event);
                        }

//                        List<Event> events = task.getResult().toObjects(Event.class);
                        eventAdapter.setEventList(events);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error: Can't Fetch Data", Toast.LENGTH_LONG).show();
                    }
                }
        );

        firebaseFirestore.collection("Ticket").get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){

                        List<Ticket> tickets = task.getResult().toObjects(Ticket.class);
                        ticketAdapter.setTicketsList(tickets);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error: Can't Fetch Data", Toast.LENGTH_LONG).show();
                    }
                }
        );

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        firebaseFirestore.collection("Users").document(userID).get().addOnSuccessListener(documentSnapshot -> {
           if(documentSnapshot.exists()){
               User user = documentSnapshot.toObject(User.class);
               if(user!=null){
                   fullnameText.setText(user.getFullName());
               }
               else{
                   fullnameText.setText("Unknown");
               }
           }
        }).addOnFailureListener(
                e->{
                    Toast.makeText(getApplicationContext(), "Error: Can't User Fetch Data" + e.getMessage(), Toast.LENGTH_LONG).show();

                }
        );



    }
}