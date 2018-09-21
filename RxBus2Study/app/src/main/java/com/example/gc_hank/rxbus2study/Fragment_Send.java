package com.example.gc_hank.rxbus2study;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gc_hank.rxbus2study.bean.TestBean;
import com.example.gc_hank.rxbus2study.bean.TestBean2;
import com.example.gc_hank.rxbus2study.consts.MyConst;
import com.gwtsz.android.rxbus.RxBus;

/**
 * 更新消息的界面
 *
 */
public class Fragment_Send extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        initView(root);
        return root;
    }

    TextView tv_send, tv_send2;

    Handler mHandler = new Handler();

    int c = 0;

    private void initView(View root) {
        tv_send = root.findViewById(R.id.tv_send);
        tv_send2 = root.findViewById(R.id.tv_send2);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里发送消息
                tv_send.setText("testBean1-发送中...");

                new Thread() {
                    @Override
                    public void run() {
                        TestBean testBean = new TestBean();
                        testBean.id = "id-" + c;
                        testBean.time = "time-" + c;
                        testBean.content = "content-" + c++;
                        SystemClock.sleep(500);
                        RxBus.getInstance().post(MyConst.CODE_1, testBean);//这个testBean，是被观察者
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_send.setText("testBean1 - 发送完毕");
                            }
                        });
                    }
                }.start();

            }
        });

        tv_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里发送消息
                tv_send2.setText("testBean2-发送中...");

                new Thread() {
                    @Override
                    public void run() {
                        TestBean2 testBean2 = new TestBean2();
                        testBean2.id2 = "id-" + c;
                        testBean2.time2 = "time-" + c;
                        testBean2.content2 = "content-" + c++;
                        SystemClock.sleep(500);
                        RxBus.getInstance().post(MyConst.CODE_2, testBean2);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_send2.setText("testBean2-发送完毕");
                                }
                        });
                    }
                }.start();

            }
        });


    }
}
