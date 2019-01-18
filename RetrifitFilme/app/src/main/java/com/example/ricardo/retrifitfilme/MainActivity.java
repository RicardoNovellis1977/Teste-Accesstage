package com.example.ricardo.retrifitfilme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ricardo.retrifitfilme.adapter.AdapterFilme;
import com.example.ricardo.retrifitfilme.helpers.MovieService;
import com.example.ricardo.retrifitfilme.helpers.RetrofitConfig;
import com.example.ricardo.retrifitfilme.model.FilmeModelo;
import com.example.ricardo.retrifitfilme.model.ItemResposta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FilmeModelo> filmeModeloList = new ArrayList<>();

    public static final String APIKEY = "fefaa5df";
    public static final String TYPE = "movie";
   // public static final String PLOT = "full";
    public static final String TITLE = "vida";
    public  int page = 1;
    public  int total = 3;
    private AdapterFilme adapterFilme;
    private MovieService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        adapterFilme = new AdapterFilme(filmeModeloList);
        service = RetrofitConfig.buildRetrofit().create(MovieService.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //((LinearLayoutManager) layoutManager).setOrientation(HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterFilme);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                verifyIsLast(recyclerView);
            }
        });

        callServiceMovie(adapterFilme, service);
    }

    private void callServiceMovie(final AdapterFilme adapterFilme, MovieService service) {
        Call<ItemResposta> call = service.getFilmes(APIKEY,TITLE,TYPE,page);

        call.enqueue(new Callback<ItemResposta>() {
            @Override
            public void onResponse(final Call<ItemResposta> call, Response<ItemResposta> response) {
                    filmeModeloList = response.body().getFilmeModelos();
                    adapterFilme.adicioarFilme(filmeModeloList);
            }

            @Override
            public void onFailure(Call<ItemResposta> call, Throwable t) {

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
            callServiceMovie(adapterFilme, service);
        }
    }
}
