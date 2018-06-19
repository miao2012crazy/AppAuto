package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DetailBean;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.model.modelimpl.DetailModelImpl;
import com.fresh.app.view.IDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductDetailViewModel implements BaseLoadListener<ProductItemBean>{
    private ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();

    public  IDetailView mDetailView;
    public FragmentDetailBinding mActivityDetailBinding;
    private final DetailModelImpl detailModel;
    private RecyclerView recyclerList;
    private BindingAdapter adapter;
    private List<BindingAdapterItem> mainList;
    private List<DetailBean> homeBeans;

    public ProductDetailViewModel(IDetailView detailView, FragmentDetailBinding fragmentDetailBinding) {
        EventBus.getDefault().register(this);
        this.mDetailView=detailView;
        this.mActivityDetailBinding=fragmentDetailBinding;
        detailModel = new DetailModelImpl();

    }

    /**
     * 初始化列表
     * @param productItemBean
     */
    private void initRecyclerList(ProductItemBean productItemBean) {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();

        recyclerList = mActivityDetailBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 3);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);

        DetailBean detailBean0 = new DetailBean(0, productItemBean.getProductName(), R.drawable.caomi, "与普通米相比，糙米中维他命，矿物质与膳食纤维含量更高", "立刻购买");
        DetailBean detailBean1 = new DetailBean(1, productItemBean.getProductName(), R.drawable.peiyami, productItemBean.getProductDetailDesc(), "立刻购买");
        DetailBean detailBean2 = new DetailBean(2, productItemBean.getProductName(), R.drawable.jingmomi, productItemBean.getProductDetailDesc(), "立刻购买");
        detailBean1.setVisiable(true);
        homeBeans.add(detailBean0);
        homeBeans.add(detailBean1);
        homeBeans.add(detailBean2);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);

    }


    /**
     * 设置product_id
     */
    public void setProductId(String product_id){
        detailModel.getDataForModel(product_id,this);
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();

        initRecyclerList(productItemBean);



    }


    @Override
    public void loadSuccess(List<ProductItemBean> list) {

    }

    @Override
    public void loadSuccess(ProductItemBean productDetailBean) {
//        mActivityDetailBinding.setItem(productDetailBean);
        CustomApplaction.product_detail_bean=productDetailBean;
    }

    @Override
    public void loadFailure(String message) {
        mDetailView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mDetailView.loadStart();
    }

    @Override
    public void loadComplete() {
        mDetailView.loadComplete();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 1002:
                Log.e("miao网络数据","请求网络数据");

                break;

            case 1003:
//                Log.e("miao查询数据库","");
//
//                //查询数据库
//                ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
//                List<ProductItemBean> productItemBeans = productItemBeanDao.loadAll();
//                mActivityDetailBinding.setItem(productDetailBean);
//                productview.getDataSuccessed(productItemBeans);
                break;
        }
    }


}
