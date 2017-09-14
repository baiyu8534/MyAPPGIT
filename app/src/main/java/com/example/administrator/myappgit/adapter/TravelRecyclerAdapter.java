package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myappgit.R;

/**
 * Created by baiyu on 2017/4/24.
 */
public class TravelRecyclerAdapter extends RecyclerView.Adapter<TravelRecyclerAdapter.ViewHolder> {


    public boolean isUp = true;
    private int[] images;

    private Context mContext;

    public TravelRecyclerAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent, false);

//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.height = layoutParams.height/2;
//        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.mImageView.setImageResource(images[position]);
        Glide.with(mContext).load(images[position]).into(holder.mImageView);
        holder.tv1.setVisibility(View.INVISIBLE);
        holder.tv2.setVisibility(View.INVISIBLE);
        holder.tv3.setVisibility(View.INVISIBLE);
        holder.tv4.setVisibility(View.INVISIBLE);
        holder.tv5.setVisibility(View.INVISIBLE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick((ImageView) v.findViewById(R.id.iv), images[position]);
            }
        });

//        System.out.println("调用了");
        // 判断是什么方向划出图片  提前滑动预定地点
        if (isUp)
            holder.mImageView.scrollTo(0, (-1 * 350));
        else
            holder.mImageView.scrollTo(0, 350);

    }


    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onClick(ImageView v, int resId);
    }

    public void setListener(onItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;

        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.iv);
            tv1 = (TextView) itemView.findViewById(R.id.tv);
            tv2 = (TextView) itemView.findViewById(R.id.tv_2);
            tv3 = (TextView) itemView.findViewById(R.id.tv_3);
            tv4 = (TextView) itemView.findViewById(R.id.tv_4);
            tv5 = (TextView) itemView.findViewById(R.id.tv_5);
        }

    }
}
