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
import com.fresh.app.bean.QRBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.listener.OnCreatOrderListener;
import com.fresh.app.model.modelimpl.DetailModelImpl;
import com.fresh.app.model.modelimpl.PayeeModelImpl;
import com.fresh.app.view.IDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductDetailViewModel implements BaseLoadListener<ProductItemBean>,OnCreatOrderListener {
    private final PayeeModelImpl payeeModelImpl;
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
        payeeModelImpl = new PayeeModelImpl();

        detailModel = new DetailModelImpl();
//        EventBus.getDefault().register(this);
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
        
        String productId = productItemBean.getProductId();
        DetailBean detailBean0 = new DetailBean(productId,0, productItemBean.getProductName(), R.drawable.caomi, "与普通米相比，糙米中维他命，矿物质与膳食纤维含量更高","糙米",R.drawable.ic_btn_caomi,R.drawable.ic_caomi_bg,false);
        DetailBean detailBean1 = new DetailBean(productId,1, productItemBean.getProductName(), R.drawable.peiyami, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_peiyami,R.drawable.ic_peiyami_bg,true);
        DetailBean detailBean2 = new DetailBean(productId,2, productItemBean.getProductName(), R.drawable.jingmomi, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_jingmomi,R.drawable.ic_jingmomi_bg,false);
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
//                Log.e("miao网络数据","请求网络数据");
                break;

            case 1003:
//                Log.e("miao查询数据库","");
//                //查询数据库
//                ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
//                List<ProductItemBean> productItemBeans = productItemBeanDao.loadAll();
//                mActivityDetailBinding.setItem(productDetailBean);
//                productview.getDataSuccessed(productItemBeans);
                break;
            case 10066:
                String message = messageEvent.getMessage();
                //创建订单

                payeeModelImpl.creatOrder(message,"20180515_01",this);

//                UIUtils.showToast(message);
                //下单
                break;
        }
    }


    @Override
    public void onCreatOrderSuccessed(QRBean qrBean) {
        UIUtils.showToast(qrBean.getWechat_url());
        CustomApplaction.QR_BEAN=qrBean;
        //打开支付页面
        EventBus.getDefault().post(new MessageEvent(10065, "3"));
    }

    @Override
    public void onCreatOrderFailed(String err_msg) {
        UIUtils.showToast("下单失败");
    }
}
