package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ComplexRvAdapter1;
import com.example.administrator.myappgit.bean.ComplexBean;
import com.example.administrator.myappgit.ui.FullyLinearLayoutManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplexGlidLayoutRv extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<ComplexBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_glid_rv);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        List<ComplexBean> datas = new ArrayList<>();

        ComplexBean complexBean1 = new ComplexBean();
        complexBean1.setTitle("西湖路雨量站");
        List<ComplexBean.DataBean> CD_datas1 = new ArrayList<>();

        ComplexBean.DataBean dataBean1 = new ComplexBean.DataBean();
        dataBean1.setTitle("实时雨情");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans1 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean1 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean1.setTitle("雨量");
        dataDataBean1.setValue("23mm/h");
        dataDataBeans1.add(dataDataBean1);
        dataBean1.setDatas(dataDataBeans1);

        ComplexBean.DataBean dataBean2 = new ComplexBean.DataBean();
        dataBean2.setTitle("实时水位");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans2 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean2 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean2.setTitle("水位");
        dataDataBean2.setValue("4.6m");
        dataDataBeans2.add(dataDataBean2);
        dataBean2.setDatas(dataDataBeans2);

        ComplexBean.DataBean dataBean3 = new ComplexBean.DataBean();
        dataBean3.setTitle("实时流速");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans3 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean3 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3.setTitle("流速");
        dataDataBean3.setValue("0.8m/s");
        dataDataBeans3.add(dataDataBean3);
        dataBean3.setDatas(dataDataBeans3);

        CD_datas1.add(dataBean1);
        CD_datas1.add(dataBean2);
        CD_datas1.add(dataBean3);


        ComplexBean complexBean2 = new ComplexBean();
        complexBean2.setTitle("南坑河水闸");
        List<ComplexBean.DataBean> CD_datas2 = new ArrayList<>();

        ComplexBean.DataBean dataBean2_1 = new ComplexBean.DataBean();
        dataBean2_1.setTitle("实时水位");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans2_1 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean2_1 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean2_1.setTitle("内水位");
        dataDataBean2_1.setValue("12.2m");
        ComplexBean.DataBean.DataDataBean dataDataBean2_1_2 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean2_1_2.setTitle("外水位");
        dataDataBean2_1_2.setValue("11.2m");
        dataDataBeans2_1.add(dataDataBean2_1);
        dataDataBeans2_1.add(dataDataBean2_1_2);
        dataBean2_1.setDatas(dataDataBeans2_1);

        ComplexBean.DataBean dataBean2_2 = new ComplexBean.DataBean();
        dataBean2_2.setTitle("管道流量");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans2_2 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean2_2 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean2_2.setTitle("流量");
        dataDataBean2_2.setValue("0.8m/s");
        dataDataBeans2_2.add(dataDataBean2_2);
        dataBean2_2.setDatas(dataDataBeans2_2);

        ComplexBean.DataBean dataBean2_3 = new ComplexBean.DataBean();
        dataBean2_3.setTitle("实时雨情");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans2_3 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean2_3 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean2_3.setTitle("雨量");
        dataDataBean2_3.setValue("154.6mm/h");
        dataDataBeans2_3.add(dataDataBean2_3);
        dataBean2_3.setDatas(dataDataBeans2_3);

        CD_datas2.add(dataBean2_1);
        CD_datas2.add(dataBean2_2);
        CD_datas2.add(dataBean2_3);


        ComplexBean complexBean3 = new ComplexBean();
        complexBean3.setTitle("南河水质");
        List<ComplexBean.DataBean> CD_datas3 = new ArrayList<>();

        ComplexBean.DataBean dataBean3_1 = new ComplexBean.DataBean();
        dataBean3_1.setTitle("水质");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans3_1 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean3_1 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3_1.setTitle("PH");
        dataDataBean3_1.setValue("7.6");
        ComplexBean.DataBean.DataDataBean dataDataBean3_1_2 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3_1_2.setTitle("浑浊度");
        dataDataBean3_1_2.setValue("5.6");
        ComplexBean.DataBean.DataDataBean dataDataBean3_1_3 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3_1_3.setTitle("污染度");
        dataDataBean3_1_3.setValue("2.6");
        dataDataBeans3_1.add(dataDataBean3_1);
        dataDataBeans3_1.add(dataDataBean3_1_2);
        dataDataBeans3_1.add(dataDataBean3_1_3);
        dataBean3_1.setDatas(dataDataBeans3_1);

        ComplexBean.DataBean dataBean3_2 = new ComplexBean.DataBean();
        dataBean3_2.setTitle("实时流速");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans3_2 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean3_2 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3_2.setTitle("流速");
        dataDataBean3_2.setValue("0.8m/s");
        dataDataBeans3_2.add(dataDataBean3_2);
        dataBean3_2.setDatas(dataDataBeans3_2);

        ComplexBean.DataBean dataBean3_3 = new ComplexBean.DataBean();
        dataBean3_3.setTitle("实时水位");
        List<ComplexBean.DataBean.DataDataBean> dataDataBeans3_3 = new ArrayList<>();
        ComplexBean.DataBean.DataDataBean dataDataBean3_3 = new ComplexBean.DataBean.DataDataBean();
        dataDataBean3_3.setTitle("水位");
        dataDataBean3_3.setValue("10.6m");
        dataDataBeans3_3.add(dataDataBean3_3);
        dataBean3_3.setDatas(dataDataBeans3_3);

        CD_datas3.add(dataBean3_1);
        CD_datas3.add(dataBean3_2);
        CD_datas3.add(dataBean3_3);


        complexBean1.setDatas(CD_datas1);
        complexBean2.setDatas(CD_datas2);
        complexBean3.setDatas(CD_datas3);
        datas.add(complexBean1);
        datas.add(complexBean2);
        datas.add(complexBean3);

        Gson gson = new Gson();
        String s = gson.toJson(datas);

        System.out.println(s);

        ComplexRvAdapter1 complexRvAdapter1 = new ComplexRvAdapter1(this, datas);
        mRv.setLayoutManager(new FullyLinearLayoutManager(this));
        mRv.setAdapter(complexRvAdapter1);
    }
}
