package com.example.administrator.myappgit.bean.DouBanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 * 豆瓣正在上映电影列表
 * api.douban.com/v2/movie/in_theaters
 */

public class InTheaterMovies {

    /**
     * count : 20
     * start : 0
     * total : 40
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":7.1,"stars":"35","min":0}
         * genres : ["喜剧","动作","科幻"]
         * title : 神偷奶爸3
         * casts : [{"alt":"https://movie.douban.com/celebrity/1054391/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/15731.jpg","large":"https://img3.doubanio.com/img/celebrity/large/15731.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/15731.jpg"},"name":"史蒂夫·卡瑞尔","id":"1054391"},{"alt":"https://movie.douban.com/celebrity/1022588/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/24543.jpg","large":"https://img3.doubanio.com/img/celebrity/large/24543.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/24543.jpg"},"name":"克里斯汀·韦格","id":"1022588"},{"alt":"https://movie.douban.com/celebrity/1027229/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/5253.jpg","large":"https://img3.doubanio.com/img/celebrity/large/5253.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/5253.jpg"},"name":"崔·帕克","id":"1027229"}]
         * collect_count : 37916
         * original_title : Despicable Me 3
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1313385/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/51602.jpg","large":"https://img3.doubanio.com/img/celebrity/large/51602.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/51602.jpg"},"name":"凯尔·巴尔达","id":"1313385"},{"alt":"https://movie.douban.com/celebrity/1313061/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1389806916.36.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1389806916.36.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1389806916.36.jpg"},"name":"皮艾尔·柯芬","id":"1313061"},{"alt":"https://movie.douban.com/celebrity/1365884/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1499651172.02.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499651172.02.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499651172.02.jpg"},"name":"埃里克·吉伦","id":"1365884"}]
         * year : 2017
         * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2469070974.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2469070974.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2469070974.jpg"}
         * alt : https://movie.douban.com/subject/25812712/
         * id : 25812712
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 7.1
             * stars : 35
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2469070974.jpg
             * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2469070974.jpg
             * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2469070974.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1054391/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/15731.jpg","large":"https://img3.doubanio.com/img/celebrity/large/15731.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/15731.jpg"}
             * name : 史蒂夫·卡瑞尔
             * id : 1054391
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/img/celebrity/small/15731.jpg
                 * large : https://img3.doubanio.com/img/celebrity/large/15731.jpg
                 * medium : https://img3.doubanio.com/img/celebrity/medium/15731.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1313385/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/51602.jpg","large":"https://img3.doubanio.com/img/celebrity/large/51602.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/51602.jpg"}
             * name : 凯尔·巴尔达
             * id : 1313385
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/img/celebrity/small/51602.jpg
                 * large : https://img3.doubanio.com/img/celebrity/large/51602.jpg
                 * medium : https://img3.doubanio.com/img/celebrity/medium/51602.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
