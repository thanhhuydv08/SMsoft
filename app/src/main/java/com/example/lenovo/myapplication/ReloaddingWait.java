package com.example.lenovo.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class ReloaddingWait {
    ProgressBar progressBar;
    AlertDialog.Builder builder;
    ArrayList arrayList;
    Dialog dialog;
    int jumpTime;
    public ReloaddingWait() {

    }
    public void CallDialog(final Activity activity, final int key){
        progressBar=new ProgressBar(activity);
        progressBar.setIndeterminate(true);
        progressBar.setProgress(0);
        builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.item_dialog_reload);
        dialog = builder.create();
        final Handler progressHandler = new Handler();
        dialog.show();
        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                arrayList = new ArrayList();
                Log.d("huynt75", "toi dang ngoai progressbar "+arrayList.size() );
                jumpTime = 10;
                while (arrayList.size() == 0) {
                    jumpTime++;
                    try {
                        Thread.sleep(300);
                        Log.d("huynt75", "toi dang o progressbar "+arrayList.size() );

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //
                    progressHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(jumpTime);
                            if(key==0) {
                                arrayList = new CreateCafeActivity().GetArraylistTable();
                                Log.d("huynt75", "toi dang o posthandler table " + new CreateCafeActivity().GetArraylistTable());
                            }else if(key==1){
                                arrayList = new InsertNhanVienActivity().GetArraylistThongBao();
                                Log.d("huynt75", "toi dang o posthandler sinh vien " + new InsertNhanVienActivity().GetArraylistThongBao());

                            }else if(key==4){
                                arrayList = new InsertThucDonActivity().GetArraylistThongBao();
                                Log.d("huynt75", "toi dang o posthandler product " + new InsertNhanVienActivity().GetArraylistThongBao());

                            }
                        }
                    });
                }
                Log.d("huynt75", "ngat ket noi progressbar");
                dialog.cancel();
            }
        };
        t.start();
    }
}
