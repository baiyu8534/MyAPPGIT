package com.example.administrator.myappgit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.ComplexBean;
import com.example.administrator.myappgit.ui.FullyGridLayoutManager;

import java.util.List;

public class ComplexRvAdapter1 extends RecyclerView.Adapter<ComplexRvAdapter1.ViewHolder> {

    private Context mContext;
    private List<ComplexBean> datas;

    public ComplexRvAdapter1(Context context, List<ComplexBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_complex_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_title.setText(datas.get(position).getTitle());
        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(mContext, 3);
        fullyGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position1) {
                ComplexBean complexBean = datas.get(position);
                List<ComplexBean.DataBean> datas = complexBean.getDatas();
                ComplexBean.DataBean dataBean = datas.get(position1);
                List<ComplexBean.DataBean.DataDataBean> datas1 = dataBean.getDatas();
                return datas1.size();
            }
        });
        holder.rv.setLayoutManager(fullyGridLayoutManager);
        ComplexRvAdapter2 complexRvAdapter2 = new ComplexRvAdapter2(mContext, datas.get(position).getDatas());
        holder.rv.setAdapter(complexRvAdapter2);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}
