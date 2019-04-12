package com.bw.movie.presenter;

import com.bw.movie.model.bean.DengLuBean;
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

    public void getZhuCeView(String nickName, String phone, String pwd, String pwd2,int sex, String birthday, String email) {
        getdanli.api.getzhuce(nickName, phone, pwd, pwd2,sex, birthday, email)
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
    public void getDengLu(String phone,String pwd){
        getdanli.api.getdenglu(phone,pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DengLuBean>() {
                    @Override
                    public void accept(DengLuBean dengLuBean) throws Exception {
                        getView().LoginOn(dengLuBean);
                    }
                });

    }

}
