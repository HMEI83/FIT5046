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
    //private List<EventModel.EventBean> eventBeanList;


    //private List<EventModel> list;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = allEvent.get(position);
        holder.textViewNum.setText(String.valueOf(position+1));
        holder.textViewName.setText(event.getEventName());
        holder.textViewTime.setText(event.getEventTime());

//        EventModel.EventBean eventBean = eventBeanList.get(position);
//        holder.textView.setText(eventBean.getName());
//        holder.textView.setText(eventBean.getDatetime_start());
    }

    @Override
    public int getItemCount() {
        return allEvent.size();
    }

//    public int getItemCount(){
//        return eventBeanList.size();
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textViewNum,textViewName,textViewTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNum = itemView.findViewById(R.id.textViewNum);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }
}
