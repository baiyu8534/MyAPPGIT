package com.example.administrator.myappgit.bean.PixadayBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class PixabayListBean {

    private int totalHits;
    private int total;
    private List<HitsBean> hits;

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HitsBean> getHits() {
        return hits;
    }

    public void setHits(List<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean {
        /**
         * previewHeight : 99
         * likes : 1
         * favorites : 0
         * tags : landscape, clouds, river
         * webformatHeight : 426
         * views : 20
         * webformatWidth : 640
         * previewWidth : 150
         * comments : 0
         * downloads : 2
         * pageURL : https://pixabay.com/en/landscape-clouds-river-scenery-2514495/
         * previewURL : https://cdn.pixabay.com/photo/2017/07/18/01/38/landscape-2514495_150.jpg
         * webformatURL : https://pixabay.com/get/eb30b00b2cfd043ed95c4518b7494f94e47fe5dd04b0144195f3c470a4e8bc_640.jpg
         * imageWidth : 6000
         * user_id : 4356484
         * user : alistairmcintyre
         * type : photo
         * id : 2514495
         * userImageURL :
         * imageHeight : 4000
         */

        private int previewHeight;
        private int likes;
        private int favorites;
        private String tags;
        private int webformatHeight;
        private int views;
        private int webformatWidth;
        private int previewWidth;
        private int comments;
        private int downloads;
        private String pageURL;
        private String previewURL;
        private String webformatURL;
        private int imageWidth;
        private int user_id;
        private String user;
        private String type;
        private int id;
        private String userImageURL;
        private int imageHeight;

        public int getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(int previewHeight) {
            this.previewHeight = previewHeight;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(int webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(int webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public int getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(int previewWidth) {
            this.previewWidth = previewWidth;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        @Override
        public String toString() {
            return "HitsBean{" +
                    "previewHeight=" + previewHeight +
                    ", likes=" + likes +
                    ", favorites=" + favorites +
                    ", tags='" + tags + '\'' +
                    ", webformatHeight=" + webformatHeight +
                    ", views=" + views +
                    ", webformatWidth=" + webformatWidth +
                    ", previewWidth=" + previewWidth +
                    ", comments=" + comments +
                    ", downloads=" + downloads +
                    ", pageURL='" + pageURL + '\'' +
                    ", previewURL='" + previewURL + '\'' +
                    ", webformatURL='" + webformatURL + '\'' +
                    ", imageWidth=" + imageWidth +
                    ", user_id=" + user_id +
                    ", user='" + user + '\'' +
                    ", type='" + type + '\'' +
                    ", id=" + id +
                    ", userImageURL='" + userImageURL + '\'' +
                    ", imageHeight=" + imageHeight +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PixabayListBean{" +
                "totalHits=" + totalHits +
                ", total=" + total +
                ", hits=" + hits +
                '}';
    }
}
