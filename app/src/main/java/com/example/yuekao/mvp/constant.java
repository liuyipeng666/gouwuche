package com.example.yuekao.mvp;

public interface constant {

    interface IView{
        void onData(XiaEntity xiaEntity);
        void onFailed(String str);
    }

    interface IParsenter{
        void requsetData();
    }

    interface IModel{
        void requsetData(XiaModel.Callback callback);
    }


}
