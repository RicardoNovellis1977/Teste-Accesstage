package com.example.testeaccesstage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evento {

    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("type")
    private String title;
    @Expose
    @SerializedName("url")
    private String url;

    public Evento(String id, String title) {
        this.id = id;
        this.title = title;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
