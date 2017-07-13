package com.example.administrator.myappgit.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myappgit.R;

/**
 * Created by Administrator on 2017/7/13 0013.
 * MainActivity Rv item的间距
 */

public class MainRvItemDecoration extends RecyclerView.ItemDecoration {

    private int divider;

    public MainRvItemDecoration(Context context) {
        divider = context.getResources().getDimensionPixelSize(R.dimen.main_activity_rv_item_divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divider;
//        outRect.bottom = divider;
        outRect.right = divider;
        outRect.left = divider;

        int position = parent.getChildAdapterPosition(view);
        if(position == state.getItemCount() - 1){
            outRect.bottom = divider;
        }
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(childAt);
//            if(position == (childCount-1)){
//                outRect.bottom = divider;
//            }
//        }

    }
}
