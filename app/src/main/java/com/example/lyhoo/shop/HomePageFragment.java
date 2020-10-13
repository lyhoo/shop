package com.example.lyhoo.shop;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener {
    View mView;
    SearchView search;
    CardView c1;
    CardView c2;
    CardView c3;
    CardView c4;
    CardView c5;
    CardView c6;
    CardView c7;
    CardView c8;
    CardView c9;
    CardView c10;

    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;
    LinearLayout l4;

    private int[] imgs = {R.drawable.lunbo1,R.drawable.lunbo2,R.drawable.lunbo3,R.drawable.lunbo4,R.drawable.lunbo5};
    private List<View> viewList;
    BannerView bannerView;


    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
    static int f=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if (mView == null){
            mView = inflater.inflate(R.layout.activity_home_page,null);
        }
        search = mView.findViewById(R.id.search_input);
        search.setFocusable(false);
        c1 = mView.findViewById(R.id.home1);
        c2 = mView.findViewById(R.id.home2);
        c3 = mView.findViewById(R.id.home3);
        c4 = mView.findViewById(R.id.home4);
        c5 = mView.findViewById(R.id.home5);
        c6 = mView.findViewById(R.id.home6);
        c7 = mView.findViewById(R.id.home7);
        c8 = mView.findViewById(R.id.home8);
        c9 = mView.findViewById(R.id.home9);
        c10 = mView.findViewById(R.id.home10);

        l1 = mView.findViewById(R.id.linearLayout1);
        l2 = mView.findViewById(R.id.linearLayout2);
        l3 = mView.findViewById(R.id.linearLayout3);
        l4 = mView.findViewById(R.id.linearLayout4);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        c9.setOnClickListener(this);
        c10.setOnClickListener(this);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(getActivity());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView) mView.findViewById(R.id.banner);
        bannerView.startLoop(true);

        bannerView.setViewList(viewList);

//            // 初始化布局 View视图
//            initViews();
//            // Model数据
//            initData();
//            f++;
//
//            // Controller 控制器
//            initAdapter();
//
//            // 开启轮询
//            new Thread() {
//                public void run() {
//                    isRunning = true;
//                    while (isRunning) {
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        // 往下跳一位
//                        getActivity().runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                System.out.println("设置当前位置: " + viewPager.getCurrentItem());
//                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//                            }
//                        });
//                    }
//                }
//
//                ;
//            }.start();




        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home1:
                openInfo(10);
                break;
            case R.id.home2:
                openInfo(3);
                break;
            case R.id.home3:
                openInfo(11);
                break;
            case R.id.home4:
                openInfo(2);
                break;
            case R.id.home5:
                openInfo(6);
                break;
            case R.id.home6:
                openInfo(7);
                break;
            case R.id.home7:
                openInfo(4);
                break;
            case R.id.home8:
                 openInfo(12);
                 break;
            case R.id.home9:
                openInfo(1);
                break;
            case R.id.home10:
                openInfo(8);
                break;
            case R.id.linearLayout1:
                String [] ids = {"2","3","10","17"};
                Bundle bundle = new Bundle();
                bundle.putStringArray("ids",ids);
                bundle.putString("name","电子产品");
                Intent intent = new Intent(getActivity(),category_info.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linearLayout2:
                String [] ids1 = {"1","5","6","11","15","16"};
                Bundle bundle1 = new Bundle();
                bundle1.putStringArray("ids",ids1);
                bundle1.putString("name","家居装饰");
                Intent intent1 = new Intent(getActivity(),category_info.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.linearLayout3:
                String [] ids2 = {"4","8","9","12","14","18","19"};
                Bundle bundle2 = new Bundle();
                bundle2.putStringArray("ids",ids2);
                bundle2.putString("name","生活用品");
                Intent intent2 = new Intent(getActivity(),category_info.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.linearLayout4:
                String [] ids3 = {"7","13","20"};
                Bundle bundle3 = new Bundle();
                bundle3.putStringArray("ids",ids3);
                bundle3.putString("name","食品");
                Intent intent3 = new Intent(getActivity(),category_info.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
        }

    }

    public void openInfo(int i){
        Myhelper myhelper = new Myhelper(getActivity());
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor cursor = db.query("good",null,"id=?",new String[]{i+""},null,null,null);
        cursor.moveToNext();
        Bundle bundle = new Bundle();
        bundle.putString("name",cursor.getString(2));
        bundle.putString("address",cursor.getString(3));
        bundle.putDouble("price",cursor.getDouble(4));
        bundle.putInt("pic",cursor.getInt(1));
        bundle.putInt("flag",1);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(getActivity(),good_info.class);
        db.close();
        startActivity(intent);
    }
    private void initViews() {
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(this);// 设置页面更新监听
//		viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
        ll_point_container = (LinearLayout) mView.findViewById(R.id.ll_point_container);


    }

    /**
     * 初始化要显示的数据
     */
    private void initData() {
        // 图片资源id数组
        imageResIds = new int[]{R.drawable.lunbo_1, R.drawable.lunbo_2, R.drawable.lunbo_3, R.drawable.lunbo_4, R.drawable.lunbo_5};


        // 初始化要展示的5个ImageView
        imageViewList = new ArrayList<ImageView>();

        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getActivity());
            pointView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
    }

    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
        previousSelectedPosition = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapter());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//			newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // 滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();



//		for (int i = 0; i < ll_point_container.getChildCount(); i++) {
//			View childAt = ll_point_container.getChildAt(position);
//			childAt.setEnabled(position == i);
//		}
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        previousSelectedPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }
}
