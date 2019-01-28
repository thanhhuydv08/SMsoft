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

public class AdapterShowBill extends RecyclerView.Adapter<AdapterShowBill.ShowBill> {

    Context context;
    ArrayList<MenuMysql> menuMysqlBill;

    public AdapterShowBill(Context context, ArrayList<MenuMysql> menuMysqlBill) {
        this.context = context;
        this.menuMysqlBill = menuMysqlBill;
    }

    @NonNull
    @Override
    public ShowBill onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill,viewGroup,false);
        return new ShowBill(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBill showBill, int position) {
        showBill.txtv_stt.setText(String.valueOf(position+1));
        showBill.txtv_nameProduct.setText(String.valueOf(menuMysqlBill.get(position).getName_product()));
        showBill.txtv_priceProduct.setText(String.valueOf(menuMysqlBill.get(position).getPrice_product()));
        showBill.txtv_countProduct.setText(String.valueOf(menuMysqlBill.get(position).getCount_product()));
        showBill.txtv_status_product.setText(String.valueOf(menuMysqlBill.get(position).getStatus()));


    }

    @Override
    public int getItemCount() {
        return menuMysqlBill.size();
    }

    public  class ShowBill extends RecyclerView.ViewHolder {
        TextView txtv_stt,txtv_nameProduct,txtv_priceProduct,txtv_countProduct, txtv_status_product;

      public ShowBill(@NonNull View itemView) {
          super(itemView);
          txtv_stt = itemView.findViewById(R.id.txtv_stt);
          txtv_nameProduct = itemView.findViewById(R.id.txtv_name_product);
          txtv_priceProduct = itemView.findViewById(R.id.txtv_price_product);
          txtv_countProduct = itemView.findViewById(R.id.txtv_count_product);
          txtv_status_product = itemView.findViewById(R.id.txtv_status_product);
      }
  }
}
