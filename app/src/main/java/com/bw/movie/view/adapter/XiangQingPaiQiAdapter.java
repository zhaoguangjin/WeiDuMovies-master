package com.bw.movie.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.XiangQingRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zh
 * @Date: 2019/4/14 16:40
 * @Description: :${赵光金}
 */
public class XiangQingPaiQiAdapter extends RecyclerView.Adapter<XiangQingPaiQiAdapter.MMN> {
    Context context;
    LayoutInflater inflater;

    public XiangQingPaiQiAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MMN onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.paiqilayout, viewGroup, false);
        MMN mmn = new MMN(view);
        return mmn;
    }

    @Override
    public void onBindViewHolder(@NonNull MMN mmn, int i) {
        String hall = results.get(i).getScreeningHall();
        mmn.paiaiyingting.setText(hall);
        String time = results.get(i).getBeginTime();
        mmn.paiqikaishi.setText(time);
        String endTime = results.get(i).getEndTime();
        mmn.paiqijieshu.setText(endTime);

    }

    @Override
    public int getItemCount() {
        return results.size();


    }

    List<XiangQingRecycler.ResultBean> results = new ArrayList<>();
    public void getData(List<XiangQingRecycler.ResultBean> result) {
        this.results.clear();
        this.results.addAll(result);
        notifyDataSetChanged();
    }

    public class MMN extends RecyclerView.ViewHolder {

        private final TextView paiaiyingting;
        private final TextView paiqikaishi;
        private final TextView paiqijieshu;

        public MMN(@NonNull View itemView) {
            super(itemView);
            paiaiyingting = itemView.findViewById(R.id.paiaiyingting);
            paiqikaishi = itemView.findViewById(R.id.paiqikaishi);
            paiqijieshu = itemView.findViewById(R.id.paiqijieshu);
        }
    }
}
