package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.unity.MenuMysql;

import java.util.ArrayList;

public class AdapterShowHistoryPayment extends RecyclerView.Adapter<AdapterShowHistoryPayment.ShowHistoryPayment> {
    ArrayList<MenuMysql> listShowHistoryPayment;
    Context context;

    public AdapterShowHistoryPayment(ArrayList<MenuMysql> listShowHistoryPayment, Context context) {
        this.listShowHistoryPayment = listShowHistoryPayment;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowHistoryPayment onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_history_payment,viewGroup,false);
        return new ShowHistoryPayment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowHistoryPayment showHistoryPayment, int position) {
        showHistoryPayment.txtv_nameProduct.setText(String.valueOf(listShowHistoryPayment.get(position).getName_product()));
        showHistoryPayment.txtv_priceProduct.setText(String.valueOf(listShowHistoryPayment.get(position).getPrice_product())+"k");
        showHistoryPayment.txtv_countProduct.setText(" "+String.valueOf(listShowHistoryPayment.get(position).getCount_product()));
        showHistoryPayment.txtv_status_product.setText(String.valueOf(listShowHistoryPayment.get(position).getStatus()));
    }

    @Override
    public int getItemCount() {
        return listShowHistoryPayment.size();
    }

    class ShowHistoryPayment extends RecyclerView.ViewHolder {
        TextView txtv_nameProduct,txtv_priceProduct,txtv_countProduct, txtv_status_product;


        public ShowHistoryPayment(@NonNull View itemView) {
            super(itemView);

            txtv_nameProduct = itemView.findViewById(R.id.txtv_name_product);
            txtv_priceProduct = itemView.findViewById(R.id.txtv_price_product);
            txtv_countProduct = itemView.findViewById(R.id.txtv_count_product);
            txtv_status_product = itemView.findViewById(R.id.txtv_status_product);
        }
    }
}
