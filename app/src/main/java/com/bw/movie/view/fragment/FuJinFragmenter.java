package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.Bean;
import com.bw.movie.model.bean.FuJinBean;
import com.bw.movie.model.bean.GuanZhuBean;
import com.bw.movie.presenter.MainPresenter;
import com.bw.movie.view.adapter.FuJinAdapter;
import com.bw.movie.view.intenter.MainInterface;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: zh
 * @Date: 2019/4/12 16:35
 * @Description: :${赵光金}
 */
public class FuJinFragmenter extends Fragment implements MainInterface {
    @BindView(R.id.fujinXRecyclerView)
    RecyclerView fujinXRecyclerView;
    Unbinder unbinder;
    private FuJinAdapter fuJinAdapter;
    private MainPresenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fujinlayout, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fujinXRecyclerView.setLayoutManager(manager);
        fuJinAdapter = new FuJinAdapter(getActivity());
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        mainPresenter.getFuJinYingYuan(1, 10);
        fujinXRecyclerView.setAdapter(fuJinAdapter);
        EventBus.getDefault().register(FuJinFragmenter.this);
        return view;
    }
    @Subscribe
    public void getjie(List<Bean> beans) {
        Log.i("AA", "点击了" + beans.get(0).getId());
        boolean click = beans.get(0).isClick();
        if (click) {
            mainPresenter.getGuanZhu(beans.get(0).getId());
        } else {
            mainPresenter.getQuXiao(beans.get(0).getId());
        }


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(FuJinFragmenter.this);
    }

    @Override
    public void LoginOn(Object loginon) {
        FuJinBean fuJinBean = (FuJinBean) loginon;
        List<FuJinBean.ResultBean> result = fuJinBean.getResult();
        fuJinAdapter.getData(result);
    }

    @Override
    public void getguanzhu(Object o) {
        GuanZhuBean guanZhuBean = (GuanZhuBean) o;
        Toast.makeText(getActivity(), guanZhuBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getquxiao(Object o) {
        GuanZhuBean guanZhuBean = (GuanZhuBean) o;
        Toast.makeText(getActivity(), guanZhuBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
