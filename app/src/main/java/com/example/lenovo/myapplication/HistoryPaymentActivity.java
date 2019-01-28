package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.myapplication.adapter.AdapterShowHistoryPayment;
import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.MenuMysql;

import java.util.ArrayList;

public class HistoryPaymentActivity extends AppCompatActivity {
    RecyclerView rcl_show_history_payment;
    EditText edt_date;
    Button btn_cancel,btn_view;
    private String urlGetHistoryPayment= "http://thanhhuydv08.000webhostapp.com/getdataHistoryPayment.php";
    public static MenuMysql menuMysql;
    ArrayList<MenuMysql> listShowHistoryPayment;
    ArrayList<MenuMysql> listShowHistoryPaymentTemp;
    AdapterShowHistoryPayment adapterShowHistoryPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_payment);
        Anhxa();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),linearLayoutManager.getOrientation());
        rcl_show_history_payment.setLayoutManager(linearLayoutManager);
        rcl_show_history_payment.addItemDecoration(dividerItemDecoration);
        ControllerDataMysql.getInstance().GetAndRefreshData(3, urlGetHistoryPayment, getApplicationContext(), new UpdateDataListener() {
            @Override
            public void onLoadSuccess(ArrayList arrayList) {
                listShowHistoryPayment = new ArrayList<>();
                listShowHistoryPayment=arrayList;
                btn_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(listShowHistoryPayment.size()>0){

                            FilterList();
                            //Log.d("huynt75","size temp"+listShowHistoryPaymentTemp.size());
                            adapterShowHistoryPayment= new AdapterShowHistoryPayment(listShowHistoryPaymentTemp,getApplicationContext());
                            rcl_show_history_payment.setAdapter(adapterShowHistoryPayment);
                        }else {
                            Toast.makeText(getApplicationContext(),"Hệ thông server  đang lỗi, Vui lòng thử lại !!!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

       // AdapterShowHistoryPayment adapterShowHistoryPayment = new



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       btn_cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
public void Anhxa(){
        rcl_show_history_payment = findViewById(R.id.rcl_show_history_payment);
        btn_cancel = findViewById(R.id.btn_cancel_showhistorypayment);
        edt_date = findViewById(R.id.edt_date_product);
    btn_view = findViewById(R.id.btn_preview_showhistorypayment);
}
public void FilterList(){
        listShowHistoryPaymentTemp = new ArrayList<>();
        if(TextUtils.isEmpty(edt_date.getText().toString())){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập ngày cần lấy dữ liệu !!!!",Toast.LENGTH_LONG).show();
        }else {
            Log.d("huynt75","size temp"+listShowHistoryPayment.size());
            for (int i=0;i<listShowHistoryPayment.size();i++){
                if(listShowHistoryPayment.get(i).getDate().equals(edt_date.getText().toString())){
                    listShowHistoryPaymentTemp.add(new MenuMysql(listShowHistoryPayment.get(i).getId(),listShowHistoryPayment.get(i).getName_product(),
                            listShowHistoryPayment.get(i).getPrice_product(),listShowHistoryPayment.get(i).getCount_product(),listShowHistoryPayment.get(i).getStatus(),
                            listShowHistoryPayment.get(i).getDate()));
                    Log.d("huynt75","size temp"+listShowHistoryPaymentTemp.size());
                }
            }
        }
}
}
