package com.example.lenovo.myapplication.unity;

public class MenuMysql {
    private int id;
    private String name_product;
    private String price_product;
    private String count_product;
    private String status;
    private String date;

    public MenuMysql(int id, String name_product, String price_product, String count_product, String status, String date) {
        this.id = id;
        this.name_product = name_product;
        this.price_product = price_product;
        this.count_product = count_product;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
