package com.adwidian.ramaniapi.projectkp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListMarkerActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_marker);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        int [] gambar = {
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
                R.drawable.hospital_buildings,
        };

        String [] judul = {
                "Puskesmas Pocol",
                "Puskesmas Pandanaran",
                "Puskesmas Bulu Lor",
                "Puskesmas Miroto",
                "Puskesmas Halmahera",
                "Puskesmas Pembantu Muktiharjo Kidul",
                "Puskesmas Gayamsari",
                "Puskesmas Gubug1",
                "RS Bhakti Wira Tamtama",
                "RS Hermina",
                "RSUP Kariadi",
                "RSU William Booth",
                "SMC Telogorejo"
        };

        Double [] latitude = {
                -6.973777,
                -6.986941,
                -6.975124,
                -6.983519,
                -6.994792,
                -7.000170,
                -6.999884,
                -6.991396,
                -6.987349,
                -6.985648,
                -6.993613,
                -6.996784,
                -6.987919
        };

        Double [] longitude = {
                110.414669,
                110.414635,
                110.406982,
                110.419010,
                110.437767,
                110.435669,
                110.448831,
                110.425618,
                110.407786,
                110.412866,
                110.407265,
                110.405100,
                110.426224
        };

        ArrayList<ModelFungsi> puskesmaslist = new ArrayList<>();

        for (int i=0; i<judul.length; i++){
            ModelFungsi puskesmas = new ModelFungsi(gambar[i],judul[i],latitude[i],longitude[i]);
            puskesmaslist.add(puskesmas);
        }

        Adapter adapter = new Adapter(this,puskesmaslist);
        recyclerView.setAdapter(adapter);
    }

}
