package com.keyi.ouyang1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.keyi.ouyang1.fragment.CarFragment;
import com.keyi.ouyang1.fragment.HuoDanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRadioGroup;
    private FrameLayout mFrameLayout;
    private FragmentManager manager;
    private List<Fragment> fraglist;
    private String[] tags;
    private long exitTime = 0;
    private SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        mRadioGroup= (RadioGroup) findViewById(R.id.rg_main);
        mFrameLayout= (FrameLayout) findViewById(R.id.cont_layout);
        mRadioGroup.setOnCheckedChangeListener(this);
        manager =getSupportFragmentManager();
        initList();
        //默认第一页是资讯
        HuoDanFragment info = new HuoDanFragment();
        initFragment(info);
    }
    private void initList() {
        fraglist = new ArrayList<Fragment>();
        HuoDanFragment info = new HuoDanFragment();
        CarFragment forum = new CarFragment();

        fraglist.add(info);
        fraglist.add(forum);

        tags = new String[fraglist.size()];
        for(int i=0;i<fraglist.size();i++){
            tags[i] = i+"";

        }

    }
    public void initFragment(Fragment fra){
        FragmentTransaction transaction = manager.beginTransaction();
        mFrameLayout= (FrameLayout) findViewById(R.id.cont_layout);
        transaction.add(R.id.cont_layout, fra, tags[0]);
        transaction.commit();
    }

    private int i= 0;

    public void initFragment(int index){
        FragmentTransaction transaction = manager.beginTransaction();


        if(!tags[index].equals(tags[i])){
            Fragment currentFragment = manager.findFragmentByTag(tags[i]);
            Fragment clickFragment = manager.findFragmentByTag(tags[index]);
            if(currentFragment!=null){
                transaction.hide(currentFragment);
                if(clickFragment!=null){
                    transaction.show(clickFragment);
                }else{
                    transaction.add(R.id.cont_layout, fraglist.get(index),tags[index]);
                }
            }
        }

        transaction.commit();
        i=index;
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rg_main_dapei:
                //资讯
                initFragment(0);
                break;
            case R.id.rg_main_danping:
                //论坛
                initFragment(1);
                break;

            default:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}

