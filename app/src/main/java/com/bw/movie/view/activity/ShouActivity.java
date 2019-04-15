package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.view.fragment.FuJinFragmenter;
import com.bw.movie.view.fragment.TuiJianFragmenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShouActivity extends FragmentActivity {

    @BindView(R.id.tuijian)
    RadioButton tuijian;
    @BindView(R.id.fujin)
    RadioButton fujin;

    private FuJinFragmenter fuJinFragmenter;
    private TuiJianFragmenter tuiJianFragmenter;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ying_pian);
        ButterKnife.bind(this);

        fuJinFragmenter = new FuJinFragmenter();
        tuiJianFragmenter = new TuiJianFragmenter();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.yingyuanFrameLayout, tuiJianFragmenter);
        transaction.commit();
    }

    @OnClick({R.id.tuijian, R.id.fujin})
    public void onViewClicked(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.tuijian:
                transaction.replace(R.id.yingyuanFrameLayout, tuiJianFragmenter);
                break;
            case R.id.fujin:
                transaction.replace(R.id.yingyuanFrameLayout, fuJinFragmenter);
                break;
        }
        transaction.commit();
    }
}
