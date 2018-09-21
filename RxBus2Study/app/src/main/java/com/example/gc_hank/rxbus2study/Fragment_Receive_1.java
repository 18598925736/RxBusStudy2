package com.example.gc_hank.rxbus2study;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gc_hank.rxbus2study.bean.TestBean;
import com.example.gc_hank.rxbus2study.consts.MyConst;
import com.gwtsz.android.rxbus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 业务1的页面
 * 角色：观察者
 */
public class Fragment_Receive_1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receive_1, container, false);
        initView(root);
        return root;
    }

    public Disposable rxBusRegister;
    TextView tv_receive, tv_register, tv_unregister;

    private void initView(View root) {
        tv_receive = root.findViewById(R.id.tv_receive);
        tv_register = root.findViewById(R.id.tv_register);
        tv_unregister = root.findViewById(R.id.tv_unregister);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerRxBus();
            }
        });
        tv_unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterRxBus();
                tv_receive.setText("");
            }
        });

    }

    /**
     * 订阅消息
     */
    private void registerRxBus() {
        //在这里订阅消息
        rxBusRegister = RxBus.getInstance().register(MyConst.CODE_1, TestBean.class)
                .observeOn(AndroidSchedulers.mainThread())//修改发布者，以异步方式在指定的调度程序上执行其排放和通知，并使用缓冲大小槽的有界缓冲区。
                .subscribe(new Consumer<TestBean>() {//订阅一个发布者，并提供一个回调来处理它发出的条目。

                    @Override
                    public void accept(@NonNull TestBean bundle) {
                        parseRes(bundle);
                    }
                });
    }

    private void unregisterRxBus() {
        if (rxBusRegister != null)
            rxBusRegister.dispose();// 处理资源，操作应该是幂等的
    }

    /**
     * 解析消息
     *
     * @param bean
     */
    private void parseRes(TestBean bean) {
        String resStr = bean.id + "-" + bean.time + "-" + bean.content;
        Log.d("parseRes", "" + resStr);
        tv_receive.setText(resStr);
    }
}
