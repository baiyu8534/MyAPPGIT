package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class RvMainAdapter extends RecyclerView.Adapter<RvMainAdapter.MyViewHolder> {

    private Context mContext;

    private RvItemClickListener mListener;

    private List<PixabayListBean.HitsBean> hitsBeen = null;

    public void setRvItemClickListener(RvItemClickListener listener) {
        mListener = listener;
    }

    public interface RvItemClickListener {
        void onRvItemClickListener(int position);
    }

    public RvMainAdapter(Context context) {
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_title.setText(itemTitles[position]);
        int color = mContext.getResources().getColor(itemBG[position % 3]);
        holder.rl_item.setBackgroundColor(color);
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRvItemClickListener(position);
            }
        });

        if (hitsBeen != null) {
            Glide.with(mContext).load(hitsBeen.get(position+1).getWebformatURL()).into(holder.iv_item);
        }

    }

    @Override
    public int getItemCount() {
        return itemTitles.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public RelativeLayout rl_item;

        public ImageView iv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            rl_item = (RelativeLayout) itemView.findViewById(R.id.rl_item);
            iv_item = (ImageView) itemView.findViewById(R.id.iv_main_rv_item);
        }
    }
}
