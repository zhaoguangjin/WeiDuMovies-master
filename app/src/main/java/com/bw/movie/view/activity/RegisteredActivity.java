package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.ZhuCheBeab;
import com.bw.movie.model.md5.EncryptUtil;
import com.bw.movie.presenter.MainPresenter;
import com.bw.movie.view.intenter.MainInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisteredActivity extends AppCompatActivity implements MainInterface {

    @BindView(R.id.zcnicheng)
    EditText zcnicheng;
    @BindView(R.id.zcxingbie)
    EditText zcxingbie;
    @BindView(R.id.zcchushengriqi)
    EditText zcchushengriqi;
    @BindView(R.id.zcshoujihao)
    EditText zcshoujihao;
    @BindView(R.id.zcyouxiang)
    EditText zcyouxiang;
    @BindView(R.id.zcdenglumima)
    EditText zcdenglumima;
    @BindView(R.id.zcbutton)
    Button zcbutton;
    @BindView(R.id.zcdenglumima2)
    EditText zcdenglumima2;
    private MainPresenter mainPresenter;
    int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
    }

    @Override
    public void LoginOn(Object loginon) {
        ZhuCheBeab zhuCheBeab = (ZhuCheBeab) loginon;
        if (zhuCheBeab.getStatus().equals("0000")) {
            Toast.makeText(this, zhuCheBeab.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisteredActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, zhuCheBeab.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.zcbutton)
    public void onViewClicked() {
        String denglumima2 = zcdenglumima2.getText().toString();
        String nicheng = zcnicheng.getText().toString();
        String xingbie = zcxingbie.getText().toString();
        String chushengriqi = zcchushengriqi.getText().toString();
        String shoujihao = zcshoujihao.getText().toString();
        String youxiang = zcyouxiang.getText().toString();
        String denglumima = zcdenglumima.getText().toString();
        String encrypt = EncryptUtil.encrypt(denglumima);
        String encrypt1 = EncryptUtil.encrypt(denglumima2);

            if (xingbie.equals("男")) {
                this.s = 1;

            } else if (xingbie.equals("女")) {
                this.s = 2;

            } else {
                Toast.makeText(this, "性别有误", Toast.LENGTH_SHORT).show();
            }
            mainPresenter.getZhuCeView(nicheng, shoujihao, encrypt, encrypt1, this.s, chushengriqi, youxiang);

        }

    }

