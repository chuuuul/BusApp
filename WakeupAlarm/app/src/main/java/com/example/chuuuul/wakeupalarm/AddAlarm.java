package com.example.chuuuul.wakeupalarm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AddAlarm extends AppCompatActivity
{
    Activity activity;
    String TAG = "chul";
    TimePicker timePicker;
    Button timePickFinishButton;
    Button musicConfigButton;
    public TextView currentMusicTV;


    public String selectedMusicName;

    // ** Input Default music **
    String DEFAULT_MUSIC = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_alarm);
        activity = this;


        currentMusicTV = (TextView) findViewById(R.id.currentMusicTV);
        musicConfigButton = (Button) findViewById(R.id.musicConfigButton);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePickFinishButton = (Button) findViewById(R.id.timePickFinish);

        selectedMusicName = DEFAULT_MUSIC;

        timePickFinishButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onClick: ");
                Intent out_intent = new Intent(getApplicationContext(),AlarmList.class);

                out_intent.putExtra("hour" , timePicker.getHour());
                out_intent.putExtra("min",timePicker.getMinute());

                setResult(RESULT_OK,out_intent);
                finish();
            }
        });


        // 음악 변경 버튼을 누르면 다이얼로그에 리스트를 띄운다.
        musicConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder musicDialogBuilder = new AlertDialog.Builder(AddAlarm.this);   // context값에 뭐가들어가는거지? 그냥 this하면 안됨
                musicDialogBuilder.setTitle("알람음 선택");


                // Dialog에 넣을 리스트 , 어댑터 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        AddAlarm.this, android.R.layout.select_dialog_singlechoice);

                ArrayList <String> listWAV =  SearchMusicInRaw();


                Log.d(TAG, "onClick: " + listWAV.size());
                for (String tmp : listWAV) {
                    adapter.add(tmp);
                }
                // Dialog 취소 버튼 누를시
                musicDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                Log.d(TAG, "onClick:  1");
                // Dialog에 List 어뎁터 연결

                musicDialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick:  2");
                        String musicName = adapter.getItem(which);
                        Toast.makeText(AddAlarm.this, "선택한 곡 : " + musicName, Toast.LENGTH_SHORT).show();
                        selectedMusicName = musicName;
                        currentMusicTV.setText(selectedMusicName);
                    }
                });
                musicDialogBuilder.show();

            }
        });
    }

    // Raw 폴더의 파일 검색해서 Array List 반환
    private ArrayList SearchMusicInRaw()
    {
        Field[] fields = R.raw.class.getFields();

        ArrayList <String> MusicArrayList = new ArrayList<>();

        for(int count=0; count < fields.length; count++)
        {
            String foundMusicName =  fields[count].getName();

            if ( foundMusicName.endsWith("music")  )
            {
                MusicArrayList.add( foundMusicName );
                Log.d(TAG, "Find Wav in Raw : " + foundMusicName);
            }
        }
        return MusicArrayList;

    }

}

