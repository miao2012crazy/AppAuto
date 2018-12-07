package com.fresh.app.commonUtil;

import android.support.v4.app.Fragment;

import com.fresh.app.base.BaseFragment;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.view.fragment.HomeFragment;
import com.fresh.app.view.fragment.PayeeFragment;
import com.fresh.app.view.fragment.ProductDetailFragment;
import com.fresh.app.view.fragment.ProductFragment;
import com.fresh.app.view.fragment.ProgressingFragment;
import com.fresh.app.view.fragment.RechargeFragment;
import com.fresh.app.view.fragment.ReserveFragment;
import com.fresh.app.view.fragment.QueryFragment;

import org.greenrobot.eventbus.EventBus;

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
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"请选择购买商品！"));

                    break;
                case 2:
                    fragment = new ProductDetailFragment();
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"请选择碾米精度！"));

                    break;
                case 3:
                    fragment = new PayeeFragment();
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"请支付！您可以使用会员卡,微信，和支付宝扫码的方式进行支付！"));

                    break;
                case 4:
                    fragment = new ProgressingFragment();
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"设备初始化中，请您耐心等待！"));

                    break;
                case 5:
                    fragment = new RechargeFragment();
                    break;
                case 6:
                    fragment = new ReserveFragment();
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"请选择您要预定的商品！"));
                    break;
                case 7:
                    fragment=new QueryFragment();
                    break;
            }
        }
        return fragment;
    }
}
