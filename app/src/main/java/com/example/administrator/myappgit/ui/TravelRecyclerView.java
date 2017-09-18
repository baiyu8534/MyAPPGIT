package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TravelRecyclerAdapter;


/**
 * itemBGroll rv
 * Created by baiyu on 2017/4/24.
 */
public class TravelRecyclerView extends RecyclerView {
    public TravelRecyclerView(Context context) {
        super(context);
//        init();
    }

    public TravelRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        init();
    }

    public TravelRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init();

    }

    private void init() {

        //会被activity中设置的listener覆盖。。
        this.setOnScrollListener(new TravelOnScrollListener());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    System.out.println("监听了滑动setOnScrollChangeListener");
                    int dy = scrollY - oldScrollY;
                    TravelRecyclerAdapter adapter = (TravelRecyclerAdapter) getAdapter();
                    if (dy > 0) {
                        //手指向上滑

                        for (int i = 0; i < getChildCount(); i++) {
                            View child = TravelRecyclerView.this.getChildAt(i);
                            ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                            //复位图片位置
                            //imageView.scrollBy(0, -350);
                            //350是我设定的图片向上或向下最大的滑动距离
                            System.out.println("dy:::"+dy);
                            System.out.println("dy>0  imageView.getScrollY():::"+imageView.getScrollY());
                            if (imageView.getScrollY() + (dy) / 3 < 750) {
                                //(scrollY - proY) / 2使滑动的速度不会太快。。
                                imageView.scrollBy(0, (int) ((dy) / 2.5));
                            } else {
//                        System.out.println("imageView.getScrollY()_dy::::"+imageView.getScrollY()+dy);
                            }
                            adapter.isUp = true;
                        }
                    } else {
                        for (int i = 0; i < getChildCount(); i++) {
                            View child = TravelRecyclerView.this.getChildAt(i);
                            ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                            //复位图片位置
                            //imageView.scrollBy(0, 350);
                            System.out.println("dy:::"+dy);
                            System.out.println("dy<0  imageView.getScrollY():::"+imageView.getScrollY());
                            if (imageView.getScrollY() + (dy) / 3 > -750) {
                                imageView.scrollBy(0, (int) ((dy) / 2.5));
                            } else {
//                        System.out.println("imageView.getScrollY()+dy::::"+imageView.getScrollY()+dy);
                            }
                            adapter.isUp = false;
                        }
                    }
                }
            });
        }
    }

    private class TravelOnScrollListener extends OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            System.out.println("监听了滑动");
            TravelRecyclerAdapter adapter = (TravelRecyclerAdapter) getAdapter();
            if (dy > 0) {
                //手指向上滑

                for (int i = 0; i < getChildCount(); i++) {
                    View child = TravelRecyclerView.this.getChildAt(i);
                    ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                    //复位图片位置
                    //imageView.scrollBy(0, -350);
                    //350是我设定的图片向上或向下最大的滑动距离
                    System.out.println("dy:::"+dy);
                    System.out.println("dy>0  imageView.getScrollY():::"+imageView.getScrollY());
                    if (imageView.getScrollY() + (dy) / 3 < 750) {
                        //(scrollY - proY) / 2使滑动的速度不会太快。。
                        imageView.scrollBy(0, (int) ((dy) / 2.5));
                    } else {
//                        System.out.println("imageView.getScrollY()_dy::::"+imageView.getScrollY()+dy);
                    }
                    adapter.isUp = true;
                }
            } else {
                for (int i = 0; i < getChildCount(); i++) {
                    View child = TravelRecyclerView.this.getChildAt(i);
                    ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                    //复位图片位置
                    //imageView.scrollBy(0, 350);
                    System.out.println("dy:::"+dy);
                    System.out.println("dy<0  imageView.getScrollY():::"+imageView.getScrollY());
                    if (imageView.getScrollY() + (dy) / 3 > -750) {
                        imageView.scrollBy(0, (int) ((dy) / 2.5));
                    } else {
//                        System.out.println("imageView.getScrollY()+dy::::"+imageView.getScrollY()+dy);
                    }
                    adapter.isUp = false;
                }
            }
        }
    }

}
