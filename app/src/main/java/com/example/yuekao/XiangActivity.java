package com.example.yuekao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.yuekao.adapter.LiulanAdapter;
import com.example.yuekao.db.DBManager;
import com.example.yuekao.db.Gouwuche;
import com.example.yuekao.db.Gouwucheche;
import com.example.yuekao.db.GwDBManager;
import com.example.yuekao.db.liulan;
import com.example.yuekao.pay.AuthResult;
import com.example.yuekao.pay.PayResult;
import com.example.yuekao.pay.util.OrderInfoUtil2_0;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

public class XiangActivity extends Activity {

    private ImageView fanhui;
    private ImageView sandian;
    private ImageView fenxiang;
    private ImageView m1;
    private TextView t1;
    private TextView t2;
    private Button jiaru;
    private Button goumai;
    private RecyclerView recycle;
    private DrawerLayout draw;
    private LinearLayout lin;
    int i = 1;
    private TextView gwcshuliang;
    private ImageView gouwuche;
    private int s=0;
    @Override
    protected void onStart() {
        super.onStart();
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }

    }

    private void shuliang(){
        //获取数据库数据
        List<Gouwucheche> gouwucheches = GwDBManager.getInstance().getDaoSession().loadAll(Gouwucheche.class);

        for(int i=0;i<gouwucheches.size();i++){
            s+=gouwucheches.get(i).getShuliang();
        }

        gwcshuliang.setText(s+"");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        initView();
        shuliang();
        Intent intent = getIntent();
        String pic = intent.getStringExtra("pic");
        Log.i("www", "onCreate: " + pic);
        String title = intent.getStringExtra("title");
        String food = intent.getStringExtra("food");

        Glide.with(this).load(pic).into(m1);

        t1.setText(title + "");
        t2.setText(food + "");

        sandian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow(XiangActivity.this);

                View inflate = LayoutInflater.from(XiangActivity.this).inflate(R.layout.pop, null);
                View viewById = inflate.findViewById(R.id.liulan);
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        draw.openDrawer(lin);
                        popupWindow.dismiss();

                    }
                });
                popupWindow.setContentView(inflate);
                popupWindow.setHeight(320);
                popupWindow.setWidth(200);
                popupWindow.setOutsideTouchable(true);

                popupWindow.showAsDropDown(sandian, 0, 0);

            }
        });




        //获取数据库
        List<liulan> liulans = DBManager.getInstance().getDaoSession().loadAll(liulan.class);
        Log.i("www", "onCreate: " + liulans.size());
        LiulanAdapter liulanAdapter = new LiulanAdapter(R.layout.liulanitems, liulans);
        recycle.setAdapter(liulanAdapter);
        recycle.setLayoutManager(new LinearLayoutManager(this));


        goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payV2(view);

            }
        });

        jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow(XiangActivity.this);
                View inflate = LayoutInflater.from(XiangActivity.this).inflate(R.layout.gouwu, null);
                Button queding = inflate.findViewById(R.id.queding);
                TextView jia = inflate.findViewById(R.id.jia);
                TextView jian = inflate.findViewById(R.id.jian);
                TextView shu = inflate.findViewById(R.id.shu);

                queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //加入数据库
                        Gouwucheche gouwuche = new Gouwucheche();

                        gouwuche.setPic(pic);
                        gouwuche.setShuliang(i);
                        gouwuche.setTitle(title);
                        gouwuche.setCheck(false);
                        GwDBManager.getInstance().getDaoSession().insert(gouwuche);

                        s=s+i;

                        gwcshuliang.setText(""+s);



                        popupWindow.dismiss();

                    }
                });

                jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i++;
                        shu.setText("" + i);
                    }
                });


                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i-- <=1){
                                i=1;

                                Toast.makeText(XiangActivity.this, "最少一个", Toast.LENGTH_SHORT).show();


                        }else{
                            shu.setText("" + i);
                        }

                    }
                });
                popupWindow.setContentView(inflate);
                popupWindow.setHeight(500);
                popupWindow.setWidth(300);
                popupWindow.setOutsideTouchable(true);

                popupWindow.showAsDropDown(jiaru, 0, 0);


            }
        });



        gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到购物车页面
                Intent intent1 = new Intent(XiangActivity.this,GouwucheActivity.class);
                startActivity(intent1);
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().unregister(this);
//        }

    }

//    @Subscribe(sticky = true)
//    public void jieshou(String str){
//
//
//        gwcshuliang.setText(str+"");
//    }

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "\t\n" +
            "2021000116677533";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "2088922929254875";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCWNR2buG/R7lfE7Ayz5jAe+GrZkKXZMm7zLV4UaFN1nf45BUIA+K1cWsOZRkuk3iqd/Y3pbOLgIRQDe9YlRGnm5jfMx2QtHKFzFEMgPQ6tpbjD8+OT9WUzpf+qBVjSULEbitYRPpQJoExukmdAh5Xcy8xQmonyeKLh4ydlibbRTYWVSn00T+mePgh/9MX8GtMvW4vjIHmVufNQVXo7sUzD3PxFL7bFMCSPXfLoHGZL96/xP0o+Eeccoi/7o1iIF+mrMjwmOBNfXSF2UYwgRhmF6dQAPqWFfz9mZhgEg8V+2mFdYa4y+8niqxoLxbLhyHxDE3k6iMy/BkpkqiEzJS/dAgMBAAECggEAFf5ZA4AR/28istbNVVoTGbXOD2U6o37a+AhgiiRsdABaRQccDwBagfdYRI0iblWJb/C8Yr1qyt6NC6WKSZKm3wQ+j2FyrZhPm4YP/hxylcJ6N1byr6ty0gkwUjjONJ1UEXBWv9el3XL3Pgl5zN0NeHLuy66VHEfFscWM8zUi66pwndbmmK9hUF8IQz324TbRgKVB2luSzaBs6G9CZ+cGZmjOiQmpaxe9aZo7qfthYCNDXvAfUftHNuGHYRzFfG5UJYLmPeLnK1sAOy3Fhj3Z4hRQVNzEQzsB5OGJ1BUWIZmq0+lATkSnl4cCCxplt/Q5Dq4npJK7P2nxnCouF0czEQKBgQDSt1NuiFsvrc5uhyhPFUTeNCKkZPxYIB9R/tJUIDXrdGbtjtEoVyVccq/kQWgZGYM2518/lgZmpIFo8QeJ1WGksBFW1eLmIHPhf6PC3x5dU+LmrJEfm+8GHJKKQxlKFxycKLl3gPdyXhItedA4MRpI6qiwafzIb1QagZwjKKszowKBgQC2fN7t0NNFvMdNFEVPwrjb6yRZXSEcoBh7TmnIVKQVFJPpUHR5LJjoTZAk8rVHcpJCIV15Qn5KhcRpffWWv9enlhk4GcXV5VKVs4EOMIZidAXLrnWu47a3bIXLtiIt986DN1KgI4gv7FuhSFED9vOJK56VhUCTrIDAoz87kwhGfwKBgQCzq3+navsdn3y6f+Jd9EvBkADN3hHQ7qrmFnqvIrv3JUyyWKl9VTlVJ7FU1nR1mmxov0ZXCIwyryhtG8AKxfg5HavbOAr9oEROzbAL9IWGnIWHnTMtGZ/ovSoyXF5O74AEozDpdf0H9rBXOFvT3gKElBn8OpNBMLQs2cERtZatMwKBgQCP3HceQf8cPgtcL3vIujhjXsckD+/3khpasuFfxaIo4DUOvJdlo4vTrrnQ9umELsUwjv8ShiHre/LwOxVtx0UqX4D4IhE9Y3CshXtbBVQbr12WSa2KtwenKE1939KoLHBIL0fa6MgHB6zgw5Lirj8lRxbYxti20koaE1fZDjt63wKBgEzmAIE8Siz1e5/42ET9EcFerWcY3RZHTRr+wzXa2nS4h/ujciglPxoYBhI6PjR6ggkXGfwQq6M13q0C30YACT4IqwwB8zhoqM0e+XfIA4RkKRq2dICRA/bKL7BdF6iuOFxDklxTJaFzUV9s/clGvuKEEWE6wQKqKu/vqU8OfRIx";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(XiangActivity.this, getString(R.string.pay_success) + payResult);
//                        //调到主页面
//                        Intent intent1 = new Intent(XiangActivity.this, HomeActivity.class);
//                        startActivity(intent1);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(XiangActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(XiangActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(XiangActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 支付宝支付业务示例
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(XiangActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务示例
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            showAlert(this, getString(R.string.error_auth_missing_partner_appid_rsa_private_target_id));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(XiangActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 获取支付宝 SDK 版本号。
     */
    public void showSdkVersion(View v) {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        showAlert(this, getString(R.string.alipay_sdk_version_is) + version);
    }


    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    private static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    private static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            sb.append(key).append("=>").append(bundle.get(key)).append("\n");
        }
        return sb.toString();
    }

    private void initView() {
        fanhui = (ImageView) findViewById(R.id.fanhui);
        sandian = (ImageView) findViewById(R.id.sandian);
        fenxiang = (ImageView) findViewById(R.id.fenxiang);
        m1 = (ImageView) findViewById(R.id.m1);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        jiaru = (Button) findViewById(R.id.jiaru);
        goumai = (Button) findViewById(R.id.goumai);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        draw = (DrawerLayout) findViewById(R.id.draw);
        lin = (LinearLayout) findViewById(R.id.lin);
        gwcshuliang = (TextView) findViewById(R.id.gwcshuliang);
        gouwuche = (ImageView) findViewById(R.id.gouwuche);
    }
}