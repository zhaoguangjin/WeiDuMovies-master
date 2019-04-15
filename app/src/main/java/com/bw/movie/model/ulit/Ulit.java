package com.bw.movie.model.ulit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bw.movie.model.app.Api;
import com.bw.movie.model.app.App;

import java.io.IOException;

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

         OkHttpClient.Builder builder = new OkHttpClient.Builder();
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
                Request build = request.newBuilder()
                        .addHeader("sessionId", sessionId)
                        .addHeader("userId", userId).build();
                Log.i("AA","sessionId"+userId);
                Log.i("AA","userId"+userId);
                Response proceed = chain.proceed(build);
                return proceed;
            }
        });
        OkHttpClient build1 = builder.build();
        Retrofit build = new Retrofit.Builder()
              .client(build1)
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
