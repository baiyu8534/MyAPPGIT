package com.example.administrator.myappgit.api;

import android.util.Log;

import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
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
    //缓存50M
    private static long cacheSize = 50 * 1024 * 1024;
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
            // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里15个，和每个保持时间为10s
            .connectionPool(new ConnectionPool(15, 10, TimeUnit.SECONDS))
            .cache(cache)
            .build();
    private static OkHttpClient pixClient = null;

    //同步锁
    private Object lock = new Object();

    private ZhiHuApi zhihuApi;
    private DouBanApi doubanApi;
    private PixabayApi pixabayApi;
    private GankApi gankApi;

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

    public DouBanApi getDouBanApiService() {
        synchronized (lock) {
            if (doubanApi == null) {
                synchronized (lock) {
                    if (doubanApi == null) {
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

    public GankApi getGankApiService() {
        if (gankApi == null) {
            synchronized (lock) {
                if (gankApi == null) {
                    gankApi = new Retrofit.Builder()
                            .baseUrl("http://gank.io")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(GankApi.class);
                }
            }
        }
        return gankApi;
    }

    public PixabayApi getPixabayApiService() {
        synchronized (lock) {
            if (pixabayApi == null) {
                synchronized (lock) {
                    if (pixabayApi == null) {
                        pixabayApi = new Retrofit.Builder()
                                .baseUrl("https://pixabay.com/api/")
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(getOkHttpClinet())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(PixabayApi.class);
                    }
                }
            }
        }
        return pixabayApi;
    }

    private String pixabay_crt = "-----BEGIN CERTIFICATE-----\n" +
            "MIIE+TCCA+GgAwIBAgISA46KN9w0DwuvxJs/MQe57g2pMA0GCSqGSIb3DQEBCwUA\n" +
            "MEoxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1MZXQncyBFbmNyeXB0MSMwIQYDVQQD\n" +
            "ExpMZXQncyBFbmNyeXB0IEF1dGhvcml0eSBYMzAeFw0xNzA3MDUxNjA0MDBaFw0x\n" +
            "NzEwMDMxNjA0MDBaMBYxFDASBgNVBAMTC3BpeGFiYXkuY29tMIIBIjANBgkqhkiG\n" +
            "9w0BAQEFAAOCAQ8AMIIBCgKCAQEArrU8+LJjtw7hO7Q9i2vPPnrLdXFaLC27IQBV\n" +
            "B7HjMoW/U0SDciuazGbY1AtcKMQu0r0YiIxFkp9FbAMmcF3HOTANPEVSr1XbZZWN\n" +
            "9NHjaoDYejJxXdui1yVDPwzjctMpjVKCvQtHN1XuDL4gx8PFFvsUT/9JSQuEbtqp\n" +
            "MTeMyBWhAe8BRU+Lv27JaFUNKI+ZM7B71x5HdrTFEtaCM5ENjEo1RCb+x5bJJ9Lw\n" +
            "VNMS06qhhOBjOr4DtqACqZbKmb3PkN3T69TNiL1CYhludfNqwBl2Wko3hPC+bCoq\n" +
            "nKYe6xD5JrDbRNgTmLOSjTCsCQ+nTe7dab2r21zXq2Hw4Z+ZlQIDAQABo4ICCzCC\n" +
            "AgcwDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcD\n" +
            "AjAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBT/GiKF391WoXWFSeWvi3KGJI/akTAf\n" +
            "BgNVHSMEGDAWgBSoSmpjBH3duubRObemRWXv86jsoTBvBggrBgEFBQcBAQRjMGEw\n" +
            "LgYIKwYBBQUHMAGGImh0dHA6Ly9vY3NwLmludC14My5sZXRzZW5jcnlwdC5vcmcw\n" +
            "LwYIKwYBBQUHMAKGI2h0dHA6Ly9jZXJ0LmludC14My5sZXRzZW5jcnlwdC5vcmcv\n" +
            "MBYGA1UdEQQPMA2CC3BpeGFiYXkuY29tMIH+BgNVHSAEgfYwgfMwCAYGZ4EMAQIB\n" +
            "MIHmBgsrBgEEAYLfEwEBATCB1jAmBggrBgEFBQcCARYaaHR0cDovL2Nwcy5sZXRz\n" +
            "ZW5jcnlwdC5vcmcwgasGCCsGAQUFBwICMIGeDIGbVGhpcyBDZXJ0aWZpY2F0ZSBt\n" +
            "YXkgb25seSBiZSByZWxpZWQgdXBvbiBieSBSZWx5aW5nIFBhcnRpZXMgYW5kIG9u\n" +
            "bHkgaW4gYWNjb3JkYW5jZSB3aXRoIHRoZSBDZXJ0aWZpY2F0ZSBQb2xpY3kgZm91\n" +
            "bmQgYXQgaHR0cHM6Ly9sZXRzZW5jcnlwdC5vcmcvcmVwb3NpdG9yeS8wDQYJKoZI\n" +
            "hvcNAQELBQADggEBAHNC1L8QfDtN55z3/wo27kL3PjectBP7PD3ymYT72rVSD3oG\n" +
            "4vx445kARYwZu9KJv+8z0CGqISL4kypJ2hlW5biJf943RCSjjJbg5O36TanIp8+I\n" +
            "59rgg1YQXGrjDysGZfmgZWjN/ret2qcUKqUyAmEqbOycN9GZQo82v7LnyWTqZL5z\n" +
            "1GKGtLYcfajGfBhuv9jS6zfxGb8UbzadLZ7FoEco01OO6SSuHCJtNtW4qEJdWF79\n" +
            "EQgGizMTd+pPtSyIPHyOUuuuNdfbtn3YsRnSL4AChLUKgGrkRfC1tC+vOngz4uRO\n" +
            "ZEwySXKsR/0L8PvxLWYVGm9BtD6HTQ4E79ct7Qk=\n" +
            "-----END CERTIFICATE-----\n";

    /**
     * 初始化HTTPS,添加信任证书
     */
    public OkHttpClient getOkHttpClinet() {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        final InputStream inputStream;
        try {
            inputStream = MyApplication.getContext().getAssets().open("aaa.cer"); // 得到证书的输入流
//            inputStream = new Buffer()
//                    .writeUtf8(pixabay_crt)
//                    .inputStream(); // 得到证书的输入流
            try {

                trustManager = trustManagerForCertificates(inputStream);//以流的方式读入证书
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                sslSocketFactory = sslContext.getSocketFactory();

            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }

            pixClient = new OkHttpClient.Builder()
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
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .build();
            return pixClient;
//        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以流的方式添加信任证书
     */
    /**
     * Returns a trust manager that trusts {@code certificates} and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a {@code
     * SSLHandshakeException}.
     * <p>
     * <p>This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     * <p>
     * <p>
     * <h3>Warning: Customizing Trusted Certificates is Dangerous!</h3>
     * <p>
     * <p>Relying on your own trusted certificates limits your server team's ability to update their
     * TLS certificates. By installing a specific set of trusted certificates, you take on additional
     * operational complexity and limit your ability to migrate between certificate authorities. Do
     * not use custom trusted certificates in production without the blessing of your server's TLS
     * administrator.
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }


    /**
     * 添加password
     *
     * @param password
     * @return
     * @throws GeneralSecurityException
     */
    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}
