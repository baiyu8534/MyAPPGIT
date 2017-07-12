package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.DouBanBean.ComingSoonMovies;
import com.example.administrator.myappgit.bean.DouBanBean.InTheaterMovies;
import com.example.administrator.myappgit.bean.DouBanBean.MovieDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public interface DouBanApi {

    @GET("/v2/movie/coming_soon")
    Observable<ComingSoonMovies> getComingSoonMoviesList();

    @GET("/v2/movie/in_theaters")
    Observable<InTheaterMovies> getInTheaterMoviesList();

    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetail> getMovieDetail(@Path("id") String id);


}
