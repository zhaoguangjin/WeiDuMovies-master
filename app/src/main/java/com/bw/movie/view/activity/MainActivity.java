package com.bw.movie.view.activity;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;


public class MainActivity extends AppCompatActivity {

    private int time;
    //延迟一秒，Handler跳转
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (time<=1){
                startActivity(new Intent(MainActivity.this,StartActivity.class));
                finish();
                return;
            }
            time--;
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time=3;
        //延迟一秒发送
        handler.sendEmptyMessageDelayed(1,1000);


    }
}
