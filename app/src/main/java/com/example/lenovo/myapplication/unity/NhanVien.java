package com.example.lenovo.myapplication.unity;

public class NhanVien {
    private int id;
    private String name;
    private String pass;
    private String permision;

    public NhanVien() {
    }

    public NhanVien(int id, String name, String pass, String permision) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.permision = permision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPermision() {
        return permision;
    }

    public void setPermision(String permision) {
        this.permision = permision;
    }
}
