package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myappgit.R;

public class MyAdapter extends BaseAdapter {
    int[] imageres;
    Context context;
    public boolean isUp = true;

    public MyAdapter(int[] imageres, Context context) {
        super();
        this.imageres = imageres;
        this.context = context;
    }

    interface onBGscroll {
    }

    @Override
    public int getCount() {
        return imageres.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = View.inflate(context, R.layout.listitem, null);
        ImageView iv = (ImageView) item.findViewById(R.id.iv);
        TextView tView = (TextView) item.findViewById(R.id.tv_2);
        tView.setText("CITY" + position);
//        Resources resources = context.getResources();
//        iv.setImageDrawable(resources.getDrawable(imageres[position]));
        Glide.with(context).load(imageres[position]).into(iv);


        // 判断是什么方向划出图片  提前滑动预定地点
        if (isUp)
            iv.scrollBy(0, (-1 * 350));
        else
            iv.scrollBy(0, 350);
        return item;
    }

    private static class viewHorder {

    }
}
