package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by mr.miao on 2018/5/8.
 */
@Entity
public class ProductItemBean extends BaseObservable implements BindingAdapterItem {

    /**
     * id : 1
     * productDesc : 明月几时有，把酒问青天
     * productId : 201805071207
     * productImg01 :
     * productImg02 :
     * productImg03 :
     * productImg04 :
     * productManage : 北京
     * productName : 新型产品第一号
     * productOption :
     * productPrice : 20.5
     * productStock : 1000
     * productUsable : 0
     * regDate :
     */
    @Id(autoincrement = true)
    private long id;
    private String productDesc;
    private String productId;
    private String productImg01;
    private String productImg02;
    private String productImg03;
    private String productImg04;
    private String productManage;
    private String productName;
    private String productOption;
    private double productPrice;
    private int productStock;
    private int productUsable;
    private String regDate;
    /**
     * deviceProductStock : 10
     * productPrice : 15.5
     * singleDeviceId : 20180510_01
     * singleStorageRoomId :
     */

    private int deviceProductStock;
    private String singleDeviceId;
    private String singleStorageRoomId;
    /**
     * deviceId : 20180515_01
     * id : 1
     * productDetailDesc : 作为宫庭御膳米，优良的品种，晶莹如珠、米香浓郁，已成为施名中外的“名产”。它含有人体所需的各种氨基酸、脂肪酸、矿物质、较多的纤维素和丰富的日族维生素，不仅好吃，而且营养丰富。
     * productPrice : 25.5
     */

    private String productDetailDesc;
    /**
     * id : 1
     * deviceId : 20180515_01
     * productPrice : 25.5
     * productPress1 : 50
     * productPress2 : 70
     * productPress3 : 90
     */

    private int productPress1;
    private int productPress2;
    private int productPress3;


    @Generated(hash = 959084042)
    public ProductItemBean(long id, String productDesc, String productId, String productImg01,
            String productImg02, String productImg03, String productImg04, String productManage,
            String productName, String productOption, double productPrice, int productStock, int productUsable,
            String regDate, int deviceProductStock, String singleDeviceId, String singleStorageRoomId,
            String productDetailDesc, int productPress1, int productPress2, int productPress3) {
        this.id = id;
        this.productDesc = productDesc;
        this.productId = productId;
        this.productImg01 = productImg01;
        this.productImg02 = productImg02;
        this.productImg03 = productImg03;
        this.productImg04 = productImg04;
        this.productManage = productManage;
        this.productName = productName;
        this.productOption = productOption;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productUsable = productUsable;
        this.regDate = regDate;
        this.deviceProductStock = deviceProductStock;
        this.singleDeviceId = singleDeviceId;
        this.singleStorageRoomId = singleStorageRoomId;
        this.productDetailDesc = productDetailDesc;
        this.productPress1 = productPress1;
        this.productPress2 = productPress2;
        this.productPress3 = productPress3;
    }

    @Generated(hash = 1206367571)
    public ProductItemBean() {
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
        notifyPropertyChanged(BR.productDesc);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImg01() {
        return productImg01;
    }

    public void setProductImg01(String productImg01) {
        this.productImg01 = productImg01;
    }

    public String getProductImg02() {
        return productImg02;
    }

    public void setProductImg02(String productImg02) {
        this.productImg02 = productImg02;
    }

    public String getProductImg03() {
        return productImg03;
    }

    public void setProductImg03(String productImg03) {
        this.productImg03 = productImg03;
    }

    public String getProductImg04() {
        return productImg04;
    }

    public void setProductImg04(String productImg04) {
        this.productImg04 = productImg04;
    }

    public String getProductManage() {
        return productManage;
    }

    public void setProductManage(String productManage) {
        this.productManage = productManage;
    }

    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }

    public String getProductOption() {
        return productOption;
    }

    public void setProductOption(String productOption) {
        this.productOption = productOption;
    }
    @Bindable
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
        notifyPropertyChanged(BR.productPrice);
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getProductUsable() {
        return productUsable;
    }

    public void setProductUsable(int productUsable) {
        this.productUsable = productUsable;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "ProductItemBean{" +
                "id=" + id +
                ", productDesc='" + productDesc + '\'' +
                ", productId='" + productId + '\'' +
                ", productImg01='" + productImg01 + '\'' +
                ", productImg02='" + productImg02 + '\'' +
                ", productImg03='" + productImg03 + '\'' +
                ", productImg04='" + productImg04 + '\'' +
                ", productManage='" + productManage + '\'' +
                ", productName='" + productName + '\'' +
                ", productOption='" + productOption + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productUsable=" + productUsable +
                ", regDate='" + regDate + '\'' +
                ", deviceProductStock=" + deviceProductStock +
                ", singleDeviceId='" + singleDeviceId + '\'' +
                ", singleStorageRoomId='" + singleStorageRoomId + '\'' +
                ", productDetailDesc='" + productDetailDesc + '\'' +
                '}';
    }

    @Override
    public int getViewType() {
        return R.layout.item_main_product;
    }
    @Bindable
    public int getDeviceProductStock() {
        return deviceProductStock;
    }

    public void setDeviceProductStock(int deviceProductStock) {
        this.deviceProductStock = deviceProductStock;
        notifyPropertyChanged(BR.deviceProductStock);
    }


    public String getSingleDeviceId() {
        return singleDeviceId;
    }

    public void setSingleDeviceId(String singleDeviceId) {
        this.singleDeviceId = singleDeviceId;
    }

    public String getSingleStorageRoomId() {
        return singleStorageRoomId;
    }

    public void setSingleStorageRoomId(String singleStorageRoomId) {
        this.singleStorageRoomId = singleStorageRoomId;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Bindable
    public String getProductDetailDesc() {
        return productDetailDesc;
    }

    public void setProductDetailDesc(String productDetailDesc) {
        this.productDetailDesc = productDetailDesc;
        notifyPropertyChanged(BR.productDetailDesc);
    }

    public int getProductPress1() {
        return productPress1;
    }

    public void setProductPress1(int productPress1) {
        this.productPress1 = productPress1;
    }

    public int getProductPress2() {
        return productPress2;
    }

    public void setProductPress2(int productPress2) {
        this.productPress2 = productPress2;
    }

    public int getProductPress3() {
        return productPress3;
    }

    public void setProductPress3(int productPress3) {
        this.productPress3 = productPress3;
    }
}