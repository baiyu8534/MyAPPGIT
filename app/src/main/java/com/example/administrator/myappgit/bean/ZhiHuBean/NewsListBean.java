package com.example.administrator.myappgit.bean.ZhiHuBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 * 最新消息列表
 * https://news-at.zhihu.com/api/4/news/latest
 */

public class NewsListBean {

    /**
     * date : 20170711
     * stories : [{"title":"为什么是小黄人，不是小绿人、小蓝人、小红人？","ga_prefix":"071110","images":["https://pic2.zhimg.com/v2-6ed7ddccd684771bb45776468a2041dd.jpg"],"multipic":true,"type":0,"id":9515770},{"images":["https://pic1.zhimg.com/v2-5ddbca5041d42ce0b5c4dc3ebf40008c.jpg"],"type":0,"id":9517249,"ga_prefix":"071109","title":"百度和 Google 都用这个牌子的车做无人驾驶测试，为什么？"},{"images":["https://pic4.zhimg.com/v2-4a072f57b890d283351d3bbe0d9fee47.jpg"],"type":0,"id":9518015,"ga_prefix":"071108","title":"知道哪些法律上的小常识可以保护自己？"},{"title":"你熟悉的那些动画明星，一开始都是些流氓疯子","ga_prefix":"071107","images":["https://pic1.zhimg.com/v2-4438e11882651c596c18c1eef75e4f10.jpg"],"multipic":true,"type":0,"id":9513233},{"images":["https://pic3.zhimg.com/v2-cf8bd4abb62af354f07ce6ae62f23916.jpg"],"type":0,"id":9517017,"ga_prefix":"071107","title":"当今中国，为什么火电仍能占据约一半的发电比例？"},{"images":["https://pic4.zhimg.com/v2-791fd11934ade832a87073a56b2bf533.jpg"],"type":0,"id":9517727,"ga_prefix":"071107","title":"有一种银行，是专门服务有钱人的「私人银行」"},{"images":["https://pic3.zhimg.com/v2-6e1eea2ce5b972fb9934047de679bdca.jpg"],"type":0,"id":9513030,"ga_prefix":"071106","title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-1f82410598b4f5d25c1876f88c54b087.jpg"],"type":0,"id":9517585,"ga_prefix":"071106","title":"这里是广告 · 如何缓解追更的痛苦？"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-a19052af0daf75c12b045311cc4a93c9.jpg","type":0,"id":9517727,"ga_prefix":"071107","title":"有一种银行，是专门服务有钱人的「私人银行」"},{"image":"https://pic2.zhimg.com/v2-be7b49d01cbfbdf78caee9d5a86b8e25.jpg","type":0,"id":9515770,"ga_prefix":"071110","title":"为什么是小黄人，不是小绿人、小蓝人、小红人？"},{"image":"https://pic2.zhimg.com/v2-9d79a8368b9756e70609351c3476c00d.jpg","type":0,"id":9517017,"ga_prefix":"071107","title":"当今中国，为什么火电仍能占据约一半的发电比例？"},{"image":"https://pic1.zhimg.com/v2-c26df9905dd484f0bc846ce64c061d1c.jpg","type":0,"id":9516813,"ga_prefix":"071019","title":"美感 10 分，难度系数 9.99 分，有生之年怕是看不到了"},{"image":"https://pic2.zhimg.com/v2-17549a1f11c4d1a5825131e7a9d8375d.jpg","type":0,"id":9516855,"ga_prefix":"071015","title":"终于又见中国队战胜韩国队，恭喜 LPL，这个冠军我们等得太久了"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 为什么是小黄人，不是小绿人、小蓝人、小红人？
         * ga_prefix : 071110
         * images : ["https://pic2.zhimg.com/v2-6ed7ddccd684771bb45776468a2041dd.jpg"]
         * multipic : true
         * type : 0
         * id : 9515770
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-a19052af0daf75c12b045311cc4a93c9.jpg
         * type : 0
         * id : 9517727
         * ga_prefix : 071107
         * title : 有一种银行，是专门服务有钱人的「私人银行」
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
