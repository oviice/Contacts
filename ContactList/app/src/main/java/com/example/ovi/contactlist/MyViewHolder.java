package com.example.ovi.contactlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ovi on 3/31/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textView,numberView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView= (TextView) itemView.findViewById(R.id.row);
        numberView= (TextView) itemView.findViewById(R.id.number);
    }
    void setContent(String s){
        textView.setText(s);
    }
    void setCNumber(String s){
        numberView.setText(s);
    }

}
