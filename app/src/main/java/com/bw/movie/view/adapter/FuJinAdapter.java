package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.Bean;
import com.bw.movie.model.bean.FuJinBean;
import com.bw.movie.view.activity.YingYuanXiangQingActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zh
 * @Date: 2019/4/12 20:54
 * @Description: :${赵光金}
 */
public class FuJinAdapter extends RecyclerView.Adapter<FuJinAdapter.DDF> {
    Context context;
    LayoutInflater inflater;

    public FuJinAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public DDF onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.fujinadapter, viewGroup, false);
        DDF ddf = new DDF(view);
        return ddf;
    }

    @Override
    public void onBindViewHolder(@NonNull DDF ddf, final int i) {
        String address = results.get(i).getAddress();
        ddf.fujinneirong.setText(address);
        String name = results.get(i).getName();
        ddf.fujinname.setText(name);
        String logo = results.get(i).getLogo();
        Uri uri = Uri.parse(logo);
        ddf.fujinimg.setImageURI(uri);
        int commentTotal = results.get(i).getCommentTotal();
        ddf.fujin.setText(commentTotal+"km");
        int followCinema = results.get(i).getFollowCinema();
        if (followCinema == 1) {
            ddf.shouchang.setChecked(true);
        } else {
            ddf.shouchang.setChecked(false);
        }
        ddf.shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox box = (CheckBox) v;
                boolean checked = box.isChecked();
                int id = results.get(i).getId();
                List<Bean> beans = new ArrayList<>();
                Bean bean = new Bean();
                bean.setClick(checked);
                bean.setId(id);
                beans.add(bean);
                EventBus.getDefault().post(beans);
            }
        });
        ddf.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YingYuanXiangQingActivity.class);
                intent.putExtra("id",results.get(i).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    List<FuJinBean.ResultBean> results=new ArrayList<>();
    public void getData(List<FuJinBean.ResultBean> result) {
        this.results.clear();
        this.results.addAll(result);
        notifyDataSetChanged();
    }

    public class DDF extends RecyclerView.ViewHolder {

        private final TextView fujin;
        private final TextView fujinname;
        private final TextView fujinneirong;
        private final SimpleDraweeView fujinimg;
        private final CheckBox shouchang;

        public DDF(@NonNull View itemView) {
            super(itemView);
            fujin = itemView.findViewById(R.id.fujinjuli);
            fujinname = itemView.findViewById(R.id.fujinname);
            fujinneirong = itemView.findViewById(R.id.fujinneirong);
            fujinimg = itemView.findViewById(R.id.fujinSimpleDraweeView);
            shouchang = itemView.findViewById(R.id.fujinshouchang);
        }
    }
}
