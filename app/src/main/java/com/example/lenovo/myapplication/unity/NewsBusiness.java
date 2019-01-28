package com.example.lenovo.myapplication.unity;

public class NewsBusiness {
    String info_title,info_under,info_order,disp_image;

    public NewsBusiness(String info_title, String info_under, String info_order, String disp_image) {
        this.info_title = info_title;
        this.info_under = info_under;
        this.info_order = info_order;
        this.disp_image = disp_image;
    }

    public String getInfo_title() {
        return info_title;
    }

    public void setInfo_title(String info_title) {
        this.info_title = info_title;
    }

    public String getInfo_under() {
        return info_under;
    }

    public void setInfo_under(String info_under) {
        this.info_under = info_under;
    }

    public String getInfo_order() {
        return info_order;
    }

    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }

    public String getDisp_image() {
        return disp_image;
    }

    public void setDisp_image(String disp_image) {
        this.disp_image = disp_image;
    }
}
