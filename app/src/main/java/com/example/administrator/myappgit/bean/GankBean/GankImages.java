package com.example.administrator.myappgit.bean.GankBean;

import java.util.List;

/**
 * 文件名：GankImages
 * 描述：Gank图片获取json对应bean
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class GankImages {

    /**
     * error : false
     * results : [{"_id":"59b720df421aa9118887ac18","createdAt":"2017-09-12T07:48:47.73Z","desc":"9-12",
     * "publishedAt":"2017-09-14T16:36:51.63Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg
     * .cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg","used":true,"who":"daimajia"},{"_id":"59b5cfb5421aa9118887ac0b",
     * "createdAt":"2017-09-11T07:50:13.510Z","desc":"9-11","publishedAt":"2017-09-12T08:15:08.26Z","source":"chrome",
     * "type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg","used":true,"who":"代码家"},
     * {"_id":"59b0d757421aa901bcb7dc0c","createdAt":"2017-09-07T13:21:27.937Z","desc":"9-7",
     * "publishedAt":"2017-09-07T13:25:26.977Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg
     * .cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg","used":true,"who":"daimajia"},{"_id":"599f7362421aa901c85e5fc2",
     * "createdAt":"2017-08-25T08:46:26.461Z","desc":"8-25","publishedAt":"2017-09-06T12:18:11.687Z","source":"chrome",
     * "type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg","used":true,"who":"daimajia"},
     * {"_id":"59aca203421aa901c1c0a8d8","createdAt":"2017-09-04T08:44:51.44Z","desc":"09-04",
     * "publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg
     * .cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg","used":true,"who":"dmj"},{"_id":"59a8cfdc421aa901c1c0a8c7",
     * "createdAt":"2017-09-01T11:11:24.81Z","desc":"9-1","publishedAt":"2017-09-01T12:55:52.582Z","source":"chrome",
     * "type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg","used":true,"who":"daimajia"},
     * {"_id":"59a755a2421aa901c85e5fea","createdAt":"2017-08-31T08:17:38.117Z","desc":"8-31",
     * "publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg
     * .cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg","used":true,"who":"daimajia"},{"_id":"59a35f6d421aa901b9dc4643",
     * "createdAt":"2017-08-28T08:10:21.141Z","desc":"8-28","publishedAt":"2017-08-29T12:19:18.634Z","source":"chrome",
     * "type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg","used":true,"who":"代码家"},
     * {"_id":"599e2220421aa901b9dc462d","createdAt":"2017-08-24T08:47:28.949Z","desc":"8-24",
     * "publishedAt":"2017-08-24T12:43:10.124Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg
     * .cn/large/610dc034ly1fiuiw5givwj20u011h79a.jpg","used":true,"who":"daimajia"},{"_id":"599ccace421aa901c85e5fb8",
     * "createdAt":"2017-08-23T08:22:38.611Z","desc":"8-23","publishedAt":"2017-08-23T12:12:15.166Z","source":"chrome",
     * "type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59b720df421aa9118887ac18
         * createdAt : 2017-09-12T07:48:47.73Z
         * desc : 9-12
         * publishedAt : 2017-09-14T16:36:51.63Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
