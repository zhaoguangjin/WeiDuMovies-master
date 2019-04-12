package com.gordon.olver.weidumovie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gordon.olver.weidumovie.R;
import com.gordon.olver.weidumovie.model.app.App;
import com.gordon.olver.weidumovie.model.bean.DengLuBean;
import com.gordon.olver.weidumovie.model.ulit.Itenter;
import com.gordon.olver.weidumovie.model.md5.EncryptUtil;
import com.gordon.olver.weidumovie.presenter.MainPresenter;
import com.gordon.olver.weidumovie.view.intenter.MainInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements MainInterface {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.jizhumima)
    CheckBox jizhumima;
    @BindView(R.id.kuaisuzhuce)
    TextView kuaisuzhuce;
    @BindView(R.id.denglu)
    Button denglu;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        boolean conn = Itenter.isConn(this);
        if (conn) {
            Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();

        } else {
            Itenter.showNoNetWorkDlg(this);
        }
        SharedPreferences cun = getSharedPreferences("CUN", Context.MODE_PRIVATE);
        String names = cun.getString("names", "");
        String pwds = cun.getString("pwds", "");
        boolean click = cun.getBoolean("click", false);
        name.setText(names);
        pwd.setText(pwds);
        jizhumima.setChecked(click);
       
    }

    @OnClick({R.id.jizhumima, R.id.kuaisuzhuce, R.id.denglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jizhumima:

                break;
            case R.id.kuaisuzhuce:
                Intent intent = new Intent(HomeActivity.this, RegisteredActivity.class);
                startActivity(intent);
                break;
            case R.id.denglu:
                String names = name.getText().toString();
                String pwds = pwd.getText().toString();
                String encrypt = EncryptUtil.encrypt(pwds);
                mainPresenter.getDengLu(names, encrypt);
                break;
        }
    }

    @Override
    public void LoginOn(Object loginon) {
        DengLuBean dengLuBean = (DengLuBean) loginon;
        if (dengLuBean.getStatus().equals("0000")) {
            if (jizhumima.isChecked()) {
                String names = name.getText().toString();
                String pwds = pwd.getText().toString();
                SharedPreferences cun = getSharedPreferences("CUN", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = cun.edit();
                edit.putString("names",names);
                edit.putString("pwds",pwds);
                edit.putBoolean("click",jizhumima.isChecked());
                edit.apply();
            }
            int userId = dengLuBean.getResult().getUserId();
            String sessionId = dengLuBean.getResult().getSessionId();
            SharedPreferences id = App.getapp().getSharedPreferences("ID", MODE_PRIVATE);
            SharedPreferences.Editor edits = id.edit();
            edits.putString("sessionId",sessionId);
            edits.putString("userId",userId+"");
            edits.apply();

            Toast.makeText(this, dengLuBean.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, dengLuBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
