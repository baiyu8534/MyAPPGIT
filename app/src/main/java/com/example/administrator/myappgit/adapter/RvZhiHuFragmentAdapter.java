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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_title.setText(mStoriesBeanList.get(position).getTitle());

        Glide.with(mContext).load(mStoriesBeanList.get(position).getImages().get(0)).into(holder.iv);

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
