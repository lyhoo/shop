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

public class BrowsingHistory extends AppCompatActivity {
    ListView listView;
    TextView t1;
    ImageView i1;
    TextView Clear;
    List<ContentValues> historyGood = new ArrayList<ContentValues>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_history);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        final person_info personInfo = new person_info();
        historyGood = personInfo.getbrowsingHistory();
        Clear = findViewById(R.id.clear);
        listView = (ListView) findViewById(R.id.history);
        t1 = (TextView) findViewById(R.id.textView1);
        i1 = (ImageView) findViewById(R.id.fanhui);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrowsingHistory.this,main_interface.class);
                intent.putExtra("flag",4);
                startActivity(intent);
            }
        });
        if (historyGood.size()==0){
            t1.setText("还没有浏览过商品！");
        }else {
            t1.setText("共浏览过"+historyGood.size()+"件商品");
        }

        MyBaseAdapter mAdapter = new MyBaseAdapter();
        listView.setAdapter(mAdapter);
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personInfo.clearbrowsingHistory();
                Intent intent = new Intent(BrowsingHistory.this,BrowsingHistory.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(BrowsingHistory.this,main_interface.class);
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
            View view = View.inflate(BrowsingHistory.this,R.layout.list_item_history,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodNum = (TextView) view.findViewById(R.id.good_num);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            TextView GoodTime = (TextView) view.findViewById(R.id.buytime);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.icon);

            GoodName.setText(historyGood.get(position).getAsString("name"));
            GoodNum.setText("");
            GoodPrice.setText("");
            GoodTime.setText("浏览时间："+historyGood.get(position).getAsString("time"));
            GoodPic.setBackgroundResource(historyGood.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
