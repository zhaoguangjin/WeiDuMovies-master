package com.bw.movie.view.fragment;

import android.content.Intent;
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
import com.bw.movie.model.bean.GuanZhuBean;
import com.bw.movie.model.bean.TuiJianBean;
import com.bw.movie.presenter.MainPresenter;
import com.bw.movie.view.adapter.TuiJianAdapter;
import com.bw.movie.view.intenter.MainInterface;

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
public class TuiJianFragmenter extends Fragment implements MainInterface {
    @BindView(R.id.tuijianXRecyclerView)
    RecyclerView tuijianXRecyclerView;
    Unbinder unbinder;
    private int page = 1;
    private int count = 10;
    private TuiJianAdapter tuiJianAdapter;
    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tuijianlayout, container, false);
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.getTuiJianYingYuan(page, count);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        tuijianXRecyclerView.setLayoutManager(manager);
        tuiJianAdapter = new TuiJianAdapter(getActivity());
        tuijianXRecyclerView.setAdapter(tuiJianAdapter);
        EventBus.getDefault().register(TuiJianFragmenter.this);
        return view;
    }

    @Subscribe
    public void getjie(List<Bean> beans) {
        Log.i("AA", "点击了" + beans.get(0).getId());
        boolean click = beans.get(0).isClick();
        if (click) {
            presenter.getGuanZhu(beans.get(0).getId());
        } else {
            presenter.getQuXiao(beans.get(0).getId());
        }


    }

    @Override
    public void LoginOn(Object loginon) {
        TuiJianBean tuiJianBean = (TuiJianBean) loginon;
        List<TuiJianBean.ResultBean> result = tuiJianBean.getResult();
        tuiJianAdapter.setData(result);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(TuiJianFragmenter.this);
    }

}
