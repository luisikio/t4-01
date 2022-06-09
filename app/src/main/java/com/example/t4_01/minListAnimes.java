package com.example.t4_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t4_01.entities.Animes;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class minListAnimes extends AppCompatActivity {

    ImageView imgA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_list_animes);

        String pokemonJson = getIntent().getStringExtra("Pokemon");
        Animes anime = new Gson().fromJson(pokemonJson,Animes.class);

        imgA=findViewById(R.id.imgAvat);
        TextView tvnom=findViewById(R.id.tvnombre);
        TextView tvmResumen=findViewById(R.id.tvdescription);

        tvnom.setText(anime.nombre);
        tvmResumen.setText(anime.descripcion);
        Picasso.get().load(anime.url).into(imgA);


    }
}