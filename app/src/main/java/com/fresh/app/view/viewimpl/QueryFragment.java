package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentQueryBinding;
import com.fresh.app.view.IQueryView;
import com.fresh.app.viewmodel.QueryCardViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryFragment extends BaseFragment implements IQueryView {

    private FragmentQueryBinding bind;
    private QueryCardViewModel queryCardViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         bind = DataBindingUtil.inflate(inflater,R.layout.fragment_query, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
//        bind = DataBindingUtil.bind(view);
        queryCardViewModel = new QueryCardViewModel(this, bind);
        EventBus.getDefault().post(new MessageEvent(1005,"123"));
    }


    @Override
    public void getCardDataSuccessed(QueryCardBean cardBean) {
        bind.setItem(cardBean);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCard(MessageEvent messageEvent){
        if (messageEvent.getCode()==1005){
            queryCardViewModel.getCardInfo(messageEvent.getMessage());
        }

    }


}
