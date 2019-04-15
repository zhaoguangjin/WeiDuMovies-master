package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.Bean;
import com.bw.movie.model.bean.TuiJianBean;
import com.bw.movie.view.activity.YingYuanXiangQingActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zh
 * @Date: 2019/4/12 18:19
 * @Description: :${赵光金}
 */
public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.GGH> {
    Context context;
    LayoutInflater inflater;

    public TuiJianAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public GGH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.tuijianadapter, viewGroup, false);
        GGH ggh = new GGH(view);

        return ggh;
    }

    @Override
    public void onBindViewHolder(@NonNull GGH ggh, final int i) {

        String name = results.get(i).getName();
        ggh.tuijianname.setText(name);
        int commentTotal = results.get(i).getCommentTotal();
        ggh.tuijianjuli.setText(commentTotal + "km");
        String logo = results.get(i).getLogo();
        Uri uri = Uri.parse(logo);
        ggh.tuijianimg.setImageURI(uri);
        String address = results.get(i).getAddress();
        ggh.tuijiannierong.setText(address);
        int followCinema = results.get(i).getFollowCinema();
        if (followCinema == 1) {
            ggh.shouchang.setChecked(true);
        } else {
            ggh.shouchang.setChecked(false);
        }
        ggh.shouchang.setOnClickListener(new View.OnClickListener() {
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
        ggh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,YingYuanXiangQingActivity.class);
                intent.putExtra("id",results.get(i).getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    List<TuiJianBean.ResultBean> results = new ArrayList<>();

    public void setData(List<TuiJianBean.ResultBean> result) {
        this.results.clear();
        this.results.addAll(result);
        notifyDataSetChanged();
    }

    public class GGH extends RecyclerView.ViewHolder {

        private final TextView tuijianname;
        private final SimpleDraweeView tuijianimg;
        private final TextView tuijiannierong;
        private final TextView tuijianjuli;
        private final CheckBox shouchang;


        public GGH(@NonNull View itemView) {
            super(itemView);
            tuijianname = itemView.findViewById(R.id.tuijianname);
            tuijianimg = itemView.findViewById(R.id.tuijianSimpleDraweeView);
            tuijiannierong = itemView.findViewById(R.id.tuijianneirong);
            tuijianjuli = itemView.findViewById(R.id.tuijianjuli);
            shouchang = itemView.findViewById(R.id.tuijianshouchang);


        }
    }
}
