package com.example.yuekao;

import android.app.Application;

import com.alipay.sdk.app.EnvUtils;
import com.example.yuekao.db.DBManager;
import com.example.yuekao.db.GwDBManager;

import org.greenrobot.eventbus.EventBus;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DBManager.getInstance().init(this);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        GwDBManager.getInstance().init(this);
    }
}
