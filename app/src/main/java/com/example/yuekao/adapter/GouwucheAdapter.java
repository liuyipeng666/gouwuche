package com.example.yuekao.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuekao.R;
import com.example.yuekao.db.Gouwuche;
import com.example.yuekao.db.Gouwucheche;

import java.util.List;

public
class GouwucheAdapter extends BaseQuickAdapter<Gouwucheche, BaseViewHolder> {
    public GouwucheAdapter(int layoutResId, @Nullable List<Gouwucheche> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Gouwucheche item) {
        helper.setText(R.id.gwt1,item.getTitle());

        helper.setText(R.id.gwcshuliang,item.getShuliang()+"");

        ImageView view = helper.getView(R.id.gwm1);
        Glide.with(mContext).load(item.getPic()).into(view);

        helper.setChecked(R.id.ck,item.getCheck());

        helper.addOnClickListener(R.id.ck);

    }
}
