package com.example.administrator.myappgit.api;

import android.util.Log;

import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ApiManager {
    static final HttpLoggingInterceptor httpLoggingInterceptor;
    static {
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Response response = chain.proceed(chain.request());
            if (BuildConfig.DEBUG) Log.d("ApiManager", response.toString());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                //在线缓存2分钟内可读取
                int maxAge = 120;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private static ApiManager apiManager;
    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "MyAppCacheDir");
    //缓存10M
    private static long cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    /* private static OkHttpClient.Builder builder = new OkHttpClient.Builder()
             //超时
             .connectTimeout(1000, TimeUnit.SECONDS)
             .readTimeout(1000, TimeUnit.SECONDS)
             .writeTimeout(1000, TimeUnit.SECONDS)
             //错误重连
             .retryOnConnectionFailure(true)
             .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS));
             //cookie设置
             //.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance())));
 */
    private static OkHttpClient client = new OkHttpClient.Builder()
            //超时
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)
            //错误重连
            .retryOnConnectionFailure(true)
            .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            //日志
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build();

    //同步锁
    private Object lock = new Object();

    private ZhiHuApi zhihuApi;
    private DouBanApi doubanApi;
    private PixabayApi pixabayApi;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public ZhiHuApi getZhuHuApiService() {
        synchronized (lock) {
            if (zhihuApi == null) {
                synchronized (lock) {
                    if (zhihuApi == null) {
                        zhihuApi = new Retrofit.Builder()
                                .baseUrl("https://news-at.zhihu.com")
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(ZhiHuApi.class);
                    }
                }
            }
        }
        return zhihuApi;
    }

    public DouBanApi getDouBanApiService(){
        synchronized (lock){
            if(doubanApi == null){
                synchronized (lock){
                    if(doubanApi == null){
                        doubanApi = new Retrofit.Builder()
                                .baseUrl("https://api.douban.com")
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(DouBanApi.class);
                    }
                }
            }
        }
        return doubanApi;
    }

    public PixabayApi getPixabayApiService(){
        synchronized (lock){
            if(pixabayApi == null){
                synchronized (lock){
                    if(pixabayApi == null){
                        pixabayApi = new Retrofit.Builder()
                                .baseUrl("https://pixabay.com/api/")
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(PixabayApi.class);
                    }
                }
            }
        }
        return pixabayApi;
    }

}
