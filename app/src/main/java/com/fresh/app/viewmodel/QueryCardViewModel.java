package com.fresh.app.viewmodel;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.databinding.FragmentQueryBinding;
import com.fresh.app.model.modelimpl.QueryCardModel;
import com.fresh.app.view.IQueryView;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryCardViewModel implements BaseLoadListener<QueryCardBean> {
    private final IQueryView mQueryView;
    private final FragmentQueryBinding mBinding;
    private final QueryCardModel queryCardModel;

    public QueryCardViewModel(IQueryView queryView, FragmentQueryBinding bind) {
        this.mQueryView=queryView;
        this.mBinding=bind;
        queryCardModel = new QueryCardModel();
    }


    @Override
    public void loadSuccess(List<QueryCardBean> list) {

    }

    @Override
    public void loadSuccess(QueryCardBean queryCardBean) {
        //得到会员卡信息
        mQueryView.getCardDataSuccessed(queryCardBean);
    }

    @Override
    public void loadFailure(String message) {

    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }

    /**
     * 获取卡片信息
     * @param card_id
     */
    public void getCardInfo(String card_id){
        queryCardModel.getCardInfo(card_id ,this);
    }

}
