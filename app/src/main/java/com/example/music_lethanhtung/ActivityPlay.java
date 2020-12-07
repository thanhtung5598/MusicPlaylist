package com.example.music_lethanhtung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.music_lethanhtung.MyService.MyBinder;

public class ActivityPlay extends AppCompatActivity {

    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;

    TextView tvName;
    TextView tvActivityAuthor;
    RatingBar ratingBar;
    ImageView imgMusic;

    ImageView imgPLay;
    ImageView imgNext;
    ImageView imgPrevious;
    ImageView imgPause;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        tvName = findViewById(R.id.tvName);
        tvActivityAuthor = findViewById(R.id.tvActivityAuthor);
        ratingBar = findViewById(R.id.ratingBar);
        imgMusic = findViewById(R.id.img);

        imgPLay = findViewById(R.id.imgPlay);
        imgNext = findViewById(R.id.imgNext);
        imgPrevious = findViewById(R.id.imgPrevious);
        imgPause = findViewById(R.id.imgPause);
        imgBack = findViewById(R.id.imgBack);

        // get intent from apdater
        Intent intentFromAdapter = getIntent();
        // set value
        String name = intentFromAdapter.getStringExtra("name");
        String author = intentFromAdapter.getStringExtra("author");
        int rateBar =  intentFromAdapter.getIntExtra("rateBar",0);
        int mp3 = intentFromAdapter.getIntExtra("mp3", 0);
        int img = intentFromAdapter.getIntExtra("img", 0);

        tvName.setText(name);
        tvActivityAuthor.setText(author);
        ratingBar.setRating(rateBar);
        imgMusic.setImageResource(img);

        // Khởi tạo ServiceConnection
        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBinder binder = (MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyService
                isBound = true;
            }
        };

        // Khởi tạo intent
        final Intent intent =
                new Intent(ActivityPlay.this,
                        MyService.class);
        intent.putExtra("mp3", mp3);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        imgPLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bắt đầu một service sủ dụng bind
                myService.play();
                imgPLay.setVisibility(View.INVISIBLE);
                imgPause.setVisibility(View.VISIBLE);
                System.out.println("play");
                isBound = true;
            }
        });

        imgPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Nếu Service đang hoạt động
                if(isBound){
                    myService.pause();
                    imgPLay.setVisibility(View.VISIBLE);
                    imgPause.setVisibility(View.INVISIBLE);
                    System.out.println("pause");
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound) {
                    // Tắt Service
                    unbindService(connection);
                    isBound = false;
                    finish();
                }
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // nếu service đang hoạt động
                if(isBound){
//                    unbindService(connection);
//                    isBound = false;
//                    finish();
//                    intent.putExtra("mp3", mp3);
//                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                    // tua bài hát
                    myService.fastForward();
                }else{
                    Toast.makeText(ActivityPlay.this,
                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    // tua nguoc bài hát
                    myService.fastBack();
                }else{
                    Toast.makeText(ActivityPlay.this,
                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}