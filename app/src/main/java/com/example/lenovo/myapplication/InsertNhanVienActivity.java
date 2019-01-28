package com.example.lenovo.myapplication;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.NhanVien;

import java.util.ArrayList;

public class InsertNhanVienActivity extends AppCompatActivity {

    Toolbar toolbar1;
    EditText edt_name,edt_id;
    EditText edt_pass;
    TextView txtv_permision;
    TextView txtv_add;
    TextView txtv_cancel;
    String urlGetDataNhanVien ="http://thanhhuydv08.000webhostapp.com/getdataNhanVien.php";
    String   urlInsertNhanVien ="http://thanhhuydv08.000webhostapp.com/insert_nhan_vien.php";
    public static  NhanVien nhanVien;
    Animation animThem,animHuy;
    public static ArrayList thongBao= new ArrayList();
    ArrayList<NhanVien> listNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_nhan_vien);
        Anhxa();
        setSupportActionBar(toolbar1);
        ActionBar actionBar1 = getSupportActionBar();
        actionBar1.setTitle(R.string.title_acitvity_insertStaff);
        ControllerDataMysql.getInstance().GetAndRefreshData(2,urlGetDataNhanVien, getApplicationContext(), new UpdateDataListener<NhanVien>() {
            @Override
            public void onLoadSuccess(ArrayList<NhanVien> arrayList) {
                    listNhanVien=arrayList;
            }
        });
        txtv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // anim text view va reloadding
                txtv_add.clearAnimation();
                animThem = AnimationUtils.loadAnimation(InsertNhanVienActivity.this,R.anim.fly_frm_btn_scale);
                txtv_add.setAnimation(animThem);
               thongBao = new ArrayList();// đưa về 0 vi arr mang kiểu static
               if( AddDataFromViewToNhanVien()) {
                   new ReloaddingWait().CallDialog(InsertNhanVienActivity.this,1);
                   ControllerDataMysql.getInstance().PostData(urlInsertNhanVien, getApplication(), nhanVien,null,null, 0, 2, new UpdateDataListener() {
                       @Override
                       public void onLoadSuccess(ArrayList arrayList) {
                             thongBao=arrayList;
                       }
                   });
               }
            }
        });
        txtv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtv_cancel.clearAnimation();
                animHuy = AnimationUtils.loadAnimation(InsertNhanVienActivity.this,R.anim.fly_frm_btn_scale);
                txtv_cancel.setAnimation(animHuy);
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar1 = findViewById(R.id.toolbar2);
        edt_name = findViewById(R.id.edt_name);
        edt_id = findViewById(R.id.edt_id);
        edt_pass = findViewById(R.id.edt_pass);
        txtv_permision = findViewById(R.id.txtv_permision);
        txtv_add = findViewById(R.id.txtv_add);
        txtv_cancel = findViewById(R.id.txtv_cancel);
    }

    public boolean AddDataFromViewToNhanVien(){

        boolean check = false;
        int temp=0;
        if(edt_id.getText().toString().isEmpty()|| edt_name.getText().toString().isEmpty()|| edt_pass.getText().toString().isEmpty()){
            Toast.makeText(this,"Bắt buộc các trường thông tin không được để trống !",Toast.LENGTH_LONG).show();
          }else {

            for (int i = 0; i < listNhanVien.size(); i++) {
                if ( edt_name.getText().toString().equals(listNhanVien.get(i).getName())) {
                  Toast.makeText(this,"Username đã có trên hệ thống ! Vui lòng nhập lại.",Toast.LENGTH_LONG).show();
                 temp++;
                  break;
                }
            }
            if(temp==0){
                nhanVien = new NhanVien(Integer.parseInt(edt_id.getText().toString()), edt_name.getText().toString(),
                        edt_pass.getText().toString(), txtv_permision.getText().toString());
                check=true;
            }
        }
        return check;
    }
    public ArrayList GetArraylistThongBao(){
        return thongBao;
    }

}
