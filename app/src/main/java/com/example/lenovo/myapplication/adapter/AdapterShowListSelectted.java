package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.unity.MenuMysql;
import com.example.lenovo.myapplication.unity.SetMenu;

import java.util.ArrayList;

public class AdapterShowListSelectted extends RecyclerView.Adapter<AdapterShowListSelectted.ShowListSelect> {
    Context context;

    ArrayList<SetMenu> listSetMenu;
    static ArrayList<SetMenu> listSetMenusTemp;
    ArrayList<MenuMysql> listMenuMysql;

    int id;
    private static int check =-1;
    static int indexRemove =-1;

    public AdapterShowListSelectted() {
    }

    public AdapterShowListSelectted(Context context, ArrayList<SetMenu> listSetMenu, int id) {
        this.context = context;
        this.listSetMenu = listSetMenu;
        this.id = id;
    }

    @NonNull
    @Override
    public ShowListSelect onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_set_menu_cafe_list_selected,viewGroup,false);
        return new ShowListSelect(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListSelect showListSelect, final int position) {

        //showListSelect.txtv_stt.setText(String.valueOf(listSetMenu.get(position).getStt()));
        showListSelect.txtv_stt.setText(String.valueOf(position+1));
        showListSelect.txtv_nameProduct.setText(String.valueOf(listSetMenu.get(position).getName_product()));
        showListSelect.txtv_priceProduct.setText(String.valueOf(listSetMenu.get(position).getPrice_product()));
        showListSelect.txtv_countProduct.setText(String.valueOf(listSetMenu.get(position).getCount_product()));
        listSetMenusTemp = listSetMenu;
        showListSelect.txtv_delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSetMenusTemp = new ArrayList<>();
                listSetMenu.remove(position);
                listSetMenusTemp= listSetMenu;
                listSetMenu =new ArrayList<>();
                for (int i=0; i<listSetMenusTemp.size();i++){
                    listSetMenu.add(new SetMenu(i+1,listSetMenusTemp.get(i).getName_product()
                            ,listSetMenusTemp.get(i).getPrice_product(),listSetMenusTemp.get(i).getCount_product()));
                    Log.d("huynt75","thanh cong vi tri "+i+" /"+listSetMenusTemp.get(i).getStt());
                }
                listSetMenusTemp = listSetMenu;
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listSetMenu.size();
    }

    public class ShowListSelect extends RecyclerView.ViewHolder {
        TextView txtv_stt,txtv_nameProduct,txtv_priceProduct,txtv_countProduct, txtv_delete_product;
        public ShowListSelect(@NonNull View itemView) {
            super(itemView);
            txtv_stt = itemView.findViewById(R.id.txtv_stt);
            txtv_nameProduct = itemView.findViewById(R.id.txtv_name_product);
            txtv_priceProduct = itemView.findViewById(R.id.txtv_price_product);
            txtv_countProduct = itemView.findViewById(R.id.txtv_count_product);
            txtv_delete_product = itemView.findViewById(R.id.txtv_delete_product);

        }
    }
    public ArrayList<SetMenu> GetListSetMenu(){

        return listSetMenusTemp;
    }
}
