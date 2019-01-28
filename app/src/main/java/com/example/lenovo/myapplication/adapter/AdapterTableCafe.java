package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.SetMenuCafeActivity;
import com.example.lenovo.myapplication.unity.TableCafe;

import java.util.ArrayList;

public class AdapterTableCafe extends RecyclerView.Adapter<AdapterTableCafe.ShowTable> {
    String [] list ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    ArrayList<TableCafe> arrayList;
    Context context;
    public static final String ID="id";
    View view;
    public AdapterTableCafe(ArrayList<TableCafe> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowTable onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_table_cafe,viewGroup,false);
        return new ShowTable(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowTable showTable, final int position) {
       // showTable.img_check_newData.setImageResource(R.color.Algae_Green);
        // Log.d("huynt75","mang"+arrayList.get(6).getId());
        Log.d("huynt75","mang arr tablecafe"+arrayList);
        showTable.textView.setText(""+arrayList.get(position).getIdTable());// list nay mang gia tri int nen ko dua truc tiep vao duoc do do them "" o truoc
        showTable.fr_item_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"Tôi click vi tri "+position,Toast.LENGTH_LONG).show();

                Intent i = new Intent().setClass(context.getApplicationContext(), SetMenuCafeActivity.class);
                int id = arrayList.get(position).getIdTable();
                Log.d("huynt75","putextra"+id);
                i.putExtra(ID,id);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.getApplicationContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        //  Toast.makeText(context,"thanh phan con"+arrayList.get(0),Toast.LENGTH_LONG).show();
        return arrayList.size();
    }

    public class ShowTable extends RecyclerView.ViewHolder {
        ImageView img_check_onoff, img_check_newData;
        TextView textView;
        FrameLayout fr_item_table;
        public ShowTable(@NonNull View itemView) {
            super(itemView);
            img_check_onoff =itemView.findViewById(R.id.img_check_on_off);
            img_check_newData =itemView.findViewById(R.id.img_check_new_data);
            textView = itemView.findViewById(R.id.txtv_id_table);
            fr_item_table = itemView.findViewById(R.id.fr_item_table);
        }
    }
}
