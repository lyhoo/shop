package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryFragment extends Fragment {
    View mView;
    ListView listView;
    SearchView searchView;
    List<ContentValues> good_s = null;
    private String[] name = {"iphone XS MAX","火锅底料","围巾","手套","咖啡"};
    private String[] address = {"河南省","重庆市","湖南省","湖北省","陕西省"};
    private double[] price = {8888.88,49.99,52,22,36.2};
    private int[] icon = {R.drawable.good_1,R.drawable.good_2,R.drawable.good_3,R.drawable.good_4,R.drawable.good_5};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if (mView == null){
            mView = inflater.inflate(R.layout.activity_categories,null);
        }
        listView = mView.findViewById(R.id.good_i);
        searchView = mView.findViewById(R.id.search_input);
        searchView.setFocusable(false);
        good_s = new ArrayList<ContentValues>();
        SQLiteDatabase db;
        Myhelper myhelper = new Myhelper(getActivity());
        db = myhelper.getReadableDatabase();
        Cursor cursor = db.query("good",null,null,null,null,null,null);
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
                intent.setClass(getActivity(),good_info.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getActivity(),Search.class);
                intent.putExtra("info",s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return mView;
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
            View view = View.inflate(getActivity(),R.layout.list_item,null);
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
