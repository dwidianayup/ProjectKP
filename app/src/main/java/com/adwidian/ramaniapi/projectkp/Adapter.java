package com.adwidian.ramaniapi.projectkp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apple on 2018/02/12.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.PuskesmasViewHolder>{
    private Context context;
    private ArrayList<ModelFungsi> puskesmaslist;

    public Adapter(Context context, ArrayList<ModelFungsi> puskesmaslist){
        this.context = context;
        this.puskesmaslist = puskesmaslist;
    }

    public class PuskesmasViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView judul;

        public PuskesmasViewHolder(View itemView){
            super(itemView);
            gambar = (ImageView)itemView.findViewById(R.id.gambar);
            judul = (TextView)itemView.findViewById(R.id.judul);
        }
    }

    @Override
    public PuskesmasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu,parent,false);
        return new PuskesmasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.PuskesmasViewHolder holder,final int position){
        holder.gambar.setImageResource(puskesmaslist.get(position).getGambar());
        holder.judul.setText(puskesmaslist.get(position).getJudul());

        holder.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("latitude",puskesmaslist.get(position).getLatitude());
                intent.putExtra("longitude",puskesmaslist.get(position).getLongitude());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return puskesmaslist.size();
    }
}
