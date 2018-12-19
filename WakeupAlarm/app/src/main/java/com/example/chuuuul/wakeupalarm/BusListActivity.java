package com.example.chuuuul.wakeupalarm;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class BusListActivity extends AppCompatActivity {

    final String TAG = "chul";
    public String dataKey = "UxEspJTxvHVRy9D6B875bgGNESs4W0p%2FrVlPWjgvoWyAcXDcEd5tt1JclOgg3Bzb71Hv13i97umcV4w4V1%2FPqg%3D%3D";
    private String requestUrl;
    ArrayList<Item> list = null;
    Item bus = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        AsyncTask
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();


    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapitraffic.daejeon.go.kr/api/rest/arrive/getArrInfoByUid?serviceKey=" +dataKey+ "&arsId=32350";
            try {
                boolean b_BUS_NODE_ID = false;
                //boolean b_BUS_STOP_ID =false;
                boolean b_EXTIME_SEC = false;
                boolean b_ROUTE_NO = false;
                boolean b_STATUS_POS = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("itemList") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("itemList")){
                                bus = new Item();
                            }
                            if (parser.getName().equals("BUS_NODE_ID")) b_BUS_NODE_ID = true;
                            //if (parser.getName().equals("BUS_STOP_ID")) b_BUS_STOP_ID = true;
                            if (parser.getName().equals("EXTIME_SEC")) b_EXTIME_SEC = true;
                            if (parser.getName().equals("ROUTE_NO")) b_ROUTE_NO = true;
                            if (parser.getName().equals("STATUS_POS")) b_STATUS_POS =true;
                            break;
                        case XmlPullParser.TEXT:
                            if(b_BUS_NODE_ID){
                                bus.setBUS_NODE_ID(parser.getText());
                                b_BUS_NODE_ID = false;
                            //} else if(b_BUS_STOP_ID) {
                              //  bus.setBUS_STOP_ID(parser.getText());
                              //  b_BUS_STOP_ID = false;
                            } else if (b_EXTIME_SEC) {
                                bus.setEXTIME_SEC(parser.getText());
                                b_EXTIME_SEC = false;
                            } else if(b_ROUTE_NO) {
                                bus.setROUTE_NO(parser.getText());
                                b_ROUTE_NO = false;
                            } else if(b_STATUS_POS)
                            {
                                bus.setSTATUS_POS(parser.getText());
                                b_STATUS_POS = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                Log.d(TAG, "doInBackground: " + e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e(TAG, "onPostExecute: " + list);
            //어답터 연결
            BusAdapter adapter = new BusAdapter(getApplicationContext(), list);

            int count = 0;
            recyclerView.setAdapter(adapter);
        }
    }
}
