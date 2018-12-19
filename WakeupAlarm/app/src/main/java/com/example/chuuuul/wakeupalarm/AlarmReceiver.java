package com.example.chuuuul.wakeupalarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

    public class AlarmReceiver extends BroadcastReceiver
    {

    Context mContext;

    NotificationMaker notificationMaker ;
    NotificationManager notificationManager;
    @Override
    public void onReceive(Context mContext, Intent intent) {
        this.mContext = mContext;


        makeNotificationManager();
        notificationMaker = new NotificationMaker(mContext, notificationManager);
        notificationMaker.createNotification( "hi" );

        Toast.makeText(mContext, "alarm Reciver Run", Toast.LENGTH_LONG).show();

         //SystemClock.sleep(2000);

    }

    public void makeNotificationManager()
    {
        //알림표시
        notificationManager = ( NotificationManager ) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationManager.createNotificationChannel(new NotificationChannel("default","기본채널",NotificationManager.IMPORTANCE_DEFAULT));
        }
    }


}
