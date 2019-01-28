package com.example.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    LinearLayout layout_start, lnl_createcoffee,lnl_createshop,lnl_createsales;
    Button btn_connect_facebook;
    private static  String username,password,permision;
    TextView txtvHeaderName,txtvHeaderMail;
    NavigationView navigationView;
    View headerView;
    int nav_addDrink_Coffee =0101;// set 1 bien int bat ki tu dau.
    int nav_addDrink_Tra =0102;
    int nav_addDrink_DaBao =0103;
    int nav_addDrink_TraiCay =0104;
    int nav_addDrink_ThuGon =0105;
    MenuItem menuItemCoffee,menuItemTra,menuItemDaBao,menuItemTraiCay,menuItemThuGon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();// lấy ra 3 thông số được truyef vao từ login
        username=intent.getStringExtra("username");
        password=intent.getStringExtra("password");
        permision=intent.getStringExtra("permision");

        Log.d("huynt75","user/pass/per="+username+"/"+password+"/"+permision);
        AnhXa();
        txtvHeaderName.setText(username);
        txtvHeaderMail.setText(username+"@android.com.vn");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_list_white_24dp);
// Tạo item trong đoạn order 3->7 đã lập trình file layout để khi click vào thêm thức uống sẽ show item này ra
        addSubMenuToNavigation();
        navigationView.setNavigationItemSelectedListener(this);
// Gọi hàm đọc json từ php
//  ReadJsonUrl("http://thanhhuydv08.000webhostapp.com/getdatabase.php", getApplication());
        lnl_createcoffee.setOnClickListener(this);
        lnl_createshop.setOnClickListener(this);
        lnl_createsales.setOnClickListener(this);
        btn_connect_facebook.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.add :
        }
        return super.onOptionsItemSelected(item);
    }
    /*******************************************
     * băt sự kiện cho navigation cho các item
     **********************************************/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {// mac đinh bat su kien luon cho navigation
        int id = item.getItemId();
        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_addDrink) {
            // có thể dùng còng lặp for or gọi hàm
            menuItemCoffee.setVisible(true);
            menuItemTra.setVisible(true);
            menuItemDaBao.setVisible(true);
            menuItemTraiCay.setVisible(true);
            menuItemThuGon.setVisible(true);

        } else if (id == R.id.nav_historyPayment) {
           // Toast.makeText(getApplication(),"toi da click vao lich su",Toast.LENGTH_LONG).show();
            if(permision.equals("admin")) {
                startActivity(new Intent(MainActivity.this, HistoryPaymentActivity.class));
                Animatoo.animateSlideDown(MainActivity.this);
            }else Toast.makeText(getApplicationContext(),"Error, Username này không có quyền xem lịch sử thanh toán !!!",
                    Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_list_thucdon) {

        } else if (id == R.id.nav_addAcount) {
            if(permision.equals("admin")){
                startActivity(new Intent(MainActivity.this,InsertNhanVienActivity.class));
                Animatoo.animateSlideDown(MainActivity.this);
            }else Toast.makeText(getApplicationContext(),"Error, Username này không có quyền tạo tài khoản !!!",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_exit) {
            finish();
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
        }else if (id ==nav_addDrink_Coffee) {// do id add bang code
           // Toast.makeText(this,"toi da vao toi sub",Toast.LENGTH_LONG).show();
            Intent intent = new  Intent(MainActivity.this,InsertThucDonActivity.class);
            intent.putExtra("thucuong","cafe");
            startActivity(intent);

        }else if (id ==nav_addDrink_Tra) {// do id add bang code
            //Toast.makeText(this,"toi da vao toi sub",Toast.LENGTH_LONG).show();
            Intent intent = new  Intent(MainActivity.this,InsertThucDonActivity.class);
            intent.putExtra("thucuong","tea");
            startActivity(intent);

        }else if (id ==nav_addDrink_DaBao) {// do id add bang code
           // Toast.makeText(this,"toi da vao toi sub",Toast.LENGTH_LONG).show();
            Intent intent = new  Intent(MainActivity.this,InsertThucDonActivity.class);
            intent.putExtra("thucuong","dabao");
            startActivity(intent);
        }else if (id ==nav_addDrink_TraiCay) {// do id add bang code
           // Toast.makeText(this,"toi da vao toi sub",Toast.LENGTH_LONG).show();
            Intent intent = new  Intent(MainActivity.this,InsertThucDonActivity.class);
            intent.putExtra("thucuong","traicay");
            startActivity(intent);

        }else if (id == nav_addDrink_ThuGon) {
            // có thể dùng còng lặp for or gọi hàm
            menuItemCoffee.setVisible(false);
            menuItemTra.setVisible(false);
            menuItemDaBao.setVisible(false);
            menuItemTraiCay.setVisible(false);
            menuItemThuGon.setVisible(false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // method xử lý sự kiện click của item trên backgound chính
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lnl_createcoffee :
                startActivity(new Intent(MainActivity.this,CreateCafeActivity.class));
                Animatoo.animateSwipeLeft(MainActivity.this);
                break;
            case R.id.lnl_createshop :
                startActivity(new Intent(MainActivity.this,ComingSoonActivity.class));
                Animatoo.animateSwipeLeft(MainActivity.this);
                break;
            case R.id.lnl_createsales :
                startActivity(new Intent(MainActivity.this,ComingSoonActivity.class));
                Animatoo.animateSwipeLeft(MainActivity.this);
                break;
            case R.id.btn_connect_facebook :
                startActivity(new Intent(MainActivity.this,ComingSoonActivity.class));
                Animatoo.animateSwipeLeft(MainActivity.this);
                break;
        }
    }
    // ánh xạ các đối tượng vào trong layout
    private void AnhXa(){
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // layout_start = findViewById(R.id.layout_wellcomestart);
        lnl_createcoffee = findViewById(R.id.lnl_createcoffee);
        lnl_createshop = findViewById(R.id.lnl_createshop);
        lnl_createsales = findViewById(R.id.lnl_createsales);
        btn_connect_facebook = findViewById(R.id.btn_connect_facebook);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);// trỏ tới navigation do dùng app ko phải include nên kho thể tự trỏ tới id của textv
        txtvHeaderName = headerView.findViewById(R.id.txtv_header_name); // trỏ tới id textv trong header thông qua view được get header tu navigation
        txtvHeaderMail = headerView.findViewById(R.id.txtv_header_mail);
    }
    // method insert submenu vào trong menu của navigation từ vị trí 3 đến 7 vị trí này được layout bỏ trống
    private void addSubMenuToNavigation(){
        Menu menu = navigationView.getMenu();
        menuItemCoffee = menu.add(R.id.group_1,nav_addDrink_Coffee,3,"Cà phê");
        menuItemTra = menu.add(R.id.group_1,nav_addDrink_Tra,4,"Trà");
        menuItemDaBao = menu.add(R.id.group_1,nav_addDrink_DaBao,5,"Đá Bào");
        menuItemTraiCay = menu.add(R.id.group_1,nav_addDrink_TraiCay,6,"Nước Trái Cây");
        menuItemThuGon = menu.add(R.id.group_1,nav_addDrink_ThuGon,7,"Hide subitem");
        // có thể dùng còng lặp for
        menuItemCoffee.setVisible(false);
        menuItemTra.setVisible(false);
        menuItemDaBao.setVisible(false);
        menuItemTraiCay.setVisible(false);
        menuItemThuGon.setVisible(false);
    }

}


