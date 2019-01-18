package com.example.ricardo.retrifitfilme.helpers;

import com.example.ricardo.retrifitfilme.model.ItemResposta;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/")
    Call<ItemResposta> getFilmes(
            @Query("apikey") String apikey,
            @Query("s") String title,
            @Query("type") String type,
            @Query("page") int page

    );

}
