package com.example.yuekao.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuekao.R;
import com.example.yuekao.db.liulan;

import java.util.List;

public class LiulanAdapter extends BaseQuickAdapter<liulan, BaseViewHolder> {
    public LiulanAdapter(int layoutResId, @Nullable List<liulan> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, liulan item) {
        helper.setText(R.id.tt1,item.getTitle());

        ImageView view = helper.getView(R.id.mm1);
        Glide.with(mContext).load(item.getPic()).into(view);
    }
}
