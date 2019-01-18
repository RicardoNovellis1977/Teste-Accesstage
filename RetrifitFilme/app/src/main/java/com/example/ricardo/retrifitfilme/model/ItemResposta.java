package com.example.ricardo.retrifitfilme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResposta {

    @SerializedName("Search")
    @Expose
    List<FilmeModelo> filmeModelos;

    @SerializedName("totalResults")
    @Expose
    int totalResults;

    @SerializedName("Response")
    @Expose
    boolean response;

    public List<FilmeModelo> getFilmeModelos() {
        return filmeModelos;
    }

    public void setFilmeModelos(List<FilmeModelo> filmeModelos) {
        this.filmeModelos = filmeModelos;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
