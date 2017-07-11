package com.example.administrator.myappgit.bean.DouBanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 * 即将上映
 * /v2/movie/coming_soon
 */

public class ComingSoonMovies {

    /**
     * count : 20
     * start : 0
     * total : 119
     * title : 即将上映的电影
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
         * rating : {"max":10,"average":0,"stars":"00","min":0}
         * genres : ["动作"]
         * title : 苏州少年横漂记
         * casts : [{"alt":"https://movie.douban.com/celebrity/1376547/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1499400068.01.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499400068.01.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499400068.01.jpg"},"name":"黄梓乘","id":"1376547"},{"alt":"https://movie.douban.com/celebrity/1317162/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/38796.jpg","large":"https://img3.doubanio.com/img/celebrity/large/38796.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/38796.jpg"},"name":"石天龙","id":"1317162"},{"alt":"https://movie.douban.com/celebrity/1312939/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/14274.jpg","large":"https://img3.doubanio.com/img/celebrity/large/14274.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/14274.jpg"},"name":"黄海冰","id":"1312939"}]
         * collect_count : 62
         * original_title : 苏州少年横漂记
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1353609/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","large":"https://img3.doubanio.com/img/celebrity/large/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/FT1OEjmNvd4cel_avatar_uploaded1450687683.61.jpg"},"name":"陈剑","id":"1353609"}]
         * year : 2017
         * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2464044657.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2464044657.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2464044657.jpg"}
         * alt : https://movie.douban.com/subject/26790194/
         * id : 26790194
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
}
