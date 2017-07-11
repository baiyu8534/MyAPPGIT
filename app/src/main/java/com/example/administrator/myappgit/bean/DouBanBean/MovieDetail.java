package com.example.administrator.myappgit.bean.DouBanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 * 电影详情
 * api.douban.com/v2/movie/subject/{id:26790194}
 */

public class MovieDetail {

    /**
     * rating : {"max":10,"average":0,"stars":"00","min":0}
     * reviews_count : 3
     * wish_count : 162
     * douban_site :
     * year : 2017
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2464044657.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2464044657.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2464044657.jpg"}
     * alt : https://movie.douban.com/subject/26790194/
     * id : 26790194
     * mobile_url : https://movie.douban.com/subject/26790194/mobile
     * title : 苏州少年横漂记
     * do_count : 11
     * share_url : https://m.douban.com/movie/subject/26790194
     * seasons_count : 11
     * schedule_url : https://movie.douban.com/subject/26790194/cinema/
     * episodes_count : 11
     * countries : ["中国大陆"]
     * genres : ["动作"]
     * collect_count : 62
     * casts : [{"alt":"https://movie.douban.com/celebrity/1376547/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1499400068.01.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499400068.01.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499400068.01.jpg"},"name":"黄梓乘","id":"1376547"},{"alt":"https://movie.douban.com/celebrity/1317162/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/38796.jpg","large":"https://img3.doubanio.com/img/celebrity/large/38796.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/38796.jpg"},"name":"石天龙","id":"1317162"},{"alt":"https://movie.douban.com/celebrity/1312939/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/14274.jpg","large":"https://img3.doubanio.com/img/celebrity/large/14274.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/14274.jpg"},"name":"黄海冰","id":"1312939"},{"alt":"https://movie.douban.com/celebrity/1301562/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/48228.jpg","large":"https://img1.doubanio.com/img/celebrity/large/48228.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/48228.jpg"},"name":"袁苑","id":"1301562"}]
     * current_season : 1
     * original_title : 苏州少年横漂记
     * summary : 电影《苏州少年横漂记》主要讲述的是一次家庭危机爆发后，苏展带着“外星熊猫”来到横店，梦想成为功夫巨星。但娇生惯养的他并不适应“横漂”生活，最终母亲决定让儿子报考横店影视职业学院表演艺术班，并答应了儿子的要求，与前夫复婚。迷途少年重返校园，离异家庭破镜重圆。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1353609/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","large":"https://img3.doubanio.com/img/celebrity/large/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg"},"name":"陈剑","id":"1353609"}]
     * comments_count : 36
     * ratings_count : 49
     * aka : ["Suzhou Youth Drift in Hengdian"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private int do_count;
    private String share_url;
    private int seasons_count;
    private String schedule_url;
    private int episodes_count;
    private int collect_count;
    private int current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDo_count() {
        return do_count;
    }

    public void setDo_count(int do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public int getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(int episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public int getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(int current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 0
         * stars : 00
         * min : 0
         */

        private int max;
        private int average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
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
         * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2464044657.jpg
         * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2464044657.jpg
         * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2464044657.jpg
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
         * alt : https://movie.douban.com/celebrity/1376547/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1499400068.01.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499400068.01.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499400068.01.jpg"}
         * name : 黄梓乘
         * id : 1376547
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
             * small : https://img3.doubanio.com/img/celebrity/small/1499400068.01.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1499400068.01.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1499400068.01.jpg
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
         * alt : https://movie.douban.com/celebrity/1353609/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","large":"https://img3.doubanio.com/img/celebrity/large/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg"}
         * name : 陈剑
         * id : 1353609
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
             * small : https://img3.doubanio.com/img/celebrity/small/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg
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
