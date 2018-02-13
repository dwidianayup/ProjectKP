package com.adwidian.ramaniapi.projectkp;

/**
 * Created by apple on 2018/02/12.
 */

public class ModelFungsi {
    private int gambar;
    private String judul;
    private Double latitude;
    private Double longitude;

    public ModelFungsi(int gambar, String judul, Double latitude, Double longitude){
        this.gambar = gambar;
        this.judul = judul;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getGambar(){
        return gambar;
    }
    public String getJudul(){
        return judul;
    }
    public Double getLatitude(){
        return latitude;
    }
    public Double getLongitude(){
        return longitude;
    }
}
