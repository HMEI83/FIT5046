package com.example.fit5046_ass3;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<Event> allEvent = new ArrayList<>();

    private OnItemClickListener listener;

    public void setAllEvent(List<Event> allEvent) {
        this.allEvent = allEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell,parent,false);
        return new ViewHolder(itemView);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String eventName,String eventDescription, String eventCategory,
                         String eventStartTime, String eventEndTime, String eventAddress, String eventLat, String eventLng);
    }

    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = allEvent.get(position);
        holder.textViewNum.setText(String.valueOf(position+1));
        holder.textViewName.setText(event.getEventName());
        holder.textViewStartTime.setText(event.getEventStartTime());
        holder.textViewLocation.setText(event.getEventAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view,holder.getAdapterPosition(),
                            allEvent.get(holder.getAdapterPosition()).getEventName(),
                            allEvent.get(holder.getAdapterPosition()).getEventDescription(),
                            allEvent.get(holder.getAdapterPosition()).getEventCategory(),
                            allEvent.get(holder.getAdapterPosition()).getEventStartTime(),
                            allEvent.get(holder.getAdapterPosition()).getEventEndTime(),
                            allEvent.get(holder.getAdapterPosition()).getEventAddress(),
                            allEvent.get(holder.getAdapterPosition()).getEventLat(),
                            allEvent.get(holder.getAdapterPosition()).getEventLng());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allEvent.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textViewNum,textViewName,textViewStartTime,textViewLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNum = itemView.findViewById(R.id.textViewNum);
            textViewName = itemView.findViewById(R.id.eventName);
            textViewStartTime = itemView.findViewById(R.id.eventStartTime);
            textViewLocation = itemView.findViewById(R.id.eventLoaction);
        }
    }
}
