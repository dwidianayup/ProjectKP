package com.adwidian.ramaniapi.projectkp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private ListView listView;
    private List<ModelFungsi> modelFungsiList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        RecyclerView view = findViewById(R.id.recyclerV);

//        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        modelFungsiList = databaseAccess.getQuotes();

        databaseAccess.close();

        adapter = new Adapter(this,modelFungsiList);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectkp);
//        this.listView.setAdapter(adapter);
    }
}
