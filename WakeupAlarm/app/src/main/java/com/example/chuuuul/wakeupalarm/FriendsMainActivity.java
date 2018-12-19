package com.example.chuuuul.wakeupalarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kakao.friends.AppFriendContext;
import com.kakao.friends.api.*;
import com.kakao.friends.response.AppFriendsResponse;
import com.kakao.util.KakaoUtilService;

public class FriendsMainActivity extends AppCompatActivity {

    String TAG = "chul";

    AppFriendContext AppFriendContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friend);
    }

}
