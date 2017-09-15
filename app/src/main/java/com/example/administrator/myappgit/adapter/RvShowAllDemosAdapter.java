package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myappgit.R;

import java.util.List;

/**
 * 文件名：
 * 描述：
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class RvShowAllDemosAdapter extends RecyclerView.Adapter<RvShowAllDemosAdapter.RvShowAllDemosAdapterViewHolder> {

    private Context mContext;
    private List<RvShowAllDemosAdapterItemBean> datas;

    public RvShowAllDemosAdapter(Context context, List<RvShowAllDemosAdapterItemBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public RvShowAllDemosAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_show_all_demos_layout, viewGroup, false);
        return new RvShowAllDemosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RvShowAllDemosAdapterViewHolder viewHolder, int position) {

        viewHolder.tv_item_title.setText(datas.get(position).getItemTitle());
//        Glide.with(mContext).load(datas.get(position).getItemImageUrl())
//                .apply(new RequestOptions()
//                .error(R.drawable))

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RvShowAllDemosAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_item_title;
        public ImageView iv_item_image;

        public RvShowAllDemosAdapterViewHolder(View itemView) {
            super(itemView);
            tv_item_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            iv_item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);
        }
    }

    public class RvShowAllDemosAdapterItemBean {
        private String itemTitle;
        private String itemImageUrl;

        public String getItemTitle() {
            return itemTitle;
        }

        public String getItemImageUrl() {
            return itemImageUrl;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public void setItemImageUrl(String itemImageUrl) {
            this.itemImageUrl = itemImageUrl;
        }
    }
}
