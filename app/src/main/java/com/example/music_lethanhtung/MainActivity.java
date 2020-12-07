package com.example.music_lethanhtung;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Music> listMusic;
    private MusicAdapter musicAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMusic = new ArrayList<>();

        listMusic.add(new Music("Feliz Navidad","Jose Feliciano", R.raw.feliz_navidad, R.drawable.feliz_navidad,3));
        listMusic.add(new Music("We wish you a merry christmas","Super Simple Songs", R.raw.we_wish_you_a_merry_christmas, R.drawable.we_wish_you_a_merry_christmas,3));
        listMusic.add(new Music("Last Christmas","Taylor Swift", R.raw._last_christmas, R.drawable.last_christmas,4));
        listMusic.add(new Music("Wildest Dreams","Taylor Swift", R.raw.wildest_dreams, R.drawable.wildest_dreams,5));
        listMusic.add(new Music("Anne-Marie","Anne-Marie -2002", R.raw.remember, R.drawable.remember,5));
        listMusic.add(new Music("I Need Your Love","Madilyn Bailey", R.raw.i_need_your_love, R.drawable.i_need_your_lovei_need_your_love,4));
        listMusic.add(new Music("Vì Em Nhớ Anh","Mina Young Cover", R.raw.vi_em_nho_anh, R.drawable.vi_em_nho_anh,4));
        listMusic.add(new Music("I'm gonna lose you","Jasmine Thompson", R.raw.like_i_m_gonna_lose_you, R.drawable.gonna_lose,3));

        recyclerView = findViewById(R.id.rcv);

        musicAdapter = new MusicAdapter(this, listMusic);

        recyclerView.setAdapter(musicAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}