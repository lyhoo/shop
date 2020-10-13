package com.example.lyhoo.shop;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class pay_page extends AppCompatActivity {
    ListView listView;
    Button payButton;
    TextView userName;
    TextView userPhone;
    TextView userAddress;
    TextView totalMoney;
    TextView Update;
    ImageView Return;
    List<ContentValues> payGood = new ArrayList<ContentValues>();
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_page);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }


        listView = (ListView) findViewById(R.id.pay_good);
        payButton = (Button) findViewById(R.id.button5);
        userName = (TextView) findViewById(R.id.user_name);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userAddress = (TextView) findViewById(R.id.user_address);
        totalMoney = (TextView) findViewById(R.id.textView20);
        Return = (ImageView) findViewById(R.id.fanhui);
        Update = (TextView) findViewById(R.id.update);

        final person_info person = new person_info();
        if (person.getConsignee().equals("0")){
            userName.setText(person.getName());
        }else{
            userName.setText(person.getConsignee());
        }
       if (person.getPhone().equals("0")){
            userPhone.setText("请先填写收货人相关信息！");
       }else{
           userPhone.setText(person.getPhone());
       }
        if (person.getAddress().equals("0")){
            userAddress.setText("");
        }else{
            userAddress.setText(person.getAddress());
        }

        Bundle bundle = getIntent().getExtras();
        int goodCount = bundle.getInt("goodNum");
        int totalNum = bundle.getInt("totalNum");
        double totalPrice = bundle.getDouble("totalPrice");
        flag = bundle.getInt("flag");
        if (flag==1){
            ContentValues contentValues =new ContentValues();
            contentValues.put("name",person.getbuygood().get(0).getAsString("name"));
            contentValues.put("price",person.getbuygood().get(0).getAsDouble("price"));
            contentValues.put("num",person.getbuygood().get(0).getAsInteger("num"));
            contentValues.put("icon",person.getbuygood().get(0).getAsInteger("icon"));
            contentValues.put("goodid",person.getbuygood().get(0).getAsInteger("goodid"));
            payGood.add(contentValues);
        }else {
            for (int i = 0;i<goodCount;i++){
                ContentValues contentValues =new ContentValues();
                contentValues.put("name",person.getbuygood().get(i).getAsString("name"));
                contentValues.put("price",person.getbuygood().get(i).getAsDouble("price"));
                contentValues.put("num",person.getbuygood().get(i).getAsInteger("num"));
                contentValues.put("icon",person.getbuygood().get(i).getAsInteger("icon"));
                contentValues.put("goodid",person.getbuygood().get(i).getAsInteger("goodid"));
                contentValues.put("shopcartid",person.getbuygood().get(i).getAsInteger("shopcartid"));
                payGood.add(contentValues);
            }
        }
        person.clearbuygood();
        totalMoney.setText(totalNum+"件商品，"+totalPrice+"元");
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        listView.setAdapter(mAdapter);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAddress.getText().toString().equals("")){
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(pay_page.this).setTitle("提示")
                            .setMessage("请先填写收货人信息")
                            .setNegativeButton("确定", null)
                            .create();
                    dialog.show();
                }else{
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(pay_page.this).setTitle("提示")
                            .setMessage("确定付款吗？")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    person_info personInfo = new person_info();
                                    Myhelper myhelper = new Myhelper(pay_page.this);
                                    SQLiteDatabase db = myhelper.getWritableDatabase();

                                    if (flag==1){
                                        ContentValues values = new ContentValues();
                                        values.put("user",personInfo.getId());
                                        values.put("goodid",payGood.get(0).getAsInteger("goodid"));
                                        values.put("goodnum",payGood.get(0).getAsInteger("num"));
                                        db.insert("history",null,values);
                                    }else{
                                        for (int j=0;j<payGood.size();j++){
                                            ContentValues values = new ContentValues();
                                            values.put("user",personInfo.getId());
                                            values.put("goodid",payGood.get(j).getAsInteger("goodid"));
                                            values.put("goodnum",payGood.get(j).getAsInteger("num"));
                                            db.insert("history",null,values);
                                            db.delete("shopcart","id=?",new String[]{payGood.get(j).getAsInteger("shopcartid")+""});
                                        }
                                    }
                                    db.close();
                                    Intent intent;
                                    intent = new Intent(pay_page.this,pay_succeed.class);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("取消", null).create();
                    dialog.show();
                }

            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pay_page.this,main_interface.class);
                intent.putExtra("flag",7);
                startActivity(intent);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pay_page.this,address_phone.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return payGood.size();
        }
        @Override
        public Object getItem(int position){
            return payGood.get(position).get("name");
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        public View getView(int position , View converView , ViewGroup parent){
            View view = View.inflate(pay_page.this,R.layout.list_item_shopping,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodNum = (TextView) view.findViewById(R.id.good_num);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.icon);

            GoodName.setText(payGood.get(position).getAsString("name"));
            GoodNum.setText("×"+payGood.get(position).getAsInteger("num").toString());
            GoodPrice.setText("¥"+payGood.get(position).getAsDouble("price").toString());
            GoodPic.setBackgroundResource(payGood.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
