package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lenovo.myapplication.controller.ControllerDataMysql;
import com.example.lenovo.myapplication.controller.UpdateDataListener;
import com.example.lenovo.myapplication.unity.NhanVien;
import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    String urlGetDataNhanVien ="http://thanhhuydv08.000webhostapp.com/getdataNhanVien.php";
    ArrayList<NhanVien> listNhanVien= new ArrayList<>();
    EditText edtUsername,edtPassword;
    TextView textView;
    LinearLayout layout_user,layout_pass;
    FrameLayout frameLogin, frameOval,imgZoomOval;
    ImageView imageView;
    Animation animImageTop;
    Animation animTxtvSingin,animEdtUser,animEdtPass,animFrmBtn, animOval, animImgOval;
    int temp;
    int checkUser;
    int checkPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        ChanceColorStatusBar();
        AnimWigets();
        // String url ="http://thanhhuydv08.000webhostapp.com/getdataNhanVien.php";

        frameLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerDataMysql.getInstance().GetAndRefreshData(2, urlGetDataNhanVien, getApplicationContext(), new UpdateDataListener <NhanVien>() {
                    @Override
                    public void onLoadSuccess(ArrayList<NhanVien> arrayList) {
                        listNhanVien=arrayList;
                    }
                });
                frameLogin.clearAnimation();
                animFrmBtn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fly_frm_btn_scale);
                frameLogin.setAnimation(animFrmBtn);
                animFrmBtn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        frameLogin.setAlpha(0);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                frameOval.clearAnimation();
                animOval =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.show_frm_oval);
                frameOval.setAnimation(animOval);
                frameOval.setAlpha(1);

                // bat su kien cho animation
                animOval.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        frameOval.setAlpha(0);
                        checkPass = 0;
                        checkUser = 0;
                        if (listNhanVien.size() != 0) {
                            for (int i = 0; i < listNhanVien.size(); i++) {
                                if (edtUsername.getText().toString().equals(listNhanVien.get(i).getName())) {
                                    checkUser++;
                                    if (edtPassword.getText().toString().equals(listNhanVien.get(i).getPass())) {
                                        checkPass++;
                                        temp = i;
                                        i = listNhanVien.size();
                                        break;
                                    }

                                }
                            }
                            if (checkUser != 0) {
                                if (checkPass != 0) {
                                    imgZoomOval.clearAnimation();
                                    animImgOval = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_img_oval);
                                    imgZoomOval.setAnimation(animImgOval);
                                    imgZoomOval.setAlpha(1);
                                    animImgOval.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {
                                            Toast.makeText(getApplicationContext(), "Bạn đã đăng nhập thành công", Toast.LENGTH_LONG).show();
                                            Thread t = new Thread() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        sleep(150);
                                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                        intent.putExtra("username", listNhanVien.get(temp).getName());
                                                        intent.putExtra("password", listNhanVien.get(temp).getPass());
                                                        intent.putExtra("permision", listNhanVien.get(temp).getPermision());
                                                        startActivity(intent);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            };
                                            t.start();
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgZoomOval.setAlpha(0);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else {
                                    Toast.makeText(getApplicationContext(), "Sai password vui lòng nhập lại", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Sai username vui lòng nhập lại", Toast.LENGTH_LONG).show();
                            }
                            frameLogin.setAlpha(1);// khi đúng cũng ẩn button để thuwjxc hiện lần 2 sau khi chuyen activity
                        }else {
                            Toast.makeText(LoginActivity.this,"Kiểm tra lại mạng chưa kết nối được server",Toast.LENGTH_LONG).show();
                            frameLogin.setAlpha(1);
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Log.d("huynt75","dang chạy "+frameLogin.getAlpha());
            }
        });

    }

    private void AnimWigets() {
        animImageTop = AnimationUtils.loadAnimation(this,R.anim.fly_img_top);
        imageView.setAnimation(animImageTop);
        animTxtvSingin = AnimationUtils.loadAnimation(this,R.anim.fly_txtv_sigin);
        textView.setAnimation(animTxtvSingin);
        animEdtUser = AnimationUtils.loadAnimation(this,R.anim.fly_layout_user);
        layout_user.setAnimation(animEdtUser);
        animEdtPass = AnimationUtils.loadAnimation(this,R.anim.fly_layout_pass);
        layout_pass.setAnimation(animEdtPass);
        // băt buoc nhé khi da goi anim thi phai clear thi moi thuc hien anim cho no tiep theo
        animFrmBtn = AnimationUtils.loadAnimation(this,R.anim.fly_frm_btn);
        frameLogin.setAnimation(animFrmBtn);
    }

    // thay doi mau statusbar phu hop voi backgroundlogin
    private void ChanceColorStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }

    public void AnhXa(){
        edtUsername=findViewById(R.id.edt_username_login);
        edtPassword=findViewById(R.id.edt_password_login);
        frameLogin=findViewById(R.id.fr_login);
        frameOval=findViewById(R.id.fr_oval_login);
        textView = findViewById(R.id.txtv_sigin);
        layout_user = findViewById(R.id.layout_user);
        layout_pass = findViewById(R.id.layout_pass);
        imageView = findViewById(R.id.img_top);
        imgZoomOval= findViewById(R.id.img_zoom);

    }
}