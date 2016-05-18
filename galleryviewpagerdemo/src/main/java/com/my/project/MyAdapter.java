package com.my.project;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshuang on 16/4/18.
 */
public class MyAdapter extends PagerAdapter {

    private int[] data;
    private Activity activity;
    public Map<Integer, View> map = new HashMap<>();

    public MyAdapter(Activity activity, int[] data){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(activity, R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //初始化除了第一个
        if(position != data.length*1000){
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
        }
        imageView.setBackgroundResource(data[position % data.length]);
        container.addView(view);
        map.put(position, view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        map.remove(position);
        container.removeView((View) object);
    }
}
