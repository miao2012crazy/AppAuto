package com.fresh.app.viewmodel;

import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.CardHistoryItem2Bean;
import com.fresh.app.bean.CardHistoryItemBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.FragmentQueryBinding;
import com.fresh.app.listener.OnCardHistoryListener;
import com.fresh.app.listener.OnGetSmsCodeListener;
import com.fresh.app.listener.OnLoadCardInfoListener;
import com.fresh.app.listener.OnUpdateCardInfoListener;
import com.fresh.app.model.modelimpl.QueryCardModel;
import com.fresh.app.view.IQueryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryCardViewModel implements OnCardHistoryListener, OnLoadCardInfoListener, OnGetSmsCodeListener, OnUpdateCardInfoListener {
    private final IQueryView mQueryView;
    private final FragmentQueryBinding mBinding;
    private final QueryCardModel queryCardModel;
    private BindingAdapter adapter;
    private List<BindingAdapterItem> mainList;
    private String msgId="";
    private String tel;


    public QueryCardViewModel(IQueryView queryView, FragmentQueryBinding bind) {
        this.mQueryView = queryView;
        this.mBinding = bind;
        queryCardModel = new QueryCardModel();
        initCardInfo();
        initCode();
    }

    private void initCode() {
        mBinding.layoutAttach.tvGetCode.setOnClickListener(v -> {
            tel = mBinding.layoutAttach.etTel.getText().toString();
            if (TextUtils.isEmpty(tel)){
                UIUtils.showToast("请输入手机号！");
                return;
            }
            mQueryView.startTimer();
            queryCardModel.getSmsCode(tel,this);


        });
        mBinding.layoutAttach.btnConfirm.setOnClickListener(v -> {
            String s = mBinding.layoutAttach.etCode.getText().toString();
            if (s.equals("")){
                UIUtils.showToast("请输入验证码！");
                return;
            }

            queryCardModel.updateCardInfo(s,msgId,CustomApplaction.MEMBER_ID,tel,"20180515_01",this);



        });
    }



    private void initCardInfo() {
        if (CustomApplaction.MEMBER_ID.equals("")) return;
        queryCardModel.getCardInfo(CustomApplaction.MEMBER_ID, this);
    }


    /**
     * 获取卡片信息
     *
     * @param card_id
     */
    public void getCardInfo(String card_id) {
        queryCardModel.getCardHistory(card_id, this);
    }

    @Override
    public void getCardHistorySuccess(List<CardHistoryItemBean> cardHistoryBeans) {
        //得到会员卡信息
        mainList.addAll(cardHistoryBeans);
        adapter.setItems(mainList);
    }


    /**
     * 初始化会员卡消费充值历史记录
     */
    public void initHistory() {
        RecyclerView recyclerView = mBinding.recyclerList.recyclerList;
        mainList = new ArrayList<>();
        LinearLayoutManager ms = new LinearLayoutManager(UIUtils.getContext());
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BindingAdapter();
        recyclerView.setLayoutManager(ms);
        recyclerView.setAdapter(adapter);
//        adapter.setItems(mainList);
        if (CustomApplaction.MEMBER_ID.equals("")) {
            UIUtils.showToast("请刷卡！");
        }
        getCardInfo(CustomApplaction.MEMBER_ID);
    }


    @Override
    public void onSuccessed(QueryCardBean queryCardBean) {
        mBinding.textView4.setText(String.valueOf(queryCardBean.getMembershipBalance()));
        mBinding.layoutAttach.etMemberCard.setText(String.valueOf(CustomApplaction.MEMBER_ID));
        mBinding.layoutAttach.etMemberCard.setEnabled(false);
        mBinding.layoutAttach.tvUpdate.setVisibility(queryCardBean.getMembershipTel().equals("") ? View.GONE : View.VISIBLE);
        mBinding.layoutAttach.etTel.setText(queryCardBean.getMembershipTel());
        mBinding.layoutAttach.etTel.setEnabled(queryCardBean.getMembershipTel().equals(""));
        mBinding.layoutAttach.tvUpdate.setOnClickListener(v -> {
            mBinding.layoutAttach.etTel.setEnabled(true);
        });
    }

    @Override
    public void onFailed(String err_msg) {

    }

    @Override
    public void getSmsCodeSuccessed(String msg_id) {
        //获取验证码成功
        msgId=msg_id;
        UIUtils.showToast("验证码获取成功");

    }

    @Override
    public void getSmsCodeFailed(String err_msg) {





    }

    @Override
    public void OnUpdateCardSuccessed(boolean result) {
        UIUtils.showToast("更新成功！");
        mQueryView.setlayout(0);
    }

    @Override
    public void OnUpdateCardFailed(String err_msg) {

    }
}
