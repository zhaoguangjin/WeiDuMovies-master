package com.bw.movie.presenter;

/**
 * @Auther: Administrator
 * @Date: 2019/4/10 0010 10:27:11:${付贤栋}
 * @Description:
 */
public class BasePresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public  void deatchView(){
        this.view=view;
    }
}
