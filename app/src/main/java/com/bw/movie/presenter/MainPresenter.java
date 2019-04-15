package com.bw.movie.presenter;

import com.bw.movie.model.bean.DengLuBean;
import com.bw.movie.model.bean.FuJinBean;
import com.bw.movie.model.bean.GuanZhuBean;
import com.bw.movie.model.bean.TuiJianBean;
import com.bw.movie.model.bean.XiangQingBean;
import com.bw.movie.model.bean.XiangQingRecycler;
import com.bw.movie.model.bean.YingYuanRecyclerCoverFlow;
import com.bw.movie.model.bean.ZhuCheBeab;
import com.bw.movie.model.ulit.Ulit;
import com.bw.movie.view.intenter.MainInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther: Administrator
 * @Date: 2019/4/10 0010 10:27:03:${付贤栋}
 * @Description:
 */
public class MainPresenter extends BasePresenter<MainInterface> {

    private final Ulit getdanli;

    public MainPresenter() {
        getdanli = Ulit.getdanli();
    }

    public void getZhuCeView(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String email) {
        getdanli.api.getzhuce(nickName, phone, pwd, pwd2, sex, birthday, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhuCheBeab>() {
                    @Override
                    public void accept(ZhuCheBeab zhuCheBeab) throws Exception {
                        getView().LoginOn(zhuCheBeab);
                    }
                })
        ;

    }

    public void getDengLu(String phone, String pwd) {
        getdanli.api.getdenglu(phone, pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DengLuBean>() {
                    @Override
                    public void accept(DengLuBean dengLuBean) throws Exception {
                        getView().LoginOn(dengLuBean);
                    }
                });

    }

    public void getTuiJianYingYuan(int page, int count) {
        getdanli.api.gettuijianyingyuan(page, count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TuiJianBean>() {
                    @Override
                    public void accept(TuiJianBean tuiJianBean) throws Exception {
                        getView().LoginOn(tuiJianBean);
                    }
                });
    }

    public void getFuJinYingYuan(int page, int count) {
        getdanli.api.getfujinyingyuan(page, count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FuJinBean>() {
                               @Override
                               public void accept(FuJinBean fuJinBean) throws Exception {
                                   getView().LoginOn(fuJinBean);
                               }
                           }

                );
    }

    public void getGuanZhu(int id) {
        getdanli.api.getguanzhu(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GuanZhuBean>() {
                    @Override
                    public void accept(GuanZhuBean guanZhuBean) throws Exception {
                        getView().getguanzhu(guanZhuBean);
                    }
                });
    }

    public void getQuXiao(int id) {
        getdanli.api.getquxiao(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GuanZhuBean>() {
                    @Override
                    public void accept(GuanZhuBean guanZhuBean) throws Exception {
                        getView().getguanzhu(guanZhuBean);
                    }
                });
    }

    public void getYingYuanXiangQing(int id) {
        getdanli.api.getyingyuanxiangqing(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiangQingBean>() {
                               @Override
                               public void accept(XiangQingBean xiangQingBean) throws Exception {
                                   getView().LoginOn(xiangQingBean);
                               }
                           }
                );
    }

    public void getYingYuanRecyclerCoverFlow(int id) {
        getdanli.api.getyingyuanRecyclerCoverFlow(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YingYuanRecyclerCoverFlow>() {
                               @Override
                               public void accept(YingYuanRecyclerCoverFlow yingYuanRecyclerCoverFlow) throws Exception {
                                   getView().getguanzhu(yingYuanRecyclerCoverFlow);
                               }
                           }
                );
    }

    public void getYingYuanPaiQi(int yingyuanid, int dianyingid) {
        getdanli.api.getpaiqi(yingyuanid, dianyingid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiangQingRecycler>() {
                               @Override
                               public void accept(XiangQingRecycler xiangQingRecycler) throws Exception {
                                   getView().getquxiao(xiangQingRecycler);
                               }
                           }
                );
    }
}
