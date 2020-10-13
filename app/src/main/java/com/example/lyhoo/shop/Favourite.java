package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Favourite extends AppCompatActivity {
    ListView listView;
    TextView t1;
    ImageView i1;
    List<ContentValues> historyGood = new ArrayList<ContentValues>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_shipped);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        person_info personInfo = new person_info();
        Myhelper myhelper = new Myhelper(Favourite.this);
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT good.id,good.name,good.price,good.pic,good.address FROM good,favourite WHERE favourite.user=? AND favourite.goodid= good.id" ,new String[]{personInfo.getId()+""});
        listView = (ListView) findViewById(R.id.history);
        t1 = (TextView) findViewById(R.id.textView1);
        i1 = (ImageView) findViewById(R.id.fanhui);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Favourite.this,main_interface.class);
                intent.putExtra("flag",4);
                startActivity(intent);
            }
        });
        if (cursor.getCount()==0){
            t1.setText("您没有收藏过商品！");
        }else {
            t1.setText("共收藏"+cursor.getCount()+"件商品");
            while (cursor.moveToNext()){
                ContentValues contentValues = new ContentValues();
                contentValues.put("id",cursor.getInt(0));
                contentValues.put("name",cursor.getString(1));
                contentValues.put("price",cursor.getDouble(2));
                contentValues.put("icon",cursor.getInt(3));
                contentValues.put("address",cursor.getString(4));
                historyGood.add(contentValues);
            }
            cursor.close();
            db.close();
        }

        MyBaseAdapter mAdapter = new MyBaseAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("name",historyGood.get(i).get("name").toString());
                bundle.putString("address",historyGood.get(i).get("address").toString());
                bundle.putDouble("price",historyGood.get(i).getAsDouble("price"));
                bundle.putInt("pic",historyGood.get(i).getAsInteger("icon"));
                bundle.putInt("flag",2);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(Favourite.this,good_info.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(Favourite.this,main_interface.class);
            intent.putExtra("flag",4);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return historyGood.size();
        }
        @Override
        public Object getItem(int position){
            return historyGood.get(position).getAsString("name");
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        public View getView(int position , View converView , ViewGroup parent){
            View view = View.inflate(Favourite.this,R.layout.list_item,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            TextView GoodAddress = (TextView) view.findViewById(R.id.good_address);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.imageView6);

            GoodName.setText(historyGood.get(position).getAsString("name"));
            GoodPrice.setText(historyGood.get(position).getAsDouble("price")+"元");
            GoodAddress.setText(historyGood.get(position).getAsString("address"));
            GoodPic.setBackgroundResource(historyGood.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
