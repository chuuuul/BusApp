package com.example.chuuuul.wakeupalarm;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {

    private static GlobalApplication mInstance = null;          // 인스턴스 객체 선언
    private static volatile Activity currentActivity = null;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;           // 인스턴스 객체 초기화
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    // static으로 선언하여 매번 객체를 생성하지 않아도 다른 activity에서 사용 가능
    public static Activity getCurrentActivity() {
        Log.d("TAG", "++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }

    public static GlobalApplication getGlobalApplicationContext() {
        if(mInstance == null)
            throw new IllegalStateException("this application does not inherit GlobalApplication");
        return mInstance;
    }
}