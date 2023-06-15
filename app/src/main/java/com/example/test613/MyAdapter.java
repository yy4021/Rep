package com.example.test613;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<recTool> items;
    Context context;

    public MyAdapter(ArrayList<recTool> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(context).inflate(R.layout.item_rec,parent,false);

        V vh= new V(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        V v= (V)holder;

        recTool item= items.get(position);
        v.fdNmV.setText(item.getFd_Nm());
        v.ckrySumryV.setText(item.getCkry_Sumry_Info());

        if(item.getFood_Image_Address()==null){
            v.iv.setVisibility(View.GONE);
        }else{
            v.iv.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getFood_Image_Address()).into(v.iv);

        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //이너클래스
    class V extends RecyclerView.ViewHolder{

        TextView fdNmV, ckrySumryV;
        ImageView iv;

        public V(@NonNull View itemView) {
            super(itemView);

            fdNmV=itemView.findViewById(R.id.fd_NmV);
            ckrySumryV=itemView.findViewById(R.id.ckry_Sumry_InfoV);
            iv=itemView.findViewById(R.id.iv);
        }
    }
}
