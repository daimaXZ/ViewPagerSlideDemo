package com.my.project;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyViewPager viewPager;
    private MyAdapter myAdapter;
    private ArrayList<View> list;
    private int dp5;
    private LinearLayout ll;
    private int lastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        ll = (LinearLayout) findViewById(R.id.ll);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int windowWidth = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.height = windowWidth / 2;
        viewPager.setLayoutParams(layoutParams);
        int[] data = {R.mipmap.imag1, R.mipmap.imag2, R.mipmap.imag3,R.mipmap.imag4,R.mipmap.imag5};
        myAdapter = new MyAdapter(this, data);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(data.length * 1000);
        list = new ArrayList<>();
        dp5 = (int) getResources().getDimension(R.dimen.dp5);
        for (int x=0; x<data.length; x++){
            View view = new View(this);
            final int position = x;
            LinearLayout.LayoutParams mLayoutParams;
            if(viewPager.getCurrentItem() == x){
                mLayoutParams = new LinearLayout.LayoutParams(dp5 *4, dp5);
            }else{
                mLayoutParams = new LinearLayout.LayoutParams(dp5, dp5);
            }
            mLayoutParams.setMargins(0, 0, dp5, 0);
            view.setLayoutParams(mLayoutParams);
            view.setBackgroundResource(R.drawable.myview);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(position);
                }
            });
            ll.addView(view);
            list.add(view);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i==0){
                View view = list.get(i);
                LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(dp5 *4, dp5);
                mLayoutParams.setMargins(0, 0, dp5, 0);
                view.setLayoutParams(mLayoutParams);
            }
        }
       viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               if (positionOffset == 0) return;
               if (myAdapter == null) myAdapter = (MyAdapter) viewPager.getAdapter();
               //缩小当前展示的View
               View view = myAdapter.map.get(position);
               float slide1 = Math.max(0.8f, 1 - (0.2f * positionOffset));
               view.setScaleX(slide1);
               view.setScaleY(slide1);
               //放大下一个显示的View
               float slide2 = Math.min(1f, 0.8f + (0.2f * positionOffset));
               View nextView = myAdapter.map.get(position + 1);
               nextView.setScaleX(slide2);
               nextView.setScaleY(slide2);
           }

           @Override
           public void onPageSelected(int position) {
               position = position%5;
               if (position != lastPosition) {
                   final View lastView = list.get(lastPosition);
                   final View view = list.get(position);
                   ValueAnimator valueAnimator = ValueAnimator.ofInt(dp5 * 4, dp5);
                   valueAnimator.setDuration(200);
                   valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                       @Override
                       public void onAnimationUpdate(ValueAnimator animation) {
                           int animatedValue = (int) animation.getAnimatedValue();
                           ViewGroup.LayoutParams layoutParams = lastView.getLayoutParams();
                           layoutParams.width = animatedValue;
                           lastView.setLayoutParams(layoutParams);

                           ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                           layoutParams2.width = dp5 * 5 - animatedValue;
                           view.setLayoutParams(layoutParams2);
                       }
                   });
                   valueAnimator.start();
               }
               lastPosition = position;
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
    }
}
