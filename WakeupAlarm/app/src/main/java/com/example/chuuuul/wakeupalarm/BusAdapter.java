package com.example.chuuuul.wakeupalarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyViewHolder> {


    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public BusAdapter(Context context, ArrayList<Item> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.BUS_NODE_ID.setText(mList.get(position).BUS_NODE_ID);
        //holder.BUS_STOP_ID.setText(mList.get(position).BUS_STOP_ID);
        holder.EXTIME_SEC.setText(mList.get(position).EXTIME_SEC);
        holder.ROUTE_NO.setText(mList.get(position).ROUTE_NO);
        holder.STATUS_POS.setText(mList.get(position).STATUS_POS);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView BUS_NODE_ID;
        //public TextView BUS_STOP_ID;
        public TextView EXTIME_SEC;
        public TextView ROUTE_NO;
        public TextView STATUS_POS;

        public MyViewHolder(View itemView) {
            super(itemView);

            BUS_NODE_ID = itemView.findViewById(R.id.tv_BUS_NODE_ID);
            //BUS_STOP_ID = itemView.findViewById(R.id.tv_BUS_STOP_ID);
            EXTIME_SEC = itemView.findViewById(R.id.tv_EXTIME_SEC);
            ROUTE_NO = itemView.findViewById(R.id.tv_ROUTE_NO);
            STATUS_POS = itemView.findViewById(R.id.tv_STATUS_POS);
        }
    }

}