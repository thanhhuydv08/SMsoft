package com.example.lenovo.myapplication.controller;

import com.example.lenovo.myapplication.unity.TableCafe;

import java.util.ArrayList;
import java.util.List;

public interface  UpdateDataListener <A> {
    // lây dữ liệu từ mysql-> bất đồng bộ nên buộc phải gọi đến interface
    public void  onLoadSuccess(ArrayList <A> arrayList);
}
