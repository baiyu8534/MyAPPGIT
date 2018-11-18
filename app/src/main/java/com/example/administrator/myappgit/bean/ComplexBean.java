package com.example.administrator.myappgit.bean;

import java.util.List;

public class ComplexBean {

    private String title;

    private List<DataBean> datas;

    public static class DataBean {
        private String title;
        private List<DataDataBean> datas;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDatas(List<DataDataBean> datas) {
            this.datas = datas;
        }

        public String getTitle() {
            return title;
        }

        public List<DataDataBean> getDatas() {
            return datas;
        }

        public static class DataDataBean {
            private String title;
            private String value;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getTitle() {
                return title;
            }

            public String getValue() {
                return value;
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDatas(List<DataBean> datas) {
        this.datas = datas;
    }

    public String getTitle() {
        return title;
    }

    public List<DataBean> getDatas() {
        return datas;
    }
}
