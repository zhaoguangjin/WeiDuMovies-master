package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.view.adapter.MyViewPagerAdapter;
import com.bw.movie.view.fragment.PageFourFragment;
import com.bw.movie.view.fragment.PageOneFragment;
import com.bw.movie.view.fragment.PageThreeFragment;
import com.bw.movie.view.fragment.PageTwoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.re_1)
    RadioButton re1;
    @BindView(R.id.re_2)
    RadioButton re2;
    @BindView(R.id.re_3)
    RadioButton re3;
    @BindView(R.id.re_4)
    RadioButton re4;
    private ViewPager start_viewPager;
    private ArrayList<Fragment> fragmentlist;
    private MyViewPagerAdapter myViewPagerAdapter;
    private SharedPreferences fistRun;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        //ViewPager
        start_viewPager = findViewById(R.id.Start_ViewPager);
        re1.setChecked(true);
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new PageOneFragment());
        fragmentlist.add(new PageTwoFragment());
        fragmentlist.add(new PageThreeFragment());
        fragmentlist.add(new PageFourFragment());
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragmentlist);
        fistRun = getSharedPreferences("FistRun", 0);
        edit = fistRun.edit();
        isfirstRun();


    }

    private void isViewPagerRun() {
        start_viewPager.setAdapter(myViewPagerAdapter);
        start_viewPager.setCurrentItem(0);
        //ViewPager改变状态监听
        start_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        re1.setChecked(true);
                        break;
                    case 1:
                        re2.setChecked(true);
                        break;
                    case 2:
                        re3.setChecked(true);
                        break;
                    case 3:
                        re4.setChecked(true);
                        edit.putBoolean("First", true);
                        edit.commit();
                        startActivity(new Intent(StartActivity.this, HomeActivity.class));
                        break;
                }
             /*   if (i == 3) {

                    edit.putBoolean("First", trues);
                    edit.commit();
                    startActivity(new Intent(StartActivity.this, HomeActivity.class));
                }*/

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void isfirstRun() {

        boolean first = fistRun.getBoolean("First", false);
        if (!first) {
            isViewPagerRun();
        } else {
            Toast.makeText(StartActivity.this, "不是第一次", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StartActivity.this, HomeActivity.class));
        }
    }
}
