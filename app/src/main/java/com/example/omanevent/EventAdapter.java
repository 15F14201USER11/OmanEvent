package com.example.omanevent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omanevent.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList = new ArrayList<>();
    private Context context;

    public EventAdapter() {
    }
    public EventAdapter(Context context) {
        this.context = context;
    }


    public static class EventViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView DatePeriod;

        TextView address;
        TextView cost;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewTitle);
            DatePeriod = itemView.findViewById(R.id.textViewPeriod);
            address = itemView.findViewById(R.id.textViewADdress);
            cost = itemView.findViewById(R.id.textViewCost);


        }
    }


    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.address.setText(event.getName());
        holder.DatePeriod.setText("x"); //event.getFrom() != null ?  event.getFrom().toString(): " x "+" - "+event.getTo() != null ? event.getTo().toString(): "x"
        holder.cost.setText("0");

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull List<Object> payloads) {
        Event event = eventList.get(position);

        System.out.println(context);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventDeatailsFormView.class);
            intent.putExtra("event_data", event);
            context.startActivity(intent);
        });
    }
}
