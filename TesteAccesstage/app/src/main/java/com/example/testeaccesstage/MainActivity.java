package com.example.testeaccesstage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.testeaccesstage.adapter.ListaAdapter;
import com.example.testeaccesstage.helpers.EventoService;
import com.example.testeaccesstage.helpers.RetrofitConfig;
import com.example.testeaccesstage.model.Evento;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public List<Evento> eventoList = new ArrayList<>() ;
    ListaAdapter listaAdapter;
    EventoService service;
    public  int page = 1;
    public  int total = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_evento);

        listaAdapter = new ListaAdapter(eventoList);

        service = RetrofitConfig.buildRetrofit().create(EventoService.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listaAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                verifyIsLast(recyclerView);
            }
        });

        callServiceEvento(listaAdapter, service);
    }
     private void callServiceEvento (final ListaAdapter listaAdapter, EventoService service){


        Call<List<Evento>> call = service.getEventos();

         call.enqueue(new Callback<List<Evento>>() {
             @Override
             public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {

                 eventoList = response.body();
                 listaAdapter.adicioarEvento(eventoList);

                 for (int i=0; i<eventoList.size(); i++){
                     Evento evento = eventoList.get(i);
                     Log.d("resultado","resultado" + evento.getId() + " / " + evento.getTitle() );
                 }

             }

             @Override
             public void onFailure(Call<List<Evento>> call, Throwable t) {

             }
         });
     }
    private void verifyIsLast(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        int lastVisible = layoutManager.findLastVisibleItemPosition();

        boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;

        if (totalItemCount > 0 && endHasBeenReached && page <= total) {
            //you have reached to the bottom of your recycler view
            page++;
            callServiceEvento(listaAdapter,service);
        }
    }
}
