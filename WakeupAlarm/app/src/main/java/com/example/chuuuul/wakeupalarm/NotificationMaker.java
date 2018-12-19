package com.example.chuuuul.wakeupalarm;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationMaker extends Application
{
    String TAG = "chul";
    Context mContext ;
    NotificationManager notificationManager;

    NotificationMaker(Context mContext,NotificationManager notificationManager)
    {
        this.mContext = mContext;
        this.notificationManager = notificationManager;
    }

    public void createNotification(String input)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext,"default");

        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("버스 도착 정보");
        builder.setContentText("버스 도착까지 남은시간" + input);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(input));

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        //builder.setAutoCancel(true);

        notificationManager.notify(1,builder.build() );
    }
}
