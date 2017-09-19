package com.example.administrator.myappgit.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TraveListAdapter;
import com.example.administrator.myappgit.utils.ScreenUtil;

/**
 * item bg roll listview
 */
public class TravelListView extends ListView {
    private static final String TAG = "bai";
    TraveListAdapter adapter;
    // 记录显示的第一个条目
    private int mFirstVisibleItem;
    // 设置头的状态 下拉刷新 释放刷新 正在刷新
    enum HEADER_MENU_STATE {
        menu_show, menu_notshow
    }
    // 处理头的初始化状态 下拉刷新
    private HEADER_MENU_STATE state = HEADER_MENU_STATE.menu_notshow;
    public void setMyAdapter(TraveListAdapter adapter) {
        this.adapter = adapter;
    }
    public TravelListView(Context context) {
        super(context);
        init();
    }
    public TravelListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public TravelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        MySrcollListener srcollListener = new MySrcollListener();
        setOnScrollListener(srcollListener);
        initheader();
    }
    private void initheader() {
        header = inflate(getContext(), R.layout.hander_trave_list_layout, null);
        addHeaderView(header);
        View headerMenu = header.findViewById(R.id.ll_headerMenu);
        headerMenu.measure(0, 0);
        headerMenuHeight = headerMenu.getMeasuredHeight();
        Log.i(TAG, headerMenuHeight + "");
        header.setPadding(0, 0, 0, -headerMenuHeight);
    }
    // 记录上一次滑动的坐标
    int proY = 0;
    private int headerMenuHeight;
    // 记录手指滑动方向
    private boolean isGoTop = true;
    private class MySrcollListener implements OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            mFirstVisibleItem = firstVisibleItem;
            int Y = myGetScrollY();
            if (Y - proY > 0) {
                Log.i("test", "手指向上滑动");
                isGoTop = true;
                View Iview;
                ImageView image;
                for (int i = firstVisibleItem == 0 ? 1 : 0; i < TravelListView.this
                        .getChildCount(); i++) {
                    Iview = TravelListView.this.getChildAt(i);
                    image = (ImageView) Iview.findViewById(R.id.iv);
                    if(image!=null) {
                        if (image.getScrollY() < ScreenUtil.getScreenHeight(getContext())/5) {
                            image.scrollBy(0, (Y - proY) / 3);
                        }
                        // 不行。。
                        // else {
                        // // 防止滑动太快，超出距离
                        // image.scrollBy(0, 350 / 2);
                        // }
                    }
                }
                adapter.isUp = true;
            } else if (Y - proY < 0) {
                Log.i("test", "手指向下滑动");
                isGoTop = false;
                View Iview;
                ImageView image;
                for (int i = firstVisibleItem == 0 ? 1 : 0; i < TravelListView.this
                        .getChildCount(); i++) {
                    Iview = TravelListView.this.getChildAt(i);
                    image = (ImageView) Iview.findViewById(R.id.iv);
                    if(image!=null) {
                        if (image.getScrollY() > -ScreenUtil.getScreenHeight(getContext())/5) {
                            image.scrollBy(0, (Y - proY) / 3);
                        }
                        // else {
                        // // 防止滑动太快，超出距离
                        // image.scrollBy(0, -350 / 2);
                        // }
                    }
                }
                adapter.isUp = false;
            }
            proY = Y;
        }
    }
    /*
     * 就相当于 listView 对一个item 的绝对的滑动距离 在 滑动过程中一直 比较两次的 差值 就可以得到每次极小距离的滑动值 在
     * OnScrollListener中 去监视 ，就可以在不同的滑动状态（手指滑动 ， 飞行滑动）去滑动你想要滑动的东西 也可以去检测
     * Listview的滑动方向
     */
    public int myGetScrollY() {
        View c = getChildAt(1);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }
    /*
     * 下拉显示菜单
     */
    // 记录按下的位置 y轴的坐标
    private int startY;
    // 头
    private View header;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                // 当头显示时，进行操作
                // mFirstVisibleItem == 0不是头完全显示，只要一显示就成立
                // header.getY() >= 0
//	if (header.getY() >= 0) {
                // 计算移动的差值
                int dis = moveY - startY;
                // 计算top值
                // // 把头进行显示
                // header.setPadding(0, 0, 0, top);
                // if (state == HEADER_MENU_STATE.menu_show && top <= 0) {
                // state = HEADER_MENU_STATE.menu_notshow;
                // } else if (state == HEADER_MENU_STATE.menu_notshow && top >
                // 0) {
                // state = HEADER_MENU_STATE.menu_show;
                // }
                if (state == HEADER_MENU_STATE.menu_notshow && header.getY() >= 0) {
                    int top = -headerMenuHeight + dis;
                    if (top < 0 && top > -headerMenuHeight) {
                        header.setPadding(0, 0, 0, top);
                        Log.i(TAG, "top" + top);
                        //使菜单在往出滑的时候不能往回滑（往下滑时不能往上滑）
                        return true;
                    } else if (top >= 0) {
                        // 保证没有空隙
                        header.setPadding(0, 0, 0, 0);
                        state = HEADER_MENU_STATE.menu_show;
                        Log.i(TAG, "菜单都显示出来了");
                        return true;
                    }
                } else if (state == HEADER_MENU_STATE.menu_show) {
                    int top = dis;
                    Log.i(TAG, "菜单都显示出来了top" + top);
                    if (top >= -headerMenuHeight && top < 0) {
                        header.setPadding(0, 0, 0, top);
                        return true;
                    } else if (top < -headerMenuHeight) {
                        // 保证没有空隙
                        header.setPadding(0, 0, 0, -headerMenuHeight);
                        state = HEADER_MENU_STATE.menu_notshow;
                        Log.i(TAG, "菜单关闭了-----");
                        return true;
                    }
                }
//	}
                // Log.i("baia", "getScaleY" + header.getScaleY());
                // Log.i("baia", "getHeight()" + header.getHeight());
                // Log.i("baia", "getScrollY()" + header.getScrollY());
                // Log.i("baia", "getMeasuredHeight()" +
                // header.getMeasuredHeight());
                // Log.i("baia", "getMeasuredWidthAndState()" +
                // header.getMeasuredWidthAndState());
                // Log.i("baia", "getY()" + header.getY());
                break;
            case MotionEvent.ACTION_UP:
                if (header.getY() >= 0) {
                    if (state == HEADER_MENU_STATE.menu_notshow && !isGoTop) {
                        header.setPadding(0, 0, 0, 0);
                        state = HEADER_MENU_STATE.menu_show;
                    } else if (state == HEADER_MENU_STATE.menu_show && isGoTop) {
                        header.setPadding(0, 0, 0, -headerMenuHeight);
                        state = HEADER_MENU_STATE.menu_notshow;
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
 /*
  * 只有在菜单是关闭 并手指向上滑 才获得事件
  *
  * @see
  * android.widget.AbsListView#onInterceptTouchEvent(android.view.MotionEvent
  * )
  */
    // @Override
    // public boolean onInterceptTouchEvent(MotionEvent ev) {
    // if (isGoTop && state == HEADER_MENU_STATE.menu_notshow) {
    // Log.i(TAG, "onInterceptTouchEvent(MotionEvent ev");
    // return super.onInterceptTouchEvent(ev);
    // }
    // return false;
    // }
}
