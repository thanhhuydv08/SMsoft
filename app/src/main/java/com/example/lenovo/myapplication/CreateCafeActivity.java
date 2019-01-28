package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.adapter.AdapterTableCafe;
import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.RemoveDataListener;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.NhanVien;
import com.example.lenovo.myapplication.unity.TableCafe;


import java.util.ArrayList;

public class CreateCafeActivity extends AppCompatActivity {
    Toolbar toolbar3;
    RecyclerView recyclerView;
    Button button;
    Handler handler;
    AdapterTableCafe adapter;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    EditText edt_number_table;
    TextView txtv_hienthi_nhaptubanphim;
    Button btn_agree;
    NhanVien nhanVien;
    public static ArrayList arrayListTable = new ArrayList();// lay size cua thang nay  chit qua progressbar de stp no vi vay bat buoc public static
    public String urlGetTable = "http://thanhhuydv08.000webhostapp.com/getdatatable.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cafe);
        // chayj loading ngay de tiens trinh khac load data
        Log.d("huynt75","tao tien trinh recyclerview");
        AnhXa();
        ChanceColorStatusBar();
        setSupportActionBar(toolbar3);
        final ActionBar actionBar1 = getSupportActionBar();
// actionBar.setDisplayShowTitleEnabled(false);
        actionBar1.setDisplayHomeAsUpEnabled(true);
        actionBar1.setTitle(R.string.title_acivity_createcafe);
//actionBar.setTitle(R.string.app_name);
        actionBar1.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(gridLayoutManager);
        // recyclerView.setBackground(getDrawable(R.drawable.bg_recyclerview_table));
        LoadDataToRecyclerview(urlGetTable);
        /*     goi ham reloadding de chay da tien trinh trong luc get data tu api - mysql*/
        new ReloaddingWait().CallDialog(CreateCafeActivity.this,0);
    }
    private void AnhXa() {
        toolbar3 = findViewById(R.id.toolbar3);
        recyclerView = findViewById(R.id.rcl_tablecoffee);
        relativeLayout = findViewById(R.id.layout_hienthi_nhapthongtin);
        edt_number_table = findViewById(R.id.edt_number_table);
        btn_agree = findViewById(R.id.btn_agree);
        txtv_hienthi_nhaptubanphim = findViewById(R.id.txtv_hienthi_nhaptubanphim);
        linearLayout = findViewById(R.id.layout_recyclerview);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_cafe_table, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.mn_add_table: {
                txtv_hienthi_nhaptubanphim.setText(R.string.text_inserttable_activity_createcafe);
                relativeLayout.setVisibility(View.VISIBLE);
                btn_agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edt_number_table.getText().toString())) {
                            Toast.makeText(getApplicationContext(),"Vui lòng nhập vào số bàn cần thêm !",Toast.LENGTH_LONG).show();
                        } else {
                            arrayListTable = new ArrayList();// tao moi doi tuong khi doituoc la static de luc nao cung bat dau 1 methdd luon la 0;
                            new ReloaddingWait().CallDialog(CreateCafeActivity.this, 0);
                            relativeLayout.setVisibility(View.GONE);
                            String url = "http://thanhhuydv08.000webhostapp.com/insert_number_table.php";
// khi post data len mysql đợi khi hàm reposn co gia tri trả về thanh cong moi goi ham get data vè vi ko đồng bộ dẫn đến chưa insert xong đã get data = như ban đầu
                            PostDataToMysql(url);// trong ham này có ham post và get luôn
                        }
                    }
                });
                break;
            }
            case R.id.mn_remv_table: {
/*
                 quan điểm xóa bàn thay đỏi vị trí position nhưng id đã gán từ trước và id này luôn luôn được show
                 vì vậy chỉ dựa vào id để load data
*/
                txtv_hienthi_nhaptubanphim.setText(R.string.text_removetable_activity_createcafe);
                relativeLayout.setVisibility(View.VISIBLE);
                btn_agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edt_number_table.getText().toString())) {
                            Toast.makeText(getApplicationContext(),"Vui lòng nhập vào Id bàn cần xóa !",Toast.LENGTH_LONG).show();

                        } else {
                        arrayListTable = new ArrayList();
                        new ReloaddingWait().CallDialog(CreateCafeActivity.this, 0);
                        relativeLayout.setVisibility(View.GONE);
/*
                          adapter.notifyItemRemoved(Integer.valueOf(edt_number_table.getText().toString()));
                         tại sao không sử dụng thằng này được vì thag này xóa theo position mà ta quản lý theo id bàn nhập lên từ bàn phím
                          adapter.notifyDataSetChanged();
                         bắt buocj phải trả về 1 list id, xong lấy size để tạo view, và lấy
*/
                        ControllerDataMysql.getInstance().DeleteTable(Integer.valueOf(edt_number_table.getText().toString()), new RemoveDataListener() {
                            @Override
                            public void onLoadSuccess(ArrayList<TableCafe> arrayList, int positionTable) {
/*
                                Log.d("huynt75","xoa ban "+positionTable);
                                Log.d("huynt75","xoa ban list size "+arrayList.size());
                                ArrayList<ShowTable> arrayList1 = new ArrayList();
                                arrayList1 =arrayList;// bắt buộc phải gán vào 1 array list1, con arraylist chỉ là ánh xạ từ class khác.
                                int index =positionTable;// tại sao đưa vào position là khong duoc ma phai gán vè biến // dưới này cũng vậy
*/
                                String url = "http://thanhhuydv08.000webhostapp.com/delete_number_table.php";
                                PostDataToMysql(url);

                            }
                        });
                    }
                    }
                });
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    // khi getdata ve tu mysql đợi khi hàm reposn co gia tri trả về thì mới truyền data về recyclerview
    private void LoadDataToRecyclerview(String url) {
        arrayListTable = new ArrayList();
        ControllerDataMysql.getInstance().GetAndRefreshData(0,
                url,
                getApplicationContext(),
                new UpdateDataListener<TableCafe>() {
                    @Override
                    public void onLoadSuccess(ArrayList<TableCafe> data) {
                        arrayListTable=data;
                        if(data!=null){
                            Log.d("huynt75","tao view table "+data.size());
                            Log.d("huynt75","gan arralistTable"+arrayListTable.size());
                            if (data.size() > 7) {// chỉnh view ko cho wrapconten khi có quá 7 màn hình
                                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));
                            }
                            adapter = new AdapterTableCafe(data, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }else {Toast.makeText(getApplicationContext(),"Kết nối mạng rất chậm , không thể kết nối tới server.",Toast.LENGTH_LONG).show();}
                    }
                });
    }

    private void PostDataToMysql(String url){
        ControllerDataMysql.getInstance()
                .PostData(url, getApplicationContext(), nhanVien,null,null, Integer.valueOf(edt_number_table.getText().toString()), 0,
                        new UpdateDataListener <TableCafe>() {
                            @Override
                            public void onLoadSuccess(ArrayList<TableCafe> arrayList) {
                                LoadDataToRecyclerview(urlGetTable);
                            }
                        });
    }

    /*tra do dai mang truyen vao class ReloaddingWait*/
    public ArrayList GetArraylistTable(){

        return arrayListTable;
    }
    private void ChanceColorStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }
}


