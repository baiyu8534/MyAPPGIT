package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myappgit.R;

/**
 * Created by Administrator on 2017/7/24 0024.
 * 横线分割
 */

public class ShowRvItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private int dividerLeft;
    private Paint dividerPaint;

    public ShowRvItemDecoration(Context context) {
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.show_rv_decoration);
        dividerLeft = context.getResources().getDimensionPixelSize(R.dimen.main_activity_rv_item_divider);
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.colorSecondaryText));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft() + dividerLeft;
        int right = parent.getWidth() - parent.getPaddingRight() - dividerLeft;
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }
}
