package com.example.testeaccesstage.helpers;

import com.example.testeaccesstage.model.Evento;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventoService {

    @GET("/events")
    Call<List<Evento>> getEventos(

    );
}
