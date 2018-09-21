package com.example.gc_hank.rxbus2study;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.fl_fragment_send, new Fragment_Send());
        addFragment(R.id.fl_fragment_receive_1, new Fragment_Receive_1());
        addFragment(R.id.fl_fragment_receive_2, new Fragment_Receive_2());
    }

    private void addFragment(int fragmentLayoutId, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragmentLayoutId, fragment);
        transaction.commit();
    }
}
