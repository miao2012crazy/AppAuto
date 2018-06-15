package com.fresh.app.commonUtil;

import android.support.v4.app.Fragment;

import com.fresh.app.base.BaseFragment;
import com.fresh.app.view.fragment.HomeFragment;
import com.fresh.app.view.fragment.ProductDetailFragment;
import com.fresh.app.view.fragment.ProductFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr.miao on 2018/6/15.
 */

public class FragmentFactory {
    private static Map<Integer, BaseFragment> mFragments = new HashMap<>();
    public static Fragment getFragment(int position) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);  //在集合中取出来Fragment
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new ProductFragment();
                    break;
                case 2:
                    fragment = new ProductDetailFragment();
                    break;
            }
        }
        return fragment;
    }
}
