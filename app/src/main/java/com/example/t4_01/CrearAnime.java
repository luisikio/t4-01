package com.example.t4_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t4_01.entities.Animes;
import com.example.t4_01.services.servicesWeb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearAnime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anime);

        Button btn = findViewById(R.id.btnCrearAnime);
        EditText etNombre=findViewById(R.id.etNombre);
        EditText etDescripcion=findViewById(R.id.etDescripcion);
        EditText etUrl=findViewById(R.id.etUrl);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etNombre.getText().toString().isEmpty() && !etDescripcion.getText().toString().isEmpty() && !etUrl.getText().toString().isEmpty()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    servicesWeb services = retrofit.create(servicesWeb.class);

                    Animes animes = new Animes();


                    animes.nombre = String.valueOf(etNombre.getText());
                    animes.descripcion = String.valueOf(etDescripcion.getText());
                    animes.url = String.valueOf(etUrl.getText());

                    Toast.makeText(CrearAnime.this,"Elemento creado",Toast.LENGTH_LONG).show();
                    services.create(animes);
                    Call<Animes> call = services.create(animes);

                    call.enqueue(new Callback<Animes>() {
                        @Override
                        public void onResponse(Call<Animes> call, Response<Animes> response) {

                        }

                        @Override
                        public void onFailure(Call<Animes> call, Throwable t) {

                        }
                    });


                }else{
                    Toast.makeText(CrearAnime.this,"Algun campo esta vacio",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}