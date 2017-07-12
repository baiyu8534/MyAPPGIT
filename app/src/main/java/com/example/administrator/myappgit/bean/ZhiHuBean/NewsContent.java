package com.example.administrator.myappgit.bean.ZhiHuBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 * https://news-at.zhihu.com/api/4/news/3892357
 */

public class NewsContent {

    /**
     * body : html源码 用webview 加载
     * image_source : Yestone.com 版权图片库
     * title : 净利润只有 29 亿的企业拿出 1000 亿去囤地，这是怎样的精神？
     * image : https://pic3.zhimg.com/v2-8744a7bad1caa2815009e9a9dd45d2f2.jpg
     * share_url : http://daily.zhihu.com/story/9519246
     * js : []
     * ga_prefix : 071210
     * images : ["https://pic1.zhimg.com/v2-429db57db0996d2b53ee2a5ab027b068.jpg"]
     * type : 0
     * id : 9519246
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
