package com.example.lenovo.myapplication.unity;

public class Product {
    private int id_product;
    private String name_product;
    private String price_product;
    private String image_product;

    public Product() {
    }

    public Product(int id_product, String name_product, String price_product, String image_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
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

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }
}
