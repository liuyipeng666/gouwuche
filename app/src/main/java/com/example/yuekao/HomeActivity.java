package com.example.yuekao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yuekao.adapter.XiaAdapter;
import com.example.yuekao.db.DBManager;
import com.example.yuekao.db.liulan;
import com.example.yuekao.mvp.XiaEntity;
import com.example.yuekao.mvp.XiaParsenter;
import com.example.yuekao.mvp.constant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public class HomeActivity extends Activity implements constant.IView {

    private RecyclerView re1;
    private Handler handler = new Handler();
    private SmartRefreshLayout smallLabel;
    private int i=1;
    XiaAdapter xiaAdapter;
    List<XiaEntity.DataBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        new XiaParsenter(this).requsetData();

        smallLabel.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                //上拉加载
                i++;
                OkGo.<String>get("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page="+i).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new Gson();
                        XiaEntity xiaEntity = gson.fromJson(body, XiaEntity.class);
                        List<XiaEntity.DataBean> data1 = xiaEntity.getData();

                        data.addAll(data1);

                        xiaAdapter.notifyDataSetChanged();

                        Toast.makeText(HomeActivity.this, "上拉加载了"+data1.size()+"条数据", Toast.LENGTH_SHORT).show();
                    }
                });

                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                Toast.makeText(HomeActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh();
            }
        });

    }

    private void initView() {
        re1 = (RecyclerView) findViewById(R.id.re1);
        smallLabel = (SmartRefreshLayout) findViewById(R.id.smallLabel);
    }


    @Override
    public void onData(XiaEntity xiaEntity) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                 data = xiaEntity.getData();

                xiaAdapter = new XiaAdapter(R.layout.items, data);

                re1.setAdapter(xiaAdapter);
                re1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

                xiaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //先存入数据库
                        XiaEntity.DataBean dataBean = data.get(position);
                        liulan liulan = new liulan();
                        liulan.setPic(dataBean.getPic());
                        liulan.setTitle(dataBean.getTitle());
                        DBManager.getInstance().getDaoSession().insert(liulan);


                        //跳入详情页面
                        Intent intent = new Intent(HomeActivity.this,XiangActivity.class);
                        intent.putExtra("pic",data.get(position).getPic());
                        Log.i("www", "onItemClick: "+data.get(position).getPic());
                        intent.putExtra("title",data.get(position).getTitle());
                        intent.putExtra("food",data.get(position).getFood_str());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onFailed(String str) {

    }
}