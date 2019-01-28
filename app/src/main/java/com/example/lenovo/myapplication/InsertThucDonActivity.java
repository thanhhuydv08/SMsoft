package com.example.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.NhanVien;
import com.example.lenovo.myapplication.unity.Product;

import java.util.ArrayList;

public class InsertThucDonActivity extends AppCompatActivity {
    String typeDrink;
   EditText edt_id_product,edt_name_product, edt_price_product;
   TextView txtv_add,txtv_cancel;
    String urlGetDataProduct ="http://thanhhuydv08.000webhostapp.com/getdataproduct.php";
    String   urlInserProduct ="http://thanhhuydv08.000webhostapp.com/insert_product.php";
     ArrayList<Product> listProduct;
     Product product;
    Animation animThem,animHuy;
    public static ArrayList thongBao= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_thuc_don);
        Anhxa();
        Intent intent = this.getIntent();
        typeDrink = intent.getStringExtra("thucuong");
        Log.d("huynt75","toi dang getextra tai thuc don "+typeDrink);

       // upload data len mysql thong tin
        ControllerDataMysql.getInstance().GetAndRefreshData(3,urlGetDataProduct, getApplicationContext(), new UpdateDataListener<Product>() {
            @Override
            public void onLoadSuccess(ArrayList<Product> arrayList) {
                listProduct=arrayList;
            }
        });
        txtv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // anim text view va reloadding
                txtv_add.clearAnimation();
                animThem = AnimationUtils.loadAnimation(InsertThucDonActivity.this,R.anim.fly_frm_btn_scale);
                txtv_add.setAnimation(animThem);
                thongBao = new ArrayList();// đưa về 0 vi arr mang kiểu static
                if( AddDataFromViewToProduct()) {
                    new ReloaddingWait().CallDialog(InsertThucDonActivity.this,4);
                    ControllerDataMysql.getInstance().PostData(urlInserProduct, getApplication(), null,null,product, 0, 4, new UpdateDataListener() {
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
                animHuy = AnimationUtils.loadAnimation(InsertThucDonActivity.this,R.anim.fly_frm_btn_scale);
                txtv_cancel.setAnimation(animHuy);
                finish();
            }
        });
    }
    public void Anhxa(){
         edt_id_product = findViewById(R.id.edt_id_product);
         edt_name_product = findViewById(R.id.edt_name_product);
         edt_price_product = findViewById(R.id.edt_price_product);
         txtv_add = findViewById(R.id.txtv_add);
         txtv_cancel = findViewById(R.id.txtv_cancel);
    }
    public boolean AddDataFromViewToProduct(){

        boolean check = false;
        int temp=0;
        if(edt_id_product.getText().toString().isEmpty()|| edt_name_product.getText().toString().isEmpty()|| edt_price_product.getText().toString().isEmpty()){
            Toast.makeText(this,"Bắt buộc các trường thông tin không được để trống !",Toast.LENGTH_LONG).show();
        }else {

            for (int i = 0; i < listProduct.size(); i++) {
                if ( edt_id_product.getText().toString().equals(listProduct.get(i).getId_product())) {
                    Toast.makeText(this,"ID thực đơn đã có trên hệ thống ! Vui lòng nhập lại.",Toast.LENGTH_LONG).show();
                    temp++;
                    break;
                }
            }
            if(temp==0){
                product = new Product(Integer.parseInt(edt_id_product.getText().toString()), typeDrink+"-"+edt_name_product.getText().toString(),
                        edt_price_product.getText().toString(),null);
                check=true;
            }else  {Toast.makeText(this,"ID thực đơn đã có trên hệ thống ! Vui lòng nhập lại.",Toast.LENGTH_LONG).show();}

        }
        return check;
    }
    public ArrayList GetArraylistThongBao(){
        return thongBao;
    }
}
