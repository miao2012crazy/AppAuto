package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QrBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpConstant;
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

public class ProductDetailViewModel {
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
        DetailBean detailBean0 = new DetailBean(productId,0, productItemBean.getProductName(), R.drawable.caomi, "与普通米相比，糙米中维他命，矿物质与膳食纤维含量更高","糙米",R.drawable.ic_btn_caomi,R.drawable.ic_caomi_bg,productItemBean.getProductPress1(),false);
        DetailBean detailBean1 = new DetailBean(productId,1, productItemBean.getProductName(), R.drawable.peiyami, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_peiyami,R.drawable.ic_peiyami_bg,productItemBean.getProductPress2(),true);
        DetailBean detailBean2 = new DetailBean(productId,2, productItemBean.getProductName(), R.drawable.jingmomi, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_jingmomi,R.drawable.ic_jingmomi_bg,productItemBean.getProductPress3(),false);
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
        detailModel.getDataForModel(product_id);
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();
        initRecyclerList(productItemBean);
    }



    /**
     * 收到数据
     * @param netResponse
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void receiveData(NetResponse netResponse){
        if (netResponse.getTag().equals(HttpConstant.STATE_PRODUCT_DETAIL)){
        }else{
        }
        switch (netResponse.getTag()){
            case HttpConstant.STATE_PRODUCT_DETAIL:
                CustomApplaction.product_detail_bean = GsonUtil.GsonToBean((String) netResponse.getData(), ProductItemBean.class);
                break;
            case HttpConstant.STATE_PRODUCT_DETAIL_2:
                CustomApplaction.product_detail_bean= (ProductItemBean) netResponse.getData();
                break;
            case HttpConstant.STATE_CREAT_ORDER:
                CustomApplaction.QR_BEAN= GsonUtil.GsonToBean((String) netResponse.getData(),QrBean.class);
                EventBus.getDefault().post(new MessageEvent(10065, "3"));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 1002:
//                Log.e("miao网络数据","请求网络数据");
                break;
            case 10066:
                String message = messageEvent.getMessage();
                //创建订单
                payeeModelImpl.creatOrder(message, AppConstant.DEVICE_ID);
                break;
        }
    }

}
