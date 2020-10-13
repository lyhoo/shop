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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToBeShipped extends AppCompatActivity {
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
        Myhelper myhelper = new Myhelper(ToBeShipped.this);
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT history.id,good.name,good.price,history.goodnum,history.buytime,good.pic FROM good,history WHERE history.user=? AND history.goodid= good.id" ,new String[]{personInfo.getId()+""});
        listView = (ListView) findViewById(R.id.history);
        t1 = (TextView) findViewById(R.id.textView1);
        i1 = (ImageView) findViewById(R.id.fanhui);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToBeShipped.this,main_interface.class);
                intent.putExtra("flag",4);
                startActivity(intent);
            }
        });
        if (cursor.getCount()==0){
            t1.setText("您还没有买过商品！");
        }else {
            t1.setText("共买过"+cursor.getCount()+"件商品");
            while (cursor.moveToNext()){
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",cursor.getString(1));
                contentValues.put("price",cursor.getDouble(2));
                contentValues.put("num",cursor.getInt(3));
                contentValues.put("time",cursor.getString(4));
                contentValues.put("icon",cursor.getInt(5));
                historyGood.add(contentValues);
            }
            cursor.close();
            db.close();
        }

        MyBaseAdapter mAdapter = new MyBaseAdapter();
        listView.setAdapter(mAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(ToBeShipped.this,main_interface.class);
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
            View view = View.inflate(ToBeShipped.this,R.layout.list_item_history,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodNum = (TextView) view.findViewById(R.id.good_num);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            TextView GoodTime = (TextView) view.findViewById(R.id.buytime);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.icon);

            GoodName.setText(historyGood.get(position).getAsString("name"));
            GoodNum.setText("数量："+historyGood.get(position).getAsInteger("num"));
            GoodPrice.setText(historyGood.get(position).getAsDouble("price")+"元");
            GoodTime.setText("购买时间："+historyGood.get(position).getAsString("time"));
            GoodPic.setBackgroundResource(historyGood.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
