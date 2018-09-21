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
import com.example.gc_hank.rxbus2study.bean.TestBean2;
import com.example.gc_hank.rxbus2study.consts.MyConst;
import com.gwtsz.android.rxbus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 业务2的接收界面;
 * 角色：观察者
 */
public class Fragment_Receive_2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receive_2, container, false);
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
        rxBusRegister = RxBus.getInstance().register(MyConst.CODE_2, TestBean2.class)
                .observeOn(AndroidSchedulers.mainThread())//保证收到消息是在主线程中
                .rebatchRequests(1)//难道这就是传说中的，
                .subscribe(new Consumer<TestBean2>() {//这个Consumer,消费者，事件的消费者? 消费者里面绑定了一个泛型，泛型要设置成JavaBean.class

                    @Override
                    public void accept(@NonNull TestBean2 bean) {
                        parseRes(bean);
                    }
                });
    }

    private void unregisterRxBus() {
        if (rxBusRegister != null && !rxBusRegister.isDisposed())
            rxBusRegister.dispose();
    }

    /**
     * 解析消息
     *
     * @param bean
     */
    private void parseRes(TestBean2 bean) {
        String resStr = bean.id2 + "-" + bean.time2 + "-" + bean.content2;
        Log.d("parseRes", "" + resStr);
        tv_receive.setText(resStr);
    }
}
