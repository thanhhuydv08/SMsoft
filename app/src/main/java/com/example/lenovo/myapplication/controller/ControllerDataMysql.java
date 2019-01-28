package com.example.lenovo.myapplication.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.myapplication.unity.MenuMysql;
import com.example.lenovo.myapplication.unity.NhanVien;
import com.example.lenovo.myapplication.unity.Product;
import com.example.lenovo.myapplication.unity.TableCafe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerDataMysql{
    private ArrayList arrayList ;
    ArrayList thongBao;
    int size;
    private static ControllerDataMysql instance;

    public static synchronized ControllerDataMysql getInstance() {
        if (instance == null) {
            instance = new ControllerDataMysql();

        }
        return instance;
    }

    private ControllerDataMysql() {
    }

    /*******************************************
     * dung method POST data tu app len php
     **********************************************/
    public void PostData(String url, final Context context, final NhanVien nhanVien, final MenuMysql menuMysql, final Product product, final int number, final int key, final UpdateDataListener listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                thongBao = new ArrayList();
                thongBao.add(0,response);
                // key =1 nghia la post data có refest view table nên trỏ về và gọi tiếp hàm get để dữ liệu luôn được làm mới
                listener.onLoadSuccess(thongBao);// bắt buộc có hàm còn biến arr ko có tác dụng ngoại tru class ínertnhanvien
                // echo "complete"; tren  file insert.php
                Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                    }
                }) {// dat con tro truoc ; va them {} de ra ham Map thuc thi

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if(key==2){
                    params.put("id", String.valueOf(nhanVien.getId()));// hoTenSV la file insert.php da tao
                    params.put("name", nhanVien.getName());// hoTenSV la file insert.php da tao
                    params.put("pass", nhanVien.getPass());// namsinhSV la file insert.php da tao
                    params.put("permision", nhanVien.getPermision());// hoTenSV la file insert.php da ta
                }else if(key==0){
                    params.put("numberTable",String.valueOf(number));
                }else  if(key==3){
                    params.put("id", String.valueOf(menuMysql.getId()));// hoTenSV la file insert.php da tao
                    params.put("name_product", menuMysql.getName_product());// hoTenSV la file insert.php da tao
                    params.put("price_product", menuMysql.getPrice_product());// namsinhSV la file insert.php da tao
                    params.put("count_product", menuMysql.getCount_product());// hoTenSV la file insert.php da tao
                    params.put("status", menuMysql.getStatus());// hoTenSV la file insert.php da tao
                    params.put("date", menuMysql.getDate());// hoTenSV la file insert.php da tao
                }else  if(key==4){
                    params.put("id_product", String.valueOf(product.getId_product()));// hoTenSV la file insert.php da tao
                    params.put("name_product",product.getName_product());// hoTenSV la file insert.php da tao
                    params.put("price_product", product.getPrice_product());// namsinhSV la file insert.php da tao
                }

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    /*******************************************
     * Đọc url json từ webservices php thông qua volley
     **********************************************/

    public void GetAndRefreshData(final int key_per,String url, final Context context, final UpdateDataListener listener) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayList = new ArrayList<>();
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if(key_per ==0){

                            arrayList.add(new TableCafe(jsonObject.getInt("id"),
                                    jsonObject.getInt("number")));}
                        else if(key_per==1){

                            arrayList.add(new Product(jsonObject.getInt("id_product"),jsonObject.getString("name_product"),jsonObject.getString("price_product"),
                                    jsonObject.getString("image_product")));
                        }else if(key_per==2){

                            arrayList.add(new NhanVien(jsonObject.getInt("id"),jsonObject.getString("name"),
                                    jsonObject.getString("pass"),jsonObject.getString("permision")));
                        }else if(key_per==3){

                            arrayList.add(new MenuMysql(jsonObject.getInt("id"),jsonObject.getString("name_product"),jsonObject.getString("price_product"),
                                    jsonObject.getString("count_product"),jsonObject.getString("status"),jsonObject.getString("date")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listener.onLoadSuccess(arrayList);
                Log.d("huynt75","trong ham get"+arrayList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void DeleteTable(int id, RemoveDataListener removeDataListener){
        removeDataListener.onLoadSuccess(arrayList,id);

    }
}