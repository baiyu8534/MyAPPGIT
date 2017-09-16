package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myappgit.R;

import java.util.ArrayList;

/**
 * Created by baiyu on 2017/4/24.
 */
public class TravelRecyclerAdapter extends RecyclerView.Adapter<TravelRecyclerAdapter.ViewHolder> {


    public boolean isUp = true;
    private ArrayList<String> images;

    private Context mContext;

    public TravelRecyclerAdapter(ArrayList<String> images, Context context) {
        this.images = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bg_roll_rv_layout, parent, false);

        //设置itemVIew高度
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.height = layoutParams.height/2;
//        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(mContext).load(images.get(position))
                .apply(new RequestOptions()
                        .error(R.drawable.guide_3_s)
                        .placeholder(R.drawable.guide_3_s)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .centerCrop())
                //加载缩略图，缩略图先加载完就显示，否则不显示
                .thumbnail(0.2f)
                .into(holder.iv_item);

//        System.out.println("调用了");
        // 判断是什么方向划出图片  提前滑动预定地点
        if (isUp)
            holder.iv_item.scrollTo(0, (-1 * 350));
        else
            holder.iv_item.scrollTo(0, 350);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_item = (ImageView) itemView.findViewById(R.id.iv_item);
        }

    }
}
