package com.example.lyhoo.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalCenterFragment extends Fragment{
    View mView;
    ImageView bt1;
    TextView t1;
    TextView t16;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;

    LinearLayout linearLayout5;//收藏商品
    LinearLayout linearLayout6;//浏览记录
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if (mView == null){
                mView = inflater.inflate(R.layout.activity_personal_center,null);
        }

        person_info person = new person_info();
        bt1 = mView.findViewById(R.id.imageView2);
        t1 = mView.findViewById(R.id.textView1);
        t16 = mView.findViewById(R.id.textView16);
        linearLayout1 = mView.findViewById(R.id.linearLayout7);
        linearLayout2 = mView.findViewById(R.id.linearLayout8);
        linearLayout3 = mView.findViewById(R.id.linearLayout4);
        linearLayout4 = mView.findViewById(R.id.linearLayout10);
        linearLayout5 = mView.findViewById(R.id.linearLayout3);
        linearLayout6 = mView.findViewById(R.id.linearLayout5);
        t1.setText(person.getName());

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),set.class);
                startActivity(intent);
            }
        });

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ToSend.class);
                startActivity(intent);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ToGet.class);
                startActivity(intent);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ToEvaluate.class);
                startActivity(intent);
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AfterSale.class);
                startActivity(intent);
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Favourite.class);
                startActivity(intent);
            }
        });
        t16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ToBeShipped.class);
                startActivity(intent);
            }
        });
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BrowsingHistory.class);
                startActivity(intent);
            }
        });
        return mView;
    }
}
