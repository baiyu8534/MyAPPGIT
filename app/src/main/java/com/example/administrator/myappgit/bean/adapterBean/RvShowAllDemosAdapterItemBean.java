package com.example.administrator.myappgit.bean.adapterBean;

/**
 * Created by baiyu on 2017/9/15.
 */

public class RvShowAllDemosAdapterItemBean {
    private String itemTitle;
    private String itemImageUrl;
    private Class mActivityClass;

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

    public Class getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class activityClass) {
        mActivityClass = activityClass;
    }
}
