package com.gordon.olver.weidumovie.model.ulit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gordon.olver.weidumovie.model.app.Api;
import com.gordon.olver.weidumovie.model.app.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: zh
 * @Date: 2019/4/11 19:49
 * @Description: :${赵光金}
 */
public class Ulit {
    private  static final Ulit ULIT = new Ulit();
    public final Api api;

    private Ulit(){

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
       /* builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        builder.writeTimeout(15,TimeUnit.SECONDS);*/
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                SharedPreferences id = App.getapp().getSharedPreferences("ID", Context.MODE_PRIVATE);
                 String sessionId = id.getString("sessionId", "");
                 String userId = id.getString("userId", "");

                Request.Builder builder1 = request.newBuilder();
                builder1.header("sessionId",sessionId);
                builder1.header("userId",userId);
                Request build = builder1.build();
                return chain.proceed(build);
            }
        });
        Retrofit build = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://172.17.8.100/")
                .build();
        api = build.create(Api.class);
    }
    public static Ulit getdanli(){
        return ULIT;
    }
}
