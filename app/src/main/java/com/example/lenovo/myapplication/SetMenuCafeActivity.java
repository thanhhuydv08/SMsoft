package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.adapter.AdapterShowBill;
import com.example.lenovo.myapplication.adapter.AdapterShowListSelectted;
import com.example.lenovo.myapplication.adapter.AdapterShowProduct;
import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.MenuMysql;
import com.example.lenovo.myapplication.unity.Product;
import com.example.lenovo.myapplication.unity.SetMenu;

import java.util.ArrayList;
import java.util.Calendar;

public class SetMenuCafeActivity extends AppCompatActivity {
    RecyclerView recyclerView_menu, rcl_preview_list_selectted, recyclerView_Bill;
    private String urlGetProduct = "http://thanhhuydv08.000webhostapp.com/getdataproduct.php";
    private String urlupdateProduct = "http://thanhhuydv08.000webhostapp.com/update_product.php";
    private String urlGetBill = "http://thanhhuydv08.000webhostapp.com/getdataBill.php";
    String   urlInsertMenu ="http://thanhhuydv08.000webhostapp.com/insert_menu.php";
    public static ArrayList thongBao= new ArrayList();
    public static MenuMysql menuMysql;
    private AdapterShowProduct adapterProduct;
    private AdapterShowBill adapterBill;
    private String statusPayment ="notPayment";
    Button btn_thanhtoan, btn_huy;
    private AdapterShowListSelectted adapterListSelectted;
    ArrayList<SetMenu> listSetMenu;
    ArrayList<MenuMysql> listMenuMysqlTemp;
    private LinearLayout linearLayoutMenu,ll_show_hide_list_selectted;
    // alert dialog
    Button btn_dialog_xacnhan,btn_dialog_huy;
    TextView txtv_dialog_count,txtv_dialog_monney;
    AlertDialog.Builder alertDialog;
    AlertDialog dialog;
    //</>
    TextView id_Table ;
    int id;
    int countMenuOfTable=0;
    int moneyOfTable=0;
    String dateNow;
    int keyChoiceSourceListSetMenu =0;
    LinearLayout  ll_view_list_selectted , ll_memu_show_hide_product, ll_infor_bill, ll_show_hide_thongtinhoadon;
    Button btn_show_hide_xacNhan,btn_show_hide_huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_menu_cafe);
        AnhXa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_activity_setmenucafe);
        Intent intent = this.getIntent();
        // position=intent.getIntExtra("id",0);
        id = intent.getIntExtra("id",0);
        id_Table.setText(""+id);
        // new ReloaddingWait().CallDialog(SetMenuCafeActivity.this);      huynt75

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 5, GridLayoutManager.VERTICAL, true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),linearLayoutManager.getOrientation());
        recyclerView_Bill.setLayoutManager(linearLayoutManager);
        recyclerView_menu.setLayoutManager(gridLayoutManager);
        recyclerView_Bill.addItemDecoration(dividerItemDecoration);
        LoadDataToRecyclerviewShowProduct(urlGetProduct);


        linearLayoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_memu_show_hide_product.getVisibility()== View.GONE){
                    ll_memu_show_hide_product.setVisibility(View.VISIBLE);
                }else {
                    ll_memu_show_hide_product.setVisibility(View.GONE);
                }

            }
        });

        ll_view_list_selectted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRecyclerviewListSelect();
                if(ll_show_hide_list_selectted.getVisibility()== View.GONE){
                    ll_show_hide_list_selectted.setVisibility(View.VISIBLE);
                }else {
                    ll_show_hide_list_selectted.setVisibility(View.GONE);
                }
            }
        });
        ll_infor_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDataToRecyclerviewShowBill(urlGetBill);
                if(ll_show_hide_thongtinhoadon.getVisibility()== View.GONE){
                    ll_show_hide_thongtinhoadon.setVisibility(View.VISIBLE);
                }else {
                    ll_show_hide_thongtinhoadon.setVisibility(View.GONE);
                }
            }
        });

        btn_show_hide_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                dateNow= String.valueOf(c.get(Calendar.DAY_OF_MONTH))+"/"+String.valueOf(c.get(Calendar.MONTH)+1)+"/"+String.valueOf(c.get(Calendar.YEAR));
                Log.d("huynt75", "thời gian hiện tại " + dateNow);
                listSetMenu = new AdapterShowListSelectted().GetListSetMenu();
                if(listSetMenu!=null) {
                    for (int i = 0; i < listSetMenu.size(); i++) {
                        menuMysql = new MenuMysql(id, listSetMenu.get(i).getName_product(), listSetMenu.get(i).getPrice_product(),
                                listSetMenu.get(i).getCount_product(), "notpayment", dateNow);
                        Log.d("huynt75", "toi o add menumysql" + listSetMenu.get(i).getName_product());
                        // new ReloaddingWait().CallDialog(SetMenuCafeActivity.this,1);
                        ControllerDataMysql.getInstance().PostData(urlInsertMenu, getApplicationContext(), null, menuMysql,null, 0, 3, new UpdateDataListener() {
                            @Override
                            public void onLoadSuccess(ArrayList arrayList) {
                                thongBao = arrayList;
                                Log.d("huynt75", "add menu mysql " + thongBao);
                            }
                        });
                    }
                    ll_show_hide_list_selectted.setVisibility(View.GONE);
                }


            }
        });
        btn_show_hide_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_show_hide_list_selectted.setVisibility(View.GONE);
            }
        });
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumInfo();
                CallAlertDialogCustom();
                // t?o dè nó d? k? th?a view trên nhu 1 alertdialog va khi dó ta s? có nhi?u thu?c tính hon
                btn_dialog_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(listMenuMysqlTemp!=null) {

                            for (int i = 0; i < listMenuMysqlTemp.size(); i++) {

                                // updata len mýql
                                menuMysql = new MenuMysql(listMenuMysqlTemp.get(i).getId(), listMenuMysqlTemp.get(i).getName_product(), listMenuMysqlTemp.get(i).getPrice_product(),
                                        listMenuMysqlTemp.get(i).getCount_product(), "finish", listMenuMysqlTemp.get(i).getDate());
                                Log.d("huynt75", "toi o add menumysql" + listMenuMysqlTemp.get(i).getName_product());
                                // new ReloaddingWait().CallDialog(SetMenuCafeActivity.this,1);
                                ControllerDataMysql.getInstance().PostData(  urlupdateProduct   , getApplicationContext(), null, menuMysql,null, 0, 3, new UpdateDataListener() {
                                    @Override
                                    public void onLoadSuccess(ArrayList arrayList) {
                                        thongBao = arrayList;
                                        Log.d("huynt75", "update product mysql " + thongBao);
                                    }
                                });
                            }
                            ll_show_hide_thongtinhoadon.setVisibility(View.GONE);
                        }
                        dialog.cancel();
                    }
                });
                btn_dialog_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show(); // ti?n hành show dialog v?a crete trên d? hi?n th? trên view chính có view con
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_show_hide_thongtinhoadon.setVisibility(View.GONE);
            }
        });
    }

    private void CallAlertDialogCustom() {
        alertDialog = new AlertDialog.Builder(SetMenuCafeActivity.this);
        alertDialog.setCancelable(false);
        final View alertView = getLayoutInflater().inflate(R.layout.item_dialog_confirm_bill,null);
        btn_dialog_xacnhan = alertView.findViewById(R.id.btn_dialog_xacnhan);
        btn_dialog_huy = alertView.findViewById(R.id.btn_dialog_huy);
        txtv_dialog_count = alertView.findViewById(R.id.txtv_dialog_count);
        txtv_dialog_monney =alertView.findViewById(R.id.txtv_dialog_monney);
        txtv_dialog_count.setText(String.valueOf(countMenuOfTable));
        txtv_dialog_monney.setText(String.valueOf(moneyOfTable));
        alertDialog.setView(alertView);// g?n alertDialog là cái view tôi mu?n hi?n exercise_login_alertdialog
        dialog = alertDialog.create();
    }

    private void AnhXa() {
        id_Table = findViewById(R.id.txtv_id_Table);
        linearLayoutMenu = findViewById(R.id.layout_menu);
        recyclerView_menu = findViewById(R.id.rcl_setmenu_menu);
        recyclerView_Bill = findViewById(R.id.rcl_Bill);
        rcl_preview_list_selectted = findViewById(R.id.rcl_preview_list_selectted);
        ll_view_list_selectted = findViewById(R.id.ll_view_list_selectted);
        ll_show_hide_list_selectted = findViewById(R.id.ll_show_hide_list_selectted);
        ll_memu_show_hide_product = findViewById(R.id.ll_memu_show_hide_product);
        ll_infor_bill = findViewById(R.id.ll_infor_bill);
        ll_show_hide_thongtinhoadon =findViewById(R.id.ll_show_hide_thongtinhoadon);
        btn_show_hide_xacNhan = findViewById(R.id.btn_show_hide_xacNhan);
        btn_show_hide_huy = findViewById(R.id.btn_show_hide_huy);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        btn_huy = findViewById(R.id.btn_Huy);

    }

    private void LoadDataToRecyclerviewShowProduct(String url) {
        ControllerDataMysql.getInstance().GetAndRefreshData(1,
                url,
                getApplicationContext(),
                new UpdateDataListener<Product>() {
                    @Override
                    public void onLoadSuccess(ArrayList<Product> data) {
                        Log.d("huynt75","tao view menu "+data.size());
                        adapterProduct = new AdapterShowProduct(getApplicationContext(),data);
                        recyclerView_menu.setAdapter(adapterProduct);
                        adapterProduct.notifyDataSetChanged();

                    }
                });

    }
    private void LoadDataToRecyclerviewShowBill(String url) {
        ControllerDataMysql.getInstance().GetAndRefreshData(3,
                url,
                getApplicationContext(),
                new UpdateDataListener<MenuMysql>() {
                    @Override
                    public void onLoadSuccess(ArrayList<MenuMysql> data) {
                        // quang diem loc cac gia tri co id vua click thoi
                        listMenuMysqlTemp= new ArrayList<>();
                        for (int i=0;i< data.size();i++){
                            if(data.get(i).getId()==id){

                                listMenuMysqlTemp.add(new MenuMysql(id,data.get(i).getName_product(),data.get(i).getPrice_product(),
                                        data.get(i).getCount_product(),data.get(i).getStatus(),data.get(i).getDate()));
                            }
                        }
                        adapterBill = new AdapterShowBill(getApplicationContext(),listMenuMysqlTemp);
                        recyclerView_Bill.setAdapter(adapterBill);
                        adapterBill.notifyDataSetChanged();
                    }
                });

    }
    public void CallRecyclerviewListSelect(){
        //listSetMenu = new ArrayList<>();

        listSetMenu = new AdapterShowProduct().GetListSetMenu();
        if(listSetMenu.size()>0) {
            Log.d("huynt75", "listsetmenu co do dai " + listSetMenu.size());
            Log.d("huynt75", "listsetmenu co do dai " + listSetMenu.get(0).getName_product());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),linearLayoutManager.getOrientation());
            rcl_preview_list_selectted.addItemDecoration(dividerItemDecoration);
            rcl_preview_list_selectted.setLayoutManager(linearLayoutManager);
            adapterListSelectted = new AdapterShowListSelectted(getApplicationContext(), listSetMenu,id);
            rcl_preview_list_selectted.setAdapter(adapterListSelectted);
            adapterListSelectted.notifyDataSetChanged();
        }
    }

    public void SumInfo(){
        countMenuOfTable=0;
        moneyOfTable=0;
        if(listMenuMysqlTemp!=null) {
            for (int i = 0; i < listMenuMysqlTemp.size(); i++) {
                //tinh tong cout va money
                countMenuOfTable = countMenuOfTable+Integer.valueOf(listMenuMysqlTemp.get(i).getCount_product());
                moneyOfTable = moneyOfTable+Integer.valueOf(listMenuMysqlTemp.get(i).getPrice_product());
            }
        }

    }
}
