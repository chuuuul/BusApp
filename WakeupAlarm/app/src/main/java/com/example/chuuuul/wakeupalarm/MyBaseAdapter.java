package com.example.chuuuul.wakeupalarm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter
{
    Context context = null;
    ArrayList <TimeFormat> timeFormat  = null;


    public MyBaseAdapter (Context context, ArrayList timeFormat )
    {
        this.context = context;
        this.timeFormat = timeFormat;
    }

    @Override
    public int getCount() {
        return timeFormat.size();
    }

    @Override
    public Object getItem(int position) {
        return timeFormat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout;
        if ( convertView == null)
        {
            itemLayout = View.inflate(context,R.layout.alarm_list_content,null);
        }
        else
        {
            itemLayout = convertView;
        }

        CheckBox checkBox = (CheckBox) itemLayout.findViewById(R.id.checkBox);
        checkBox.setChecked(timeFormat.get(position).toggle);

        TextView timetv = (TextView) itemLayout.findViewById(R.id.timeTextView);
        timetv.setText(timeFormat.get(position).hour + " : " + timeFormat.get(position).min);

        TextView musictv = (TextView) itemLayout.findViewById(R.id.musicTextView);
        musictv.setText(timeFormat.get(position).music);
        return itemLayout;
    }
}
