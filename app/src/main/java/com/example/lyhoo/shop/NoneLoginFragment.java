package com.example.lyhoo.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NoneLoginFragment extends Fragment{
    View mView;
    Button login;
    Button register;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if (mView == null){
            mView = inflater.inflate(R.layout.activity_non_login,null);
        }

        login = mView.findViewById(R.id.button1);
        register = mView.findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),login.class);
                startActivityForResult(intent,1);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),register.class);
                startActivity(intent);
            }
        });

        return mView;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1&&resultCode==10){
            main_interface mainInterface = (main_interface) getActivity();
            FragmentManager fManger =mainInterface.getManager();
            FragmentTransaction fragmentTransaction = fManger.beginTransaction();
            fragmentTransaction.replace(R.id.ViewPagerLayout,new PersonalCenterFragment());
            fragmentTransaction.commit();
        }
    }
}
