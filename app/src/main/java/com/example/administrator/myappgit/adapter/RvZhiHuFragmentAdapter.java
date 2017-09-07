package com.example.administrator.myappgit.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.interfaze.LoadMore;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class RvZhiHuFragmentAdapter extends RecyclerView.Adapter<RvZhiHuFragmentAdapter.MyViewHolder> implements LoadMore {

    private Context mContext;

    private List<NewsListBean.StoriesBean> mStoriesBeanList;

    public RvZhiHuFragmentAdapter(Context context, List<NewsListBean.StoriesBean> storiesBeanList) {
        mContext = context;
        mStoriesBeanList = storiesBeanList;
    }

    @Override
    public RvZhiHuFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihu_list_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final NewsListBean.StoriesBean storiesBean = mStoriesBeanList.get(position);

        holder.tv_title.setText(mStoriesBeanList.get(position).getTitle());
        // FIXME: 2017/9/6 加阅读后字体变灰，要用的数据库
        Glide.with(mContext)
                .load(mStoriesBeanList.get(position).getImages().get(0))
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.hander)
                        .error(R.drawable.hander)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.iv.setHasTransientState(true);
                        }
                        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
                        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f);
                        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f);

                        final ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(holder.iv, alpha, scaleX, scaleY);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    holder.iv.setHasTransientState(false);
                                }
                            }
                        });
                        animator.start();
                        animator.setDuration(300);
                        animator.setInterpolator(new AccelerateInterpolator());
                        return false;
                    }
                })
                .into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoZhiHuDetail();
            }
        });
    }

    private void gotoZhiHuDetail() {

    }

    @Override
    public int getItemCount() {
        return mStoriesBeanList.size();
    }

    @Override
    public void loadingStart() {

    }

    @Override
    public void loadingFinish() {

    }

    public void addItems(List<NewsListBean.StoriesBean> list) {
        int oldSize = mStoriesBeanList.size();
        mStoriesBeanList.addAll(list);
        notifyItemRangeChanged(oldSize - 1, mStoriesBeanList.size() - 1);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;

        public ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_zhihu_item_title);
            iv = (ImageView) itemView.findViewById(R.id.iv_zhihu_item);
        }
    }
}
