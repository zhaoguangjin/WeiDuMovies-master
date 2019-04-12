package com.gordon.olver.weidumovie.model.app;

import com.gordon.olver.weidumovie.model.bean.DengLuBean;
import com.gordon.olver.weidumovie.model.bean.ZhuCheBeab;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @Auther: Administrator
 * @Date: 2019/4/10 0010 16:32:31:${付贤栋}
 * @Description:
 */
public interface Api {
    //http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser?")
    Observable<ZhuCheBeab> getzhuce(@Field("nickName") String nickName,
                                     @Field("phone") String phone,
                                     @Field("pwd") String pwd,
                                     @Field("pwd2") String pwds,
                                     @Field("sex") int sex,
                                     @Field("birthday") String birthday,
                                     @Field("email") String email

    );
    //http://172.17.8.100/movieApi/user/v1/login
    @FormUrlEncoded
    @POST("movieApi/user/v1/login?")
    Observable<DengLuBean> getdenglu(@Field("phone") String phone,@Field("pwd") String pwd);

}
