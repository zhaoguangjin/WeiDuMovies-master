package com.bw.movie.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.XiangQingBean;
import com.bw.movie.model.bean.XiangQingRecycler;
import com.bw.movie.model.bean.YingYuanRecyclerCoverFlow;
import com.bw.movie.presenter.MainPresenter;
import com.bw.movie.view.adapter.RecyclerCoverFlowAdapter;
import com.bw.movie.view.adapter.XiangQingPaiQiAdapter;
import com.bw.movie.view.intenter.MainInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class YingYuanXiangQingActivity extends AppCompatActivity implements MainInterface {

    @BindView(R.id.xiangqingimg)
    SimpleDraweeView xiangqingimg;
    @BindView(R.id.xiangqingname)
    TextView xiangqingname;
    @BindView(R.id.xingqingdizhi)
    TextView xingqingdizhi;
    @BindView(R.id.xiangqingRecyclerCoverFlow)
    RecyclerCoverFlow xiangqingRecyclerCoverFlow;
    @BindView(R.id.xiangqingRecyclerView)
    RecyclerView xiangqingRecyclerView;

    private RecyclerCoverFlowAdapter recyclerCoverFlowAdapter;

    private MainPresenter mainPresenter;
    private int id1;
    private XiangQingBean.ResultBean result;
    private XiangQingPaiQiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying_yuan_xiang_qing);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("id", 0);
        Log.i("AA", "id1" + id1);
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        mainPresenter.getYingYuanXiangQing(id1);
        mainPresenter.getYingYuanRecyclerCoverFlow(id1);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xiangqingRecyclerView.setLayoutManager(manager);
        xiangqingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow();
                popupWindow.setContentView();
            }
        });

    }

    @Override
    public void LoginOn(Object loginon) {
        XiangQingBean xiangQingBean = (XiangQingBean) loginon;
        result = xiangQingBean.getResult();
        String name = xiangQingBean.getResult().getName();
        xiangqingname.setText(name);
        String logo = xiangQingBean.getResult().getLogo();
        Uri parse = Uri.parse(logo);
        xiangqingimg.setImageURI(parse);
        String address = xiangQingBean.getResult().getAddress();
        xingqingdizhi.setText(address);
    }

    @Override
    public void getguanzhu(Object o) {
        YingYuanRecyclerCoverFlow yingYuanRecyclerCoverFlow = (YingYuanRecyclerCoverFlow) o;
        if (yingYuanRecyclerCoverFlow.getStatus().equals("0000")) {

            final List<YingYuanRecyclerCoverFlow.ResultBean> result1 = yingYuanRecyclerCoverFlow.getResult();
            List<String> lists = new ArrayList<>();
            List<String> list = new ArrayList<>();
            if (result1.size() > 0) {
                for (int i = 0; i < result1.size(); i++) {
                    String imageUrl = result1.get(i).getImageUrl();
                    list.add(imageUrl);
                    String name = result1.get(i).getName();
                    lists.add(name);
                }

            }
            String id = result1.get(0).getId();
            int s = Integer.parseInt(id);
            mainPresenter.getYingYuanPaiQi(id1, s);

            xiangqingRecyclerCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                @Override
                public void onItemSelected(int position) {
                    String id = result1.get(position).getId();
                    int i = Integer.parseInt(id);
                    mainPresenter.getYingYuanPaiQi(id1, i);

                }
            });
            recyclerCoverFlowAdapter = new RecyclerCoverFlowAdapter(this);
            recyclerCoverFlowAdapter.getData(list);
            recyclerCoverFlowAdapter.getList(lists);
            xiangqingRecyclerCoverFlow.setAdapter(recyclerCoverFlowAdapter);
        } else {
            Toast.makeText(this, "本期无电影", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getquxiao(Object o) {
        XiangQingRecycler recycler = (XiangQingRecycler) o;
        List<XiangQingRecycler.ResultBean> result = recycler.getResult();
        adapter = new XiangQingPaiQiAdapter(this);
        Log.i("AA", "" + result.size());
        adapter.getData(result);
        xiangqingRecyclerView.setAdapter(adapter);

    }
}
