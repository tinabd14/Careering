package com.example.careering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForSearch extends RecyclerView.Adapter<AdapterForSearch.ViewHolder> {

    private List<Post> myData;
    private LayoutInflater myInflater;
    private ItemClickListener myClickListener;

    // data is passed into the constructor
    public AdapterForSearch(Context context, List<Post> data) {
        this.myInflater = LayoutInflater.from(context);
        this.myData = data;
    }

    @NonNull
    @Override
    public AdapterForSearch.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myInflater.inflate(R.layout.search_post_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForSearch.ViewHolder holder, int position) {
        String name = myData.get(position).getName();
        if(myData.get(position) != null || !myData.get(position).getCompany().equals("") && !myData.get(position).getName().equals(""))
        {
            name = myData.get(position).getName() + ", " + myData.get(position).getCompany();
        }
        holder.myTextView.setText(name);
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return myData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.post_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (myClickListener != null) myClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Post getItem(int id) {
        return myData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.myClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
