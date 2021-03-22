package com.example.yuekao.mvp;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class XiaModel implements constant.IModel {

    private String url="http://www.qubaobei.com";

    @Override
    public void requsetData(Callback callback) {
        //解析数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit build = new Retrofit.Builder()
                        .baseUrl(url)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = build.create(Api.class);
                Observable<XiaEntity> getdata = api.getdata(1, 20, 1);

                getdata.subscribeOn(Schedulers.io())
                        .subscribe(new Observer<XiaEntity>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull XiaEntity xiaEntity) {
                                callback.onData(xiaEntity);
                                Log.i("wwww", "onNext: "+xiaEntity.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        }).start();
    }


    interface Callback{
        void onData(XiaEntity xiaEntity);
        void onFailed(String str);
    }
}
