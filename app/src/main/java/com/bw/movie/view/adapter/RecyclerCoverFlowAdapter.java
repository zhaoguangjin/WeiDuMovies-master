package com.bw.movie.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.YingYuanRecyclerCoverFlow;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Auther: zh
 * @Date: 2019/4/13 17:14
 * @Description: :${赵光金}
 */
public class RecyclerCoverFlowAdapter extends RecyclerCoverFlow.Adapter<RecyclerCoverFlowAdapter.VVS> {
    Context context;
    LayoutInflater linearLayout;

    public RecyclerCoverFlowAdapter(Context context) {
        this.linearLayout = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public VVS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = linearLayout.inflate(R.layout.recyclercoverflow, viewGroup, false);
        VVS vvs = new VVS(inflate);
        return vvs;
    }

    @Override
    public void onBindViewHolder(@NonNull VVS vvs, int i) {
        String s = results.get(i).toString();
        Uri parse = Uri.parse(s);
        vvs.img.setImageURI(parse);
        String s1 = text.get(i).toString();
        vvs.text.setText(s1);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    List<String> results = new ArrayList<>();
    public void getData(List<String> result) {
        this.results.clear();
        this.results.addAll(result);
        notifyDataSetChanged();
    }
    List<String> text =new ArrayList<>();
    public void getList(List<String> lists) {
        this.text.clear();
        this.text.addAll(lists);
        notifyDataSetChanged();
    }
    public class VVS extends RecyclerView.ViewHolder {
        private final SimpleDraweeView img;
        private final TextView text;

        public VVS(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text2);
        }
    }
}
