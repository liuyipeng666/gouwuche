package com.example.yuekao.mvp;

public class XiaParsenter implements constant.IParsenter {
    private constant.IView mIView;
    private XiaModel mModel;

    public XiaParsenter(constant.IView mIView) {
        this.mIView = mIView;
        mModel=new XiaModel();
    }

    @Override
    public void requsetData() {
        mModel.requsetData(new XiaModel.Callback() {
            @Override
            public void onData(XiaEntity xiaEntity) {
                mIView.onData(xiaEntity);
            }

            @Override
            public void onFailed(String str) {
                mIView.onFailed(str);
            }
        });
    }
}
