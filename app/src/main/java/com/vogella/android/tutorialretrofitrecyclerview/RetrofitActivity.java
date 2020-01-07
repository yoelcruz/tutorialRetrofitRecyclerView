package com.vogella.android.tutorialretrofitrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vogella.android.tutorialretrofitrecyclerview.adapter.PersonajeAdapter;
import com.vogella.android.tutorialretrofitrecyclerview.model.Data;
import com.vogella.android.tutorialretrofitrecyclerview.model.Personaje;
import com.vogella.android.tutorialretrofitrecyclerview.webservice.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonajeAdapter adapter;
    private List<Personaje> personajes;

    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        setUpView();
        lanzarPeticion();
    }

    private void lanzarPeticion(){
        //para ver que datos se estan recibiendo en pantalla
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<Data>  call = client.getPersonajes();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //getResults nos devuelve la lista de personajes como hemos hecho en Data de model
                adapter.setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void setUpView(){
        personajes = new ArrayList<>();
        adapter = new PersonajeAdapter(personajes);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }
}
