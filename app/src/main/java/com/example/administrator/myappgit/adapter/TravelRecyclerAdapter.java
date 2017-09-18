package com.example.administrator.myappgit.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.activity.ItemBGRollRvActivity;
import com.example.administrator.myappgit.activity.ShowImageActivity;
import com.example.administrator.myappgit.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext).load(images.get(position))
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        //重新设置图片的宽高
                        .override(ScreenUtil.getScreenWidth(mContext), ScreenUtil.getScreenHeight(mContext))
                        .centerCrop()
                )
                .into(holder.iv_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowImageActivity.class);
                intent.putExtra("imageUrl", images.get(position));
                List<Pair<View, String>> pairs = new ArrayList<>();
                pairs.add(Pair.create((View) holder.iv_item, "iv_item"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((ItemBGRollRvActivity) mContext, pairs.toArray
                            (new Pair[]{}))
                            .toBundle();
                    mContext.startActivity(intent, bundle);
                } else {
                    mContext.startActivity(intent);
                }
            }
        });

        // 判断是什么方向划出图片  提前滑动预定地点
        //ScreenUtil.getScreenHeight(mContext) / 5 是滚动的最大距离
        if (isUp)
            holder.iv_item.scrollTo(0, (-1 * ScreenUtil.getScreenHeight(mContext) / 5));
        else
            holder.iv_item.scrollTo(0, ScreenUtil.getScreenHeight(mContext) / 5);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_item = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}
