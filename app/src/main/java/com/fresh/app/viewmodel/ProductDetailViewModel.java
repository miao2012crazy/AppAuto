package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DetailBean;
import com.fresh.app.bean.PressureBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QrBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.IConstant;
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
        this.mDetailView=detailView;
        this.mActivityDetailBinding=fragmentDetailBinding;
        payeeModelImpl = new PayeeModelImpl();
        detailModel = new DetailModelImpl();
        detailModel.getPressureFormNet(AppConstant.product_id);
    }


    /**
     * 初始化列表
     * @param productItemBean
     * @param pressureBean
     */
    private void initRecyclerList(ProductItemBean productItemBean, PressureBean pressureBean) {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();

        recyclerList = mActivityDetailBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 3);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        
        String productId = productItemBean.getProductId();
        DetailBean detailBean0 = new DetailBean(productId,0, productItemBean.getProductName(), R.drawable.caomi, "与普通米相比，糙米中维他命，矿物质与膳食纤维含量更高","糙米",R.drawable.ic_btn_caomi,R.drawable.ic_caomi_bg,pressureBean.getPressure10(),false);
        DetailBean detailBean1 = new DetailBean(productId,1, productItemBean.getProductName(), R.drawable.peiyami, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_peiyami,R.drawable.ic_peiyami_bg,pressureBean.getPressure2(),true);
        DetailBean detailBean2 = new DetailBean(productId,2, productItemBean.getProductName(), R.drawable.jingmomi, productItemBean.getProductDetailDesc(), "立刻购买",R.drawable.ic_btn_jingmomi,R.drawable.ic_jingmomi_bg,pressureBean.getPressure3(),false);
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
    public void setProductId(String product_id, PressureBean pressureBean){
        detailModel.getDataForModel(product_id);
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();
        initRecyclerList(productItemBean,pressureBean);

    }


    public void creatFreshOrder(String message, String deviceId) {
        payeeModelImpl.creatOrder(message,deviceId);

    }
}
