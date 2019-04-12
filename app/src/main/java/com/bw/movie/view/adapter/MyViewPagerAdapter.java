package com.bw.movie.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @Auther: Administrator
 * @Date: 2019/4/10 0010 19:27:50:${付贤栋}
 * @Description:
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentlist;
    public MyViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentlist) {
        super(fm);
        this.fragmentlist=fragmentlist;
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }


}
