package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.SetMenuCafeActivity;
import com.example.lenovo.myapplication.unity.Product;
import com.example.lenovo.myapplication.unity.SetMenu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterShowProduct extends RecyclerView.Adapter<AdapterShowProduct.ShowProduct> {
    Context context;
    ArrayList<Product> listProduct;
    public static ArrayList<SetMenu> listSetMenu = new ArrayList<>();

    public AdapterShowProduct() {
    }

    public AdapterShowProduct(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.listProduct = productArrayList;
    }

    @NonNull
    @Override
    public ShowProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_set_menu_cafe,viewGroup,false);
        return new ShowProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowProduct showProduct, final int position) {
        listSetMenu = new ArrayList<>();
        Picasso.with(context)
                .load(listProduct.get(position).getImage_product())
                .resize(90, 90)
                .into(showProduct.imageView);
        showProduct.txtv_name_product.setText(listProduct.get(position).getName_product());
        showProduct.txtv_price_product.setText(listProduct.get(position).getPrice_product());
        showProduct.ll_Item_SetMenuCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "toi click len mon " + listProduct.get(position).getName_product(), Toast.LENGTH_LONG).show();
                listSetMenu.add(new SetMenu(listSetMenu.size() + 1, listProduct.get(position).getName_product(),
                        listProduct.get(position).getPrice_product(), "1"));
                // goi recyclerview

            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ShowProduct extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout ll_Item_SetMenuCafe;
        TextView txtv_name_product,txtv_price_product;
        public ShowProduct(@NonNull View itemView) {
            super(itemView);
            txtv_name_product = itemView.findViewById(R.id.txtv_name_product);
            imageView = itemView.findViewById(R.id.img_image_product);
            txtv_price_product = itemView.findViewById(R.id.txtv_price_product);
            ll_Item_SetMenuCafe =itemView.findViewById(R.id.layout_item_set_menu_cafe);
        }
    }

    public ArrayList<SetMenu> GetListSetMenu(){

        return listSetMenu;
    }
}
