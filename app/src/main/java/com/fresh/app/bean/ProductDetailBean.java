package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 *
 * Created by mr.miao on 2018/5/7.
 */
@Entity
public class ProductDetailBean extends BaseObservable {


    /**
     * deviceId : 20180515_01
     * deviceProductStock : 20
     * id : 3
     * productDesc : 其香糯的口感和独特的露兜树香味享誉世界
     * productDetailDesc : 泰国香米是原产于泰国的长粒型大米，是籼米的一种。因其香糯的口感和独特的露兜树香味享誉世界。是仅次于印度香米的世界上最大宗的出口大米品种之一。
     * productId : 201805151351
     * productImg01 : 1525919372344.jpg
     * productImg02 : 1525919372344.jpg
     * productImg03 :
     * productImg04 :
     * productManage : 泰国
     * productName : 泰国香米
     * productOption :
     * productPrice : 45.5
     * productUsable : 0
     * regDate :
     * singleStorageRoomId :
     */

    private String deviceId;
    private int deviceProductStock;
    @Id(autoincrement = true)
    private long id;
    private String productDesc;
    private String productDetailDesc;
    private String productId;
    private String productImg01;
    private String productImg02;
    private String productImg03;
    private String productImg04;
    private String productManage;
    private String productName;
    private String productOption;
    private double productPrice;
    private int productUsable;
    private String regDate;
    private String singleStorageRoomId;


    @Generated(hash = 1349766707)
    public ProductDetailBean(String deviceId, int deviceProductStock, long id, String productDesc,
            String productDetailDesc, String productId, String productImg01, String productImg02,
            String productImg03, String productImg04, String productManage, String productName,
            String productOption, double productPrice, int productUsable, String regDate,
            String singleStorageRoomId) {
        this.deviceId = deviceId;
        this.deviceProductStock = deviceProductStock;
        this.id = id;
        this.productDesc = productDesc;
        this.productDetailDesc = productDetailDesc;
        this.productId = productId;
        this.productImg01 = productImg01;
        this.productImg02 = productImg02;
        this.productImg03 = productImg03;
        this.productImg04 = productImg04;
        this.productManage = productManage;
        this.productName = productName;
        this.productOption = productOption;
        this.productPrice = productPrice;
        this.productUsable = productUsable;
        this.regDate = regDate;
        this.singleStorageRoomId = singleStorageRoomId;
    }

    @Generated(hash = 764105882)
    public ProductDetailBean() {
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Bindable
    public int getDeviceProductStock() {
        return deviceProductStock;
    }

    public void setDeviceProductStock(int deviceProductStock) {
        this.deviceProductStock = deviceProductStock;
        notifyPropertyChanged(BR.deviceProductStock);
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
    @Bindable
    public String getProductDetailDesc() {
        return productDetailDesc;
    }

    public void setProductDetailDesc(String productDetailDesc) {
        this.productDetailDesc = productDetailDesc;
        notifyPropertyChanged(BR.productDetailDesc);
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
    @Bindable
    public String getProductImg02() {
        return productImg02;
    }

    public void setProductImg02(String productImg02) {
        this.productImg02 = productImg02;
        notifyPropertyChanged(BR.productImg02);
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
    @Bindable
    public String getProductManage() {
        return productManage;
    }

    public void setProductManage(String productManage) {
        this.productManage = productManage;
        notifyPropertyChanged(BR.productManage);
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

    public String getSingleStorageRoomId() {
        return singleStorageRoomId;
    }

    public void setSingleStorageRoomId(String singleStorageRoomId) {
        this.singleStorageRoomId = singleStorageRoomId;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceProductStock=" + deviceProductStock +
                ", id=" + id +
                ", productDesc='" + productDesc + '\'' +
                ", productDetailDesc='" + productDetailDesc + '\'' +
                ", productId='" + productId + '\'' +
                ", productImg01='" + productImg01 + '\'' +
                ", productImg02='" + productImg02 + '\'' +
                ", productImg03='" + productImg03 + '\'' +
                ", productImg04='" + productImg04 + '\'' +
                ", productManage='" + productManage + '\'' +
                ", productName='" + productName + '\'' +
                ", productOption='" + productOption + '\'' +
                ", productPrice=" + productPrice +
                ", productUsable=" + productUsable +
                ", regDate='" + regDate + '\'' +
                ", singleStorageRoomId='" + singleStorageRoomId + '\'' +
                '}';
    }
}
