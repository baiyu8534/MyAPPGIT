package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myappgit.R;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class RvMainAdapter extends RecyclerView.Adapter<RvMainAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;

    public RvMainAdapter(Context context) {
        mContext = context;
    }

    private int[] itemTitles = {
            R.string.rv_main_item_1,
            R.string.rv_main_item_2,
            R.string.rv_main_item_3,
            R.string.rv_main_item_4,
            R.string.rv_main_item_5,
    };

    private int[] itemBG = {
            R.color.mainRvItemBg1,
            R.color.mainRvItemBg2,
            R.color.mainRvItemBg3
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.main_rv_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(itemTitles[position]);
        int color = mContext.getResources().getColor(itemBG[position % 3]);
        holder.tv_title.setBackgroundColor(color);

        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return itemTitles.length;
    }

    @Override
    public void onClick(View v) {

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}
