package com.fresh.app.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.CardHistoryItemBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.bean.SMSBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentQueryBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.model.modelimpl.QueryCardModel;
import com.fresh.app.view.IQueryView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryCardViewModel  {
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
            queryCardModel.getSmsCode(tel);


        });
        mBinding.layoutAttach.btnConfirm.setOnClickListener(v -> {
            String s = mBinding.layoutAttach.etCode.getText().toString();
            if (s.equals("")){
                UIUtils.showToast("请输入验证码！");
                return;
            }

            queryCardModel.updateCardInfo(s,msgId,CustomApplaction.MEMBER_ID,tel,"20180515_01");



        });
    }



    private void initCardInfo() {
        if (CustomApplaction.MEMBER_ID.equals("")) return;
        queryCardModel.getCardInfo(CustomApplaction.MEMBER_ID);
    }


    /**
     * 获取卡片信息
     *
     * @param card_id
     */
    public void getCardInfo(String card_id) {
        queryCardModel.getCardHistory(card_id);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_CARD_INFO:
                QueryCardBean queryCardBean = GsonUtil.GsonToBean((String) netResponse.getData(), QueryCardBean.class);
                mBinding.textView4.setText(String.valueOf(queryCardBean.getMembershipBalance()));
                mBinding.layoutAttach.etMemberCard.setText(String.valueOf(CustomApplaction.MEMBER_ID));
                mBinding.layoutAttach.etMemberCard.setEnabled(false);
                mBinding.layoutAttach.tvUpdate.setVisibility(queryCardBean.getMembershipTel().equals("") ? View.GONE : View.VISIBLE);
                mBinding.layoutAttach.etTel.setText(queryCardBean.getMembershipTel());
                mBinding.layoutAttach.etTel.setEnabled(queryCardBean.getMembershipTel().equals(""));
                mBinding.layoutAttach.tvUpdate.setOnClickListener(v -> {
                    mBinding.layoutAttach.etTel.setEnabled(true);
                });
                break;

            case HttpConstant.STATE_CARD_HISTORY:
                List<CardHistoryItemBean> cardHistoryItemBeans = GsonUtil.jsonToList((String) netResponse.getData(), CardHistoryItemBean.class);
                //得到会员卡信息
                mainList.addAll(cardHistoryItemBeans);
                adapter.setItems(mainList);
                break;

            case HttpConstant.STATE_GET_SMS_CODE:
                SMSBean smsBean = GsonUtil.GsonToBean((String) netResponse.getData(), SMSBean.class);
                msgId=smsBean.getMsgID();
                break;

            case HttpConstant.STATE_UPDATE_CARD_INFO:
                mQueryView.setlayout(0);
                break;

        }
    }
}
