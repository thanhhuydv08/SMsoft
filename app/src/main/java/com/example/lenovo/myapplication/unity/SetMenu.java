package com.example.lenovo.myapplication.unity;

public class SetMenu {
    private int stt;
    private String name_product;
    private String price_product;
    private String count_product;

    public SetMenu(int stt, String name_product, String price_product, String count_product) {
        this.stt = stt;
        this.name_product = name_product;
        this.price_product = price_product;
        this.count_product = count_product;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getCount_product() {
        return count_product;
    }

    public void setCount_product(String count_product) {
        this.count_product = count_product;
    }
}
