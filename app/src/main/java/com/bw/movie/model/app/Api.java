package com.bw.movie.model.app;

import com.bw.movie.model.bean.DengLuBean;
import com.bw.movie.model.bean.FuJinBean;
import com.bw.movie.model.bean.GuanZhuBean;
import com.bw.movie.model.bean.TuiJianBean;
import com.bw.movie.model.bean.XiangQingBean;
import com.bw.movie.model.bean.XiangQingRecycler;
import com.bw.movie.model.bean.YingYuanRecyclerCoverFlow;
import com.bw.movie.model.bean.ZhuCheBeab;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Observable<DengLuBean> getdenglu(@Field("phone") String phone, @Field("pwd") String pwd);

    //推荐影院
    //http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas
    @GET("movieApi/cinema/v1/findRecommendCinemas?")
    Observable<TuiJianBean> gettuijianyingyuan(@Query("page") int page, @Query("count") int count);

    //附近影院
    //http://172.17.8.100/movieApi/cinema/v1/findNearbyCinemas
    @GET("movieApi/cinema/v1/findNearbyCinemas?")
    Observable<FuJinBean> getfujinyingyuan(@Query("page") int page, @Query("count") int count);

    //关注
    //http://172.17.8.100/movieApi/cinema/v1/verify/followCinema
    @GET("movieApi/cinema/v1/verify/followCinema?")
    Observable<GuanZhuBean> getguanzhu(@Query("cinemaId") int cinemaId);

    //http://172.17.8.100/movieApi/cinema/v1/verify/cancelFollowCinema
    @GET("movieApi/cinema/v1/verify/cancelFollowCinema?")
    Observable<GuanZhuBean> getquxiao(@Query("cinemaId") int cinemaId);

    //http://172.17.8.100/movieApi/cinema/v1/findCinemaInfo
    @GET("movieApi/cinema/v1/findCinemaInfo?")
    Observable<XiangQingBean> getyingyuanxiangqing(@Query("cinemaId") int cinemaId);

    //http://172.17.8.100/movieApi/movie/v1/findMovieListByCinemaId
    @GET("movieApi/movie/v1/findMovieListByCinemaId?")
    Observable<YingYuanRecyclerCoverFlow> getyingyuanRecyclerCoverFlow(@Query("cinemaId") int cinemaId);

    //http://172.17.8.100/movieApi/movie/v1/findMovieScheduleList
    @GET("movieApi/movie/v1/findMovieScheduleList?")
    Observable<XiangQingRecycler> getpaiqi(@Query("cinemasId") int cinemasId,@Query("movieId") int movieId);
}
