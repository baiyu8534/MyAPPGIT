package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class RvTransformIndicatorAdapter extends RecyclerView.Adapter<RvTransformIndicatorAdapter.MyViewHolder> {

    private Context mContext;

    private RvItemClickListener mListener;

    private List<PixabayListBean.HitsBean> hitsBeen = null;

    public void setRvItemClickListener(RvItemClickListener listener) {
        mListener = listener;
    }

    public interface RvItemClickListener {
        void onRvItemClickListener(int position);
    }

    public RvTransformIndicatorAdapter(Context context) {
        mContext = context;
    }

    public void setHitsBeen(List<PixabayListBean.HitsBean> hitsBeen) {
        this.hitsBeen = hitsBeen;
    }

    private int[] itemTitles = {
            R.string.rv_main_item_1,
            R.string.rv_main_item_2,
//            R.string.rv_main_item_3,
            R.string.rv_main_item_4,
            R.string.rv_main_item_5,
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_title.setText(itemTitles[position]);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRvItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemTitles.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
