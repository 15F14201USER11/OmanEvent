package com.example.omanevent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omanevent.models.Event;
import com.example.omanevent.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketAdabter extends RecyclerView.Adapter<TicketAdabter.EventViewHolder> {

    private List<Ticket> ticketsList = new ArrayList<>();

    public TicketAdabter() {
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
    public TicketAdabter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_ticket, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Ticket ticket = ticketsList.get(position);
        holder.address.setText(ticket.getEvent_id());
        holder.DatePeriod.setText("x"); //event.getFrom() != null ?  event.getFrom().toString(): " x "+" - "+event.getTo() != null ? event.getTo().toString(): "x"
        holder.cost.setText("0");

    }


    @Override
    public int getItemCount() {
        return ticketsList.size();
    }

    public void setTicketsList(List<Ticket> ticketList) {
        this.ticketsList = ticketList;
        notifyDataSetChanged();
    }
}
