package com.example.administrator.myappgit.IView;

import java.util.ArrayList;

/**
 * Created by baiyu on 2017/9/16.
 */

public interface IItemBGRollRvActivity extends IBaseView {

    /**
     * 更新图片显示
     *
     * @param images
     */
    void upDataImages(ArrayList<String> images);

    /**
     * 显示错误信息
     *
     * @param message
     */
    void showErrorMessage(String message);

    /**
     * 控制下拉刷新的显示
     *
     * @param refreshing
     */
    void setRefreshing(boolean refreshing);
}
