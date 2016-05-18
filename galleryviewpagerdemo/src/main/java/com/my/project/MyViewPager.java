package com.my.project;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by zhaoshuang on 16/4/19.
 */
public class MyViewPager extends ViewPager {


    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        int dp80 = (int) getResources().getDimension(R.dimen.dp90);
        //增加viewpager的层次效果
        setPageMargin(-dp80);
        //缓存5个界面
        setOffscreenPageLimit(5);
    }
}
