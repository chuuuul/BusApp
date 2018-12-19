package com.example.chuuuul.wakeupalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AlarmList extends AppCompatActivity
{
    static final int local_config_RequestCode = 101;

    ListView listView;
    Button addAlarmButton ;
    ArrayList <TimeFormat> timeFormatList ;
    MyBaseAdapter adapter;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);



        addAlarmButton = (Button)findViewById(R.id.AddAlarmButton);
        listView = (ListView)findViewById(R.id.listView);
        timeFormatList = new ArrayList<TimeFormat>();


        // #### todo : 알람리스트 : 앱 켰을때 저장 되어있는것을 가져와야함
        adapter = new MyBaseAdapter(this,timeFormatList);
        listView.setAdapter(adapter);


        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),AddAlarm.class);
                startActivityForResult(intent,local_config_RequestCode);



            }
        });
    }


    // Time Picker에서 설정한 시간을 리턴 받는다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == local_config_RequestCode) {
            if (resultCode == RESULT_OK) {

                int hour = data.getIntExtra("hour", -1);
                int min = data.getIntExtra("min", -1);
                String music = data.getStringExtra("music");
                boolean toggle = true;

                timeFormatList.add(new TimeFormat(toggle, hour, min,music));
                refreshAlarmList();

            }
        }
    }

    private void refreshAlarmList ()
    {
        adapter = new MyBaseAdapter(this,timeFormatList);
        listView.setAdapter(adapter);
    }
}
