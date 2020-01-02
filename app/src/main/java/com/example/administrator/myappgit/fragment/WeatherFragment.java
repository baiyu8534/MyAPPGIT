package com.example.administrator.myappgit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TestingRvAdapter;
import com.example.administrator.myappgit.ui.MainRvItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by baiyu on 2017/9/7.
 */

public class WeatherFragment extends BaseFragment {

    @BindView(R.id.rv)
    public RecyclerView mRv;
    Unbinder unbinder;
    @BindView(R.id.ll_base)
    LinearLayout mLlBase;
    private LinearLayoutManager mLinearLayoutManager;
    private TestingRvAdapter mRvTestAdapter;
    private RecyclerView.RecycledViewPool mPool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate:::"+this.getArguments().getInt("id"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_weather_layout, container, false);
        unbinder = ButterKnife.bind(this, v);
        System.out.println("onCreateView:::"+this.getArguments().getInt("id"));
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(mLinearLayoutManager);
        mRv.addItemDecoration(new MainRvItemDecoration(getContext()));
        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0, 16);
        mRvTestAdapter = new TestingRvAdapter(getContext());
        mRv.setAdapter(mRvTestAdapter);
        System.out.println("onViewCreated:::"+this.getArguments().getInt("id"));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)//展示
        {
            System.out.println("setUserVisibleHint:::"+this.getArguments().getInt("id"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume:::"+this.getArguments().getInt("id"));
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart:::"+this.getArguments().getInt("id"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
