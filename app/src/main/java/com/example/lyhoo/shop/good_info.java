package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class good_info extends AppCompatActivity {
    TextView t1;//名字
    TextView t2;//运费
    TextView t3;//产地
    TextView t4;//价格
    TextView t5;//库存
    TextView t6;//数量
    TextView t7;//具体信息
    Button bt1;//减数量
    Button bt2;//加数量
    Button bt3;//加入购物车
    Button bt4;//购买
    ImageView i1;//照片
    ImageView i2;//返回
    int flag;
    TextView Favourite;
    int good_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info);


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        t6 = (TextView) findViewById(R.id.editText);
        t7 = (TextView) findViewById(R.id.textView6);
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        i1 = (ImageView) findViewById(R.id.imageView);
        i2 = (ImageView) findViewById(R.id.fanhui);
        Favourite = (TextView) findViewById(R.id.favourite);



        Bundle bundle = getIntent().getExtras();

        final String Name = bundle.getString("name");
        String Aaddress = bundle.getString("address");
        final Double Price = bundle.getDouble("price");
        final int Icon = bundle.getInt("pic");
        flag = bundle.getInt("flag",0);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String systemTime = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date().getTime());// 获取系统时间
        person_info personInfo = new person_info();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",Name);
        contentValues.put("icon",Icon);
        contentValues.put("time",systemTime);
        personInfo.addbrowsingHistory(contentValues);

        t1.setText(Name);
        t3.setText(Aaddress);
        t4.setText(Price.toString());
        i1.setBackgroundResource(Icon);

        Myhelper myhelper1 = new Myhelper(good_info.this);
        SQLiteDatabase db1 = myhelper1.getReadableDatabase();
        Cursor cursor = db1.query("good",null,"name=?",new String[]{Name},null,null,null);
        cursor.moveToNext();
        String detail = cursor.getString(7);
        t7.setText(detail);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(t6.getText().toString());
                if (num==0){
                    num = 0;
                    t6.setText(String.valueOf(num));
                }else {
                    num--;
                    t6.setText(String.valueOf(num));
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(t6.getText().toString());
                num++;
                t6.setText(String.valueOf(num));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_info person = new person_info();
                if (person.getName().equals("")){
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(good_info.this).setTitle("提示")
                            .setMessage("请登录后进行操作！")
                            .setNegativeButton("登录", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(good_info.this,login.class);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("取消",null)
                            .create();
                    dialog.show();
                }else{
//                    person.addGoodName(Name);
//                    person.addGoodPrice(Price);
//                    person.addGoodIcon(Icon);
//                    person.addGoodNum(Integer.parseInt(t6.getText().toString()));

                    Myhelper myhelper = new Myhelper(good_info.this);
                    SQLiteDatabase w_db = myhelper.getWritableDatabase();
                    SQLiteDatabase r_db = myhelper.getReadableDatabase();

                    Cursor cursor = r_db.query("good",null,"name=?",new String[]{Name},null,null,null);
                    cursor.moveToNext();
                    good_id = cursor.getInt(0);


                    ContentValues contentValues = new ContentValues();
                    contentValues.put("user",person.getId());
                    contentValues.put("goodid",good_id);
                    contentValues.put("goodnum",Integer.parseInt(t6.getText().toString()));
                    w_db.insert("shopcart",null,contentValues);

                    w_db.close();
                    r_db.close();
                    cursor.close();

                    AlertDialog dialog1;
                    dialog1 = new AlertDialog.Builder(good_info.this).setTitle("提示")
                            .setMessage("添加购物车成功！")
                            .setNegativeButton("确定", null)
                            .create();
                    dialog1.show();
                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_info person = new person_info();
                if (person.getName().equals("")) {
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(good_info.this).setTitle("提示")
                            .setMessage("请登录后进行操作！")
                            .setNegativeButton("登录", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(good_info.this, login.class);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("取消", null)
                            .create();
                    dialog.show();
                } else {
                    //person_info person = new person_info();

                    Myhelper myhelper = new Myhelper(good_info.this);
                    SQLiteDatabase r_db = myhelper.getReadableDatabase();


                    Cursor cursor = r_db.query("good", null, "name=?", new String[]{Name}, null, null, null);
                    cursor.moveToNext();
                    good_id = cursor.getInt(0);
                    cursor.close();
                    r_db.close();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", Name);
                    contentValues.put("price", Price);
                    contentValues.put("num", Integer.parseInt(t6.getText().toString()));
                    contentValues.put("icon", Icon);
                    contentValues.put("goodid", good_id);
                    person.addbuygood(contentValues);

                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("totalNum", Integer.parseInt(t6.getText().toString()));
                    bundle1.putDouble("totalPrice", mul(Integer.parseInt(t6.getText().toString()), Price));
                    bundle1.putInt("flag", 1);
                    Intent intent = new Intent();
                    intent.putExtras(bundle1);
                    intent.setClass(good_info.this, pay_page.class);
                    startActivity(intent);
                }
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    Intent intent = new Intent(good_info.this,main_interface.class);
                    intent.putExtra("flag",6);
                    startActivity(intent);
                }else if (flag ==1){
                    Intent intent = new Intent(good_info.this,main_interface.class);
                    intent.putExtra("flag",10);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(good_info.this,Favourite.class);
                    startActivity(intent);
                }

            }
        });

        Favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_info person = new person_info();
                if (person.getName().equals("")){
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(good_info.this).setTitle("提示")
                            .setMessage("请登录后进行操作！")
                            .setNegativeButton("登录", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(good_info.this,login.class);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("取消",null)
                            .create();
                    dialog.show();
                }else {
                    Myhelper myhelper = new Myhelper(good_info.this);
                    SQLiteDatabase w_db = myhelper.getWritableDatabase();
                    SQLiteDatabase r_db = myhelper.getReadableDatabase();

                    Cursor cursor = r_db.query("good", null, "name=?", new String[]{Name}, null, null, null);
                    cursor.moveToNext();
                    good_id = cursor.getInt(0);
                    ContentValues contentValues = new ContentValues();
                    person_info personInfo = new person_info();
                    contentValues.put("user", personInfo.getId());
                    contentValues.put("goodid", good_id);
                    w_db.insert("favourite", null, contentValues);
                    cursor.close();
                    r_db.close();
                    w_db.close();
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(good_info.this).setTitle("提示")
                            .setMessage("收藏成功！")
                            .setNegativeButton("确定", null)
                            .create();
                    dialog.show();
                }
            }
        });
    }

    public static double mul(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).doubleValue();
    }
}
