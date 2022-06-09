package com.example.t4_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.t4_01.DataBase.AppDatabase;
import com.example.t4_01.adapters.animeAdapter;
import com.example.t4_01.dao.AnimeDao;
import com.example.t4_01.entities.Animes;
import com.example.t4_01.services.servicesWeb;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostrarAnime extends AppCompatActivity {

    public RecyclerView rv;
    List<Animes> animes= new ArrayList<>();
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_anime);

//
//        db = AppDatabase.getDatabase(getApplicationContext());
//        AnimeDao dao = db.userDao();
//
//        List<Animes> animes=dao.getAll();
//        Log.i("APP_VJ20202", new Gson().toJson(animes));

   //     db = AppDatabase.getDatabase(getApplicationContext());

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Animes>> call=services.getContacts();

        call.enqueue(new Callback<List<Animes>>() {
            @Override
            public void onResponse(Call<List<Animes>> call, Response<List<Animes>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    animes=response.body();

                  //  saveInDatabase(animes);

                    animeAdapter adapter=new animeAdapter(animes);


                    rv= findViewById(R.id.rvAnimes);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Animes>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");

            }
        });
    }
    private void saveInDatabase(List<Animes> animes) {
        AnimeDao dao = db.userDao();
        for (Animes anime : animes) {
            dao.create(anime);
        }
    }
}