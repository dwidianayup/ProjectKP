package com.adwidian.ramaniapi.projectkp;

/**
 * Created by apple on 2018/02/12.
 */

public class ModelFungsi {
    private String nama;
    private String alamat;
    private Double latitude;
    private Double longitude;

    public ModelFungsi(String nama, String alamat, Double latitude, Double longitude) {
        this.nama = nama;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public Double getLatitude() {
        return latitude;
    }


    public Double getLongitude() {
        return longitude;
    }
}
