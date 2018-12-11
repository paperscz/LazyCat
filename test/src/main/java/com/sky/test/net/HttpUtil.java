package com.sky.test.net;


import com.sky.test.BuildConfig;
import com.sky.test.converter.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/11 0011.
 **/
public class HttpUtil {

    private Retrofit mRetrofit;

    private static class SingletonInstance {
        private static HttpUtil instance = new HttpUtil();
    }

    public static HttpUtil getInstance() {
        return SingletonInstance.instance;
    }

    private HttpUtil() {
        OkHttpClient httpClient = provideHttpClient();
        mRetrofit = provideRetrofit(httpClient);
    }

    private OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 连接超时设置
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    private Retrofit provideRetrofit(OkHttpClient httpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(httpClient);
        //ConverterFactory 对请求和返回的字符串进行解析，可自定义ConverterFactory
        builder.addConverterFactory(GsonConverterFactory.create());
        //CallAdapterFactory 把字符串转换为对象，可自定义CallAdapterFactory
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(Urls.GANK_API_BASE);
        return builder.build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
