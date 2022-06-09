package com.example.t4_01.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t4_01.R;
import com.example.t4_01.entities.Animes;
import com.example.t4_01.minListAnimes;
import com.example.t4_01.services.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class animeAdapter extends RecyclerView.Adapter<animeAdapter.animeViewHolder>{
    List<Animes> animes;

    public animeAdapter(List<Animes> animes) {
        this.animes = animes;
    }

    @NonNull
    @Override
    public animeAdapter.animeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime,parent,false);
        animeAdapter.animeViewHolder vh= new animeAdapter.animeViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull animeAdapter.animeViewHolder holder, int position) {
        View vw=holder.itemView;

        Animes anime = animes.get(position);
        TextView itemNombre=holder.itemView.findViewById(R.id.tvNombre);
        TextView itemDescipcion=holder.itemView.findViewById(R.id.tvDescripcion);
        //TextView itemUrl=holder.itemView.findViewById(R.id.etUrl);
        ImageView itemImg=holder.itemView.findViewById(R.id.ivAvatar);

        itemNombre.setText(anime.nombre);
       // itemNombre.setText(anime.nombre);
        itemDescipcion.setText(anime.descripcion);

        //String url="https://i.ytimg.com/vi/1roy4o4tqQM/maxresdefault.jpg";
        Picasso.get().load(anime.url).into(itemImg);

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);
                Call<Animes> call=services.findContact(anime.id);

                call.enqueue(new Callback<Animes>() {
                    @Override
                    public void onResponse(Call<Animes> call, Response<Animes> response) {
                        if (!response.isSuccessful()){
                            Log.e("asd1234", "error");
                        }else {

                            Log.i("asdasd12312", new Gson().toJson(response.body()));
                            Log.i("asd32", "Respuesta correcta por id");

                            Intent intent= new Intent(vw.getContext(), minListAnimes.class);


 //                           Log.i("asd32", "Respuesta correcta por id------------ ");
//                            intent.putExtra("img",pokemon.img);
//                            intent.putExtra("name",pokemon.name);
//                            intent.putExtra("number",pokemon.number);
//                            intent.putExtra("region",pokemon.region);
//                            intent.putExtra("tipo",pokemon.tipo);
                            String pokemonJson = new Gson().toJson(anime);
                            intent.putExtra("Pokemon",pokemonJson);

                            vw.getContext().startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Animes> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class animeViewHolder extends RecyclerView.ViewHolder {
        public animeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
