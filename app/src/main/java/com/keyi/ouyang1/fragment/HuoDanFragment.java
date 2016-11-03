package com.keyi.ouyang1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.keyi.ouyang1.R;
import com.keyi.ouyang1.view.CustomViewPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/21.
 */
public class HuoDanFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{
    private CustomViewPager viewpager;
    private RadioGroup radio;
    private View view;
    private ArrayList<Fragment> mFragment;
    private FragmentPagerAdapter mAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a, container, false);
        radio= (RadioGroup) view.findViewById(R.id.rg_main);
        radio.setOnCheckedChangeListener(this);
        viewpager= (CustomViewPager) view.findViewById(R.id.view_pager);
        viewpager.setOnPageChangeListener(this);
        mFragment=new ArrayList<Fragment>();
        AllCarFragment detailsFragment=new AllCarFragment();
        YiXieHuoFragment day=new YiXieHuoFragment();
        mFragment.add(detailsFragment);
        mFragment.add(day);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mFragment.size();
            }
            @Override
            public Fragment getItem(int arg0) {
                return mFragment.get(arg0);
            }
        };
        viewpager.setAdapter(mAdapter);
        return view;
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_main_day:
                viewpager.setCurrentItem(0);
                break;
            case R.id.rb_main_month:
                viewpager.setCurrentItem(1);
                break;
            default:
                break;
        }

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radio.check(R.id.rb_main_day);
                break;
            case 1:
                radio.check(R.id.rb_main_month);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
