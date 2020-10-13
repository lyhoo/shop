package com.example.lyhoo.shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class main_interface extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity.TAG";

    public LinearLayout Home;
    public LinearLayout Category;
    public LinearLayout Shopping;
    public LinearLayout Personal;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    ViewPager mViewPager;
    ViewPagerFragmentAdapter mViewPagerFramentAdapter;
    FragmentManager mFragmentManager;

    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //changeStatusBarTextImgColor(true);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        mFragmentManager = getSupportFragmentManager();
        initFragmentList();
        mViewPagerFramentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        initView();
        initViewPager();

        Intent intent =getIntent();
        int id = intent.getIntExtra("flag",0);
        switch (id){
            case 1:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
                mViewPager.setCurrentItem(3);//登录
                break;
            case 2:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
                mViewPager.setCurrentItem(3);//注册
                break;
            case 3:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
                mViewPager.setCurrentItem(4);//退出
                break;
            case 4:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
                mViewPager.setCurrentItem(3);//查看订单
                break;
            case 5:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.selected3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(2);//支付成功
                break;
            case 6:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.selected2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(1);//查看商品信息
                break;
            case 7:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.selected3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(2);//支付界面
                break;
            case 8:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.selected3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(2);//修改收货人信息
                break;
            case 9:
                imageView1.setImageResource(R.drawable.selected1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(0);//分类查看信息
                break;
            case 10:
                imageView1.setImageResource(R.drawable.selected1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(0);//主页查看商品信息
                break;
            case 11:
                imageView1.setImageResource(R.drawable.selected1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(0);//搜索返回
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    public void initViewPager(){
        mViewPager.addOnPageChangeListener(new ViewPagetOnPageChangeListener());
        mViewPager.setAdapter(mViewPagerFramentAdapter);
        mViewPager.setCurrentItem(0);
        imageView1.setImageResource(R.drawable.selected1);
    }

    public void initFragmentList(){
        Fragment homepageFragment = new HomePageFragment();
        Fragment categoryrFragment = new CategoryFragment();
        Fragment shoppingCartFragment = new ShoppingCartFragment();
        Fragment personalCenterFragment = new PersonalCenterFragment();
        Fragment noneLoginFragment = new NoneLoginFragment();
        mFragmentList.add(homepageFragment);
        mFragmentList.add(categoryrFragment);
        mFragmentList.add(shoppingCartFragment);
        mFragmentList.add(personalCenterFragment);
        mFragmentList.add(noneLoginFragment);
    }

    public void initView(){
        mViewPager = (ViewPager) findViewById(R.id.ViewPagerLayout);
        Home = (LinearLayout) findViewById(R.id.l1);
        Category = (LinearLayout) findViewById(R.id.l2);
        Shopping = (LinearLayout) findViewById(R.id.l3);
        Personal = (LinearLayout) findViewById(R.id.l4);
        Home.setOnClickListener(this);
        Category.setOnClickListener(this);
        Shopping.setOnClickListener(this);
        Personal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.l1:
                imageView1.setImageResource(R.drawable.selected1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.l2:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.selected2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.l3:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.selected3);
                imageView4.setImageResource(R.drawable.dibu_4);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.l4:
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
                person_info person = new person_info();
                if (person.getName().equals("")){
                    mViewPager.setCurrentItem(4);
                }else {
                    mViewPager.setCurrentItem(3);
                }

                break;
            default:
                break;
        }
    }

    class ViewPagetOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){

        }
        @Override
        public void onPageSelected(int positon){
            if (positon==0){
                imageView1.setImageResource(R.drawable.selected1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
            }else if (positon==1){
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.selected2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.dibu_4);
            }else if (positon==2){
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.selected3);
                imageView4.setImageResource(R.drawable.dibu_4);
            }else {
                imageView1.setImageResource(R.drawable.dibu_1);
                imageView2.setImageResource(R.drawable.dibu_2);
                imageView3.setImageResource(R.drawable.dibu_3);
                imageView4.setImageResource(R.drawable.selected4);
            }
            Log.d(TAG,"onPageSelected");
        }
        @Override
        public void onPageScrollStateChanged(int state){
            Log.d(TAG,"onPageScrollStateChanged");
        }
    }

    public FragmentManager getManager(){
        return mFragmentManager;
    }

    public void changeStatusBarTextImgColor(boolean isBlack) {
        if (isBlack) {
            //设置状态栏黑色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //恢复状态栏白色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
}
