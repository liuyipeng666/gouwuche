package com.example.yuekao.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuekao.R;
import com.example.yuekao.mvp.XiaEntity;

import java.util.List;

public class XiaAdapter  extends BaseQuickAdapter<XiaEntity.DataBean, BaseViewHolder> {
    public XiaAdapter(int layoutResId, @Nullable List<XiaEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaEntity.DataBean item) {
        helper.setText(R.id.t1,item.getTitle());

        ImageView view = helper.getView(R.id.m1);

        Glide.with(mContext).load(item.getPic()).into(view);
    }
}
