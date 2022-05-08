package com.example.fit5046_ass3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MybookingAdapter extends RecyclerView.Adapter<MybookingAdapter.MyEventViewHolder>{

    List<UserEvent> allUserEventList = new ArrayList<>();

    private OnItemClickListener listener;

    public void setAllUserEventList(List<UserEvent> allUserEventList) {
        this.allUserEventList = allUserEventList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String eventName,
                         String eventStartTime, String eventEndTime, String eventAddress);
    }

    @NonNull
    @Override
    public MyEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card,parent,false);
        return new MyEventViewHolder(itemView);
    }

    public void setOnItemClick(MybookingAdapter.OnItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyEventViewHolder holder, int position) {
        UserEvent userEvent = allUserEventList.get(position);
        holder.textViewNum.setText(String.valueOf(position+1));
        holder.textViewName.setText(userEvent.getEventName());
        holder.textViewStartTime.setText(userEvent.getEventStartTime());
        holder.textViewEndTime.setText(userEvent.getEventEndTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(view,holder.getAdapterPosition(),
                            allUserEventList.get(holder.getAdapterPosition()).getEventName(),
                    allUserEventList.get(holder.getAdapterPosition()).getEventStartTime(),
                            allUserEventList.get(holder.getAdapterPosition()).getEventEndTime(),
                            allUserEventList.get(holder.getAdapterPosition()).getEventLocation());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUserEventList.size();
    }


    static class MyEventViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNum,textViewName,textViewStartTime,textViewEndTime;
        public MyEventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNum = itemView.findViewById(R.id.booking_pos);
            textViewName = itemView.findViewById(R.id.booking_name);
            textViewStartTime = itemView.findViewById(R.id.booking_startTime);
            textViewEndTime =  itemView.findViewById(R.id.booking_endTime);
        }
    }

}