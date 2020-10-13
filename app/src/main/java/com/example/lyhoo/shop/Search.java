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
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    SearchView SearchInput;
    ListView listView;
    List<ContentValues> good_s = null;
    ImageView Return;
    TextView None;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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



        Bundle bundle = getIntent().getExtras();
        final String info = bundle.getString("info");

        Return = findViewById(R.id.fanhui);
        listView = findViewById(R.id.search_info);
        SearchInput = findViewById(R.id.search_input);
        None = findViewById(R.id.none);
        SearchInput.setQueryHint(info);
        SearchInput.setFocusable(false);


        good_s = new ArrayList<ContentValues>();
        SQLiteDatabase db;
        Myhelper myhelper = new Myhelper(Search.this);
        db = myhelper.getReadableDatabase();
        Cursor cursor = db.query("good",null,"category=?",new String[]{info},null,null,null);
        if (cursor.getCount()==0){
            None.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else{
            ContentValues values = new ContentValues();
            cursor.moveToNext();
            values.put("name",cursor.getString(2));
            values.put("address",cursor.getString(3));
            values.put("price",cursor.getDouble(4));
            values.put("freight",cursor.getInt(5));
            values.put("icon",cursor.getInt(1));
            good_s.add(values);
            while (cursor.moveToNext()){
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",cursor.getString(2));
                contentValues.put("address",cursor.getString(3));
                contentValues.put("price",cursor.getDouble(4));
                contentValues.put("freight",cursor.getInt(5));
                contentValues.put("icon",cursor.getInt(1));
                good_s.add(contentValues);
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
                bundle.putString("name",good_s.get(i).get("name").toString());
                bundle.putString("address",good_s.get(i).get("address").toString());
                bundle.putDouble("price",good_s.get(i).getAsDouble("price"));
                bundle.putInt("pic",good_s.get(i).getAsInteger("icon"));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(Search.this,good_info.class);
                startActivity(intent);
            }
        });
        SearchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(Search.this,Search.class);
                intent.putExtra("info",s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this,main_interface.class);
                intent.putExtra("flag",11);
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
            View view = View.inflate(Search.this,R.layout.list_item,null);
            TextView GoodName = (TextView) view.findViewById(R.id.good_name);
            TextView GoodAddress = (TextView) view.findViewById(R.id.good_address);
            TextView GoodPrice = (TextView) view.findViewById(R.id.good_price);
            ImageView GoodPic = (ImageView) view.findViewById(R.id.imageView6);

            GoodName.setText(good_s.get(position).get("name").toString());
            GoodAddress.setText(good_s.get(position).get("address").toString());
            GoodPrice.setText("¥"+good_s.get(position).get("price").toString());
            //String icon = "R.drawable.good_1";
            GoodPic.setBackgroundResource(good_s.get(position).getAsInteger("icon"));
            return view;
        }
    }
}
