package com.example.music_lethanhtung;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private ArrayList<Music> listMusics;
    private LayoutInflater layoutInflater;

    public MusicAdapter(Context ctx, ArrayList<Music> listMusic) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.listMusics = listMusic;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_rcv, parent, false);
        return new MusicViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        String name = listMusics.get(position).getName();
        String author = listMusics.get(position).getAuthor();
        int mp3 = listMusics.get(position).getMp3();
        int rateBar = listMusics.get(position).getRateBar();
        int img = listMusics.get(position).getImage();
        holder.tvMusicName.setText(name);
        holder.tvAuthor.setText(author);
        holder.imgMusic.setImageResource(img);
        holder.tvRateBar.setRating(rateBar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityPlay.class);
                intent.putExtra("name", name);
                intent.putExtra("author", author);
                intent.putExtra("rateBar", rateBar);
                intent.putExtra("img", img);
                intent.putExtra("mp3", mp3);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMusics.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMusicName;
        public TextView tvAuthor;
        public RatingBar tvRateBar;
        public ImageView imgMusic;
        public MusicAdapter musicAdapter;
        public MusicViewHolder(@NonNull View itemView, MusicAdapter musicAdapter) {
            super(itemView);
            tvMusicName = itemView.findViewById(R.id.tvMusicName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            imgMusic = itemView.findViewById(R.id.imgMusic);
            tvRateBar = itemView.findViewById(R.id.rateBar);
            this.musicAdapter = musicAdapter;
        }
    }
}
