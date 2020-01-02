package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class TestingRvAdapter extends RecyclerView.Adapter<TestingRvAdapter.MyViewHolder> {

    private Context mContext;

    private RvItemClickListener mListener;

    private List<PixabayListBean.HitsBean> hitsBeen = null;

    public void setRvItemClickListener(RvItemClickListener listener) {
        mListener = listener;
    }

    public interface RvItemClickListener {
        void onRvItemClickListener(int position);
    }

    public TestingRvAdapter(Context context) {
        mContext = context;
        initDate();

    }

    private void initDate() {
        for (int i = 0; i < 20; i++) {
            itemTitles.add("标题：：：："+i);
        }
    }


    private List<String> itemTitles = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_title.setText(itemTitles.get(position));
        holder.rl_item.setBackgroundResource(R.drawable.day);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRvItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemTitles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public CardView cv;
        public RelativeLayout rl_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            cv = (CardView) itemView.findViewById(R.id.cv);
            rl_item = itemView.findViewById(R.id.rl_item);
        }
    }
}
