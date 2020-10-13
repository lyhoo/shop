package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class category_info extends AppCompatActivity {
    TextView t1;
    ListView l1;
    ImageView i1;
    String[] Ids = null;
    List<ContentValues> good_s = new ArrayList<ContentValues>();
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_info);


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        t1 = findViewById(R.id.textView);
        l1 = findViewById(R.id.categoryinfo);
        i1 = findViewById(R.id.fanhui);

        Intent intent = getIntent();
        Ids = intent.getStringArrayExtra("ids");
        Name = intent.getStringExtra("name");
        t1.setText(Name);
        Myhelper myhelper = new Myhelper(category_info.this);
        SQLiteDatabase db = myhelper.getReadableDatabase();
        for (int i=0;i<Ids.length;i++){
            Cursor cursor = db.query("good",null,"id=?",new String[]{Ids[i]},null,null,null);
            cursor.moveToNext();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",cursor.getString(2));
            contentValues.put("address",cursor.getString(3));
            contentValues.put("price",cursor.getDouble(4));
            contentValues.put("freight",cursor.getInt(5));
            contentValues.put("icon",cursor.getInt(1));
            good_s.add(contentValues);
            cursor.close();
        }
        db.close();

        MyBaseAdapter mAdapter = new MyBaseAdapter();
        l1.setAdapter(mAdapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("name",good_s.get(i).get("name").toString());
                bundle.putString("address",good_s.get(i).get("address").toString());
                bundle.putDouble("price",good_s.get(i).getAsDouble("price"));
                bundle.putInt("pic",good_s.get(i).getAsInteger("icon"));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(category_info.this,good_info.class);
                startActivity(intent);
            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category_info.this,main_interface.class);
                intent.putExtra("flag",9);
                startActivity(intent);
            }
        });
    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return good_s.size();
        }
        @Override
        public Object getItem(int position){
            return good_s.get(position).get("name");
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        public View getView(int position , View converView , ViewGroup parent){
            View view = View.inflate(category_info.this,R.layout.list_item,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodAddress = (TextView) view.findViewById(R.id.good_address);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.imageView6);

            GoodName.setText(good_s.get(position).get("name").toString());
            GoodAddress.setText(good_s.get(position).get("address").toString());
            GoodPrice.setText("¥"+good_s.get(position).get("price").toString());
            GoodPic.setBackgroundResource(good_s.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
