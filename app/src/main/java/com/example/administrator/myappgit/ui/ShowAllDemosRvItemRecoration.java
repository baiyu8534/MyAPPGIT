package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myappgit.R;

/**
 * 文件名：ShowAllDemosRvItemRecoration
 * 描述：item的周围边距
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class ShowAllDemosRvItemRecoration extends RecyclerView.ItemDecoration {

    private int divider;

    public ShowAllDemosRvItemRecoration(Context context) {
        this.divider = context.getResources().getDimensionPixelSize(R.dimen.main_activity_rv_item_divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int childPosition = parent.getChildAdapterPosition(view);
        if (childPosition % 2 == 0) {
            outRect.top = divider;
            outRect.left = divider;
            outRect.right = divider;
        } else if (childPosition % 2 == 1) {
            outRect.top = divider;
            outRect.right = divider;
        }
        if (state.getItemCount() % 2 == 0) {
            //双数，最后两个
            if (childPosition == state.getItemCount() - 2 || childPosition == state.getItemCount() - 1) {
                outRect.bottom = divider;
            }
        } else if (state.getItemCount() % 2 == 1) {
            //单数最后一个
            if (childPosition == state.getItemCount() - 1) {
                outRect.bottom = divider;
            }
        }
    }
}
