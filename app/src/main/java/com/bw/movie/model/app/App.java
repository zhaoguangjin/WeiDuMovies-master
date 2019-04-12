package com.bw.movie.model.app;

import android.app.Application;
import android.content.Context;

/**
 * @Auther: zh
 * @Date: 2019/4/11 22:03
 * @Description: :${赵光金}
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getapp(){
        return context;
    }
}
