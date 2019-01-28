package com.example.lenovo.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AdapterNewsBusiness extends RecyclerView.Adapter<AdapterNewsBusiness.NewsBusiness> {

    @NonNull
    @Override
    public NewsBusiness onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsBusiness newsBusiness, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NewsBusiness extends RecyclerView.ViewHolder {
        public NewsBusiness(@NonNull View itemView) {
            super(itemView);
        }
    }
}
