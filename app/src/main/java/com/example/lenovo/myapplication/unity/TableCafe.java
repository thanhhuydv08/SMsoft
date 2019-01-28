package com.example.lenovo.myapplication.unity;

public class TableCafe {
    int idTable;
    int numberTable;

    public TableCafe(int idTable, int numberTable) {
        this.idTable = idTable;
        this.numberTable = numberTable;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }
}
