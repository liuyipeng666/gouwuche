package com.example.yuekao.db;

import android.content.Context;

public
class GwDBManager {

    private static volatile GwDBManager dbManager=new GwDBManager();

    private String DB_NAME="gouwucheche_db";

    public static GwDBManager getInstance(){
        return dbManager;
    }

    public Context context;


    public  void init(Context context){
        this.context=context;
    }


    private DaoMaster daoMaster;

    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;

    public DaoMaster getDaoMaster(){
        if(daoMaster==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
            daoMaster=new DaoMaster(devOpenHelper.getWritableDatabase());
        }
        return daoMaster;
    }


    public DaoSession getDaoSession(){
        if(daoMaster==null){
            if(daoSession==null){
                getDaoMaster();
            }
            daoSession=daoMaster.newSession();
        }
        return daoSession;
    }


    //清除
    public void clear(){
        if(devOpenHelper!=null){
            devOpenHelper=null;
        }
        if(daoSession!=null){
            daoSession.clear();
            daoSession=null;
        }
    }
}
