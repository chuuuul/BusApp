package com.example.chuuuul.wakeupalarm;

import java.util.Calendar;



import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;



public class AlarmDemoActivity extends Activity

        implements OnClickListener{

    Button button_setAlarm;

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmdemo);
        button_setAlarm = (Button) findViewById(R.id.button_setAlarm);
        button_setAlarm.setOnClickListener(this);
    }

    @Override

    public void onClick(View v) {

        // 알람 매니저에 등록할 인텐트를 만듬
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

        // 알람을 받을 시간을 5초 뒤로 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);

        // 알람 매니저에 알람을 등록
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

}