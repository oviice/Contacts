package com.example.ovi.contactlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ovi on 3/31/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<Contact> list;
    LayoutInflater inflater;

    MyAdapter(ArrayList<Contact> list, LayoutInflater inflater){
        this.list=list;
        this.inflater=inflater;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setContent(list.get(position).name);
        holder.setCNumber(list.get(position).number);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(ArrayList<Contact> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
