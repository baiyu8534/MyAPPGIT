package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.ComplexBean;

import java.util.List;

public class ComplexRvAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ComplexBean.DataBean> mDataBeans;

    public static enum ITEM_TYPE {
        ITEM_TYPE_1,
        ITEM_TYPE_2,
        ITEM_TYPE_3
    }

    public ComplexRvAdapter2(Context context, List<ComplexBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_1.ordinal()) {
            return new ViewHolder1(LayoutInflater.from(mContext).inflate(R.layout.item_complex_2_1, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_2.ordinal()) {
            return new ViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.item_complex_2_2, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_3.ordinal()) {
            return new ViewHolder3(LayoutInflater.from(mContext).inflate(R.layout.item_complex_2_3, parent, false));
        } else {
            return new ViewHolder1(LayoutInflater.from(mContext).inflate(R.layout.item_complex_2_1, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).tv_title.setText(mDataBeans.get(position).getTitle());
            ((ViewHolder1) holder).tv_title_1.setText(mDataBeans.get(position).getDatas().get(0).getTitle());
            ((ViewHolder1) holder).tv_value_1.setText(mDataBeans.get(position).getDatas().get(0).getValue());
        } else if (holder instanceof ViewHolder2) {
            ((ViewHolder2) holder).tv_title.setText(mDataBeans.get(position).getTitle());
            ((ViewHolder2) holder).tv_title_1.setText(mDataBeans.get(position).getDatas().get(0).getTitle());
            ((ViewHolder2) holder).tv_value_1.setText(mDataBeans.get(position).getDatas().get(0).getValue());
            ((ViewHolder2) holder).tv_title_2.setText(mDataBeans.get(position).getDatas().get(1).getTitle());
            ((ViewHolder2) holder).tv_value_2.setText(mDataBeans.get(position).getDatas().get(1).getValue());
        } else if (holder instanceof ViewHolder3) {
            ((ViewHolder3) holder).tv_title.setText(mDataBeans.get(position).getTitle());
            ((ViewHolder3) holder).tv_title_1.setText(mDataBeans.get(position).getDatas().get(0).getTitle());
            ((ViewHolder3) holder).tv_value_1.setText(mDataBeans.get(position).getDatas().get(0).getValue());
            ((ViewHolder3) holder).tv_title_2.setText(mDataBeans.get(position).getDatas().get(1).getTitle());
            ((ViewHolder3) holder).tv_value_2.setText(mDataBeans.get(position).getDatas().get(1).getValue());
            ((ViewHolder3) holder).tv_title_3.setText(mDataBeans.get(position).getDatas().get(2).getTitle());
            ((ViewHolder3) holder).tv_value_3.setText(mDataBeans.get(position).getDatas().get(2).getValue());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int size = mDataBeans.get(position).getDatas().size();
        if (size == 1) {
            return ITEM_TYPE.ITEM_TYPE_1.ordinal();
        } else if (size == 2) {
            return ITEM_TYPE.ITEM_TYPE_2.ordinal();
        } else if (size == 3) {
            return ITEM_TYPE.ITEM_TYPE_3.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_1.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_title_1;
        public TextView tv_value_1;

        public ViewHolder1(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title_1 = itemView.findViewById(R.id.tv_title_1);
            tv_value_1 = itemView.findViewById(R.id.tv_value_1);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_title_1;
        public TextView tv_value_1;
        public TextView tv_title_2;
        public TextView tv_value_2;

        public ViewHolder2(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title_1 = itemView.findViewById(R.id.tv_title_1);
            tv_value_1 = itemView.findViewById(R.id.tv_value_1);
            tv_title_2 = itemView.findViewById(R.id.tv_title_2);
            tv_value_2 = itemView.findViewById(R.id.tv_value_2);
        }
    }

    public static class ViewHolder3 extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_title_1;
        public TextView tv_value_1;
        public TextView tv_title_2;
        public TextView tv_value_2;
        public TextView tv_title_3;
        public TextView tv_value_3;

        public ViewHolder3(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title_1 = itemView.findViewById(R.id.tv_title_1);
            tv_value_1 = itemView.findViewById(R.id.tv_value_1);
            tv_title_2 = itemView.findViewById(R.id.tv_title_2);
            tv_value_2 = itemView.findViewById(R.id.tv_value_2);
            tv_title_3 = itemView.findViewById(R.id.tv_title_3);
            tv_value_3 = itemView.findViewById(R.id.tv_value_3);
        }
    }
}
