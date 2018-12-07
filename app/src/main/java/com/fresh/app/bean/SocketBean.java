package com.fresh.app.bean;

import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.StringUtils;

import java.util.Arrays;

/**
 * Created by mr.miao on 2018/5/31.
 */

public class SocketBean {

    //第一个字节
    boolean b0 = false;
    boolean b0_1 = false;
    boolean b0_2 = false;
    boolean b0_3 = false;
    boolean b0_4 = false;
    boolean b0_5 = false;
    boolean b0_6 = false;
    boolean b0_7 = false;
    boolean b1_0 = false;
    boolean b1_1 = false;
    boolean b1_2 = false;
    boolean b1_3 = false;
    boolean b1_4 = false;
    boolean b1_5 = false;
    boolean b1_6 = false;
    boolean b1_7 = false;

    //第二个字节
    boolean b2_0 = false;
    boolean b2_1 = false;
    boolean b2_2 = false;
    boolean b2_3 = false;
    boolean b2_4 = false;
    boolean b2_5 = false;
    boolean b2_6 = false;
    boolean b2_7 = false;
    boolean b3_0 = false;
    boolean b3_1 = false;
    boolean b3_2 = false;
    boolean b3_3 = false;
    boolean b3_4 = false;
    boolean b3_5 = false;
    boolean b3_6 = false;
    boolean b3_7 = false;
    //第三个字节
    boolean b4_0 = false;//出米信号
    boolean b4_1 = false;
    boolean b4_2 = false;
    boolean b4_3 = false;
    boolean b4_4 = false;
    boolean b4_5 = false;
    boolean b4_6 = false;
    boolean b4_7 = false;
    boolean b5_0 = false;
    boolean b5_1 = false;
    boolean b5_2 = false;
    boolean b5_3 = false;
    boolean b5_4 = false;
    boolean b5_5 = false;
    boolean b5_6 = false;
    boolean b5_7= false;


    boolean b6_0=false;
    boolean b6_1=false;
    boolean b6_2=false;
    boolean b6_3=false;
    boolean b6_4=false;
    boolean b6_5=false;
    boolean b6_6=false;
    boolean b6_7=false;



    boolean b8_0=false;
    boolean b8_1=false;
    boolean b8_2=false;
    boolean b8_3=false;
    boolean b8_4=false;
    boolean b8_5=false;
    boolean b8_6=false;
    boolean b8_7=false;





    public boolean isB0() {
        return b0;
    }

    public void setB0(boolean b0) {
        this.b0 = b0;
    }

    public boolean isB0_1() {
        return b0_1;
    }

    public void setB0_1(boolean b0_1) {
        this.b0_1 = b0_1;
    }

    public boolean isB0_2() {
        return b0_2;
    }

    public void setB0_2(boolean b0_2) {
        this.b0_2 = b0_2;
    }

    public boolean isB0_3() {
        return b0_3;
    }

    public void setB0_3(boolean b0_3) {
        this.b0_3 = b0_3;
    }

    public boolean isB0_4() {
        return b0_4;
    }

    public void setB0_4(boolean b0_4) {
        this.b0_4 = b0_4;
    }

    public boolean isB0_5() {
        return b0_5;
    }

    public void setB0_5(boolean b0_5) {
        this.b0_5 = b0_5;
    }

    public boolean isB0_6() {
        return b0_6;
    }

    public void setB0_6(boolean b0_6) {
        this.b0_6 = b0_6;
    }

    public boolean isB0_7() {
        return b0_7;
    }

    public void setB0_7(boolean b0_7) {
        this.b0_7 = b0_7;
    }

    public boolean isB1_0() {
        return b1_0;
    }

    public void setB1_0(boolean b1_0) {
        this.b1_0 = b1_0;
    }

    public boolean isB1_1() {
        return b1_1;
    }

    public void setB1_1(boolean b1_1) {
        this.b1_1 = b1_1;
    }

    public boolean isB1_2() {
        return b1_2;
    }

    public void setB1_2(boolean b1_2) {
        this.b1_2 = b1_2;
    }

    public boolean isB1_3() {
        return b1_3;
    }

    public void setB1_3(boolean b1_3) {
        this.b1_3 = b1_3;
    }

    public boolean isB1_4() {
        return b1_4;
    }

    public void setB1_4(boolean b1_4) {
        this.b1_4 = b1_4;
    }

    public boolean isB1_5() {
        return b1_5;
    }

    public void setB1_5(boolean b1_5) {
        this.b1_5 = b1_5;
    }

    public boolean isB1_6() {
        return b1_6;
    }

    public void setB1_6(boolean b1_6) {
        this.b1_6 = b1_6;
    }

    public boolean isB1_7() {
        return b1_7;
    }

    public void setB1_7(boolean b1_7) {
        this.b1_7 = b1_7;
    }

    public boolean isB2_0() {
        return b2_0;
    }

    public void setB2_0(boolean b2_0) {
        this.b2_0 = b2_0;
    }

    public boolean isB2_1() {
        return b2_1;
    }

    public void setB2_1(boolean b2_1) {
        this.b2_1 = b2_1;
    }

    public boolean isB2_2() {
        return b2_2;
    }

    public void setB2_2(boolean b2_2) {
        this.b2_2 = b2_2;
    }

    public boolean isB2_3() {
        return b2_3;
    }

    public void setB2_3(boolean b2_3) {
        this.b2_3 = b2_3;
    }

    public boolean isB2_4() {
        return b2_4;
    }

    public void setB2_4(boolean b2_4) {
        this.b2_4 = b2_4;
    }

    public boolean isB2_5() {
        return b2_5;
    }

    public void setB2_5(boolean b2_5) {
        this.b2_5 = b2_5;
    }

    public boolean isB2_6() {
        return b2_6;
    }

    public void setB2_6(boolean b2_6) {
        this.b2_6 = b2_6;
    }

    public boolean isB2_7() {
        return b2_7;
    }

    public void setB2_7(boolean b2_7) {
        this.b2_7 = b2_7;
    }

    public boolean isB3_0() {
        return b3_0;
    }

    public void setB3_0(boolean b3_0) {
        this.b3_0 = b3_0;
    }

    public boolean isB3_1() {
        return b3_1;
    }

    public void setB3_1(boolean b3_1) {
        this.b3_1 = b3_1;
    }

    public boolean isB3_2() {
        return b3_2;
    }

    public void setB3_2(boolean b3_2) {
        this.b3_2 = b3_2;
    }

    public boolean isB3_3() {
        return b3_3;
    }

    public void setB3_3(boolean b3_3) {
        this.b3_3 = b3_3;
    }

    public boolean isB3_4() {
        return b3_4;
    }

    public void setB3_4(boolean b3_4) {
        this.b3_4 = b3_4;
    }

    public boolean isB3_5() {
        return b3_5;
    }

    public void setB3_5(boolean b3_5) {
        this.b3_5 = b3_5;
    }

    public boolean isB3_6() {
        return b3_6;
    }

    public void setB3_6(boolean b3_6) {
        this.b3_6 = b3_6;
    }

    public boolean isB3_7() {
        return b3_7;
    }

    public void setB3_7(boolean b3_7) {
        this.b3_7 = b3_7;
    }

    public boolean isB4_0() {
        return b4_0;
    }

    public void setB4_0(boolean b4_0) {
        this.b4_0 = b4_0;
    }

    public boolean isB4_1() {
        return b4_1;
    }

    public void setB4_1(boolean b4_1) {
        this.b4_1 = b4_1;
    }

    public boolean isB4_2() {
        return b4_2;
    }

    public void setB4_2(boolean b4_2) {
        this.b4_2 = b4_2;
    }

    public boolean isB4_3() {
        return b4_3;
    }

    public void setB4_3(boolean b4_3) {
        this.b4_3 = b4_3;
    }

    public boolean isB4_4() {
        return b4_4;
    }

    public void setB4_4(boolean b4_4) {
        this.b4_4 = b4_4;
    }

    public boolean isB4_5() {
        return b4_5;
    }

    public void setB4_5(boolean b4_5) {
        this.b4_5 = b4_5;
    }

    public boolean isB4_6() {
        return b4_6;
    }

    public void setB4_6(boolean b4_6) {
        this.b4_6 = b4_6;
    }

    public boolean isB4_7() {
        return b4_7;
    }

    public void setB4_7(boolean b4_7) {
        this.b4_7 = b4_7;
    }

    public boolean isB5_0() {
        return b5_0;
    }

    public void setB5_0(boolean b5_0) {
        this.b5_0 = b5_0;
    }

    public boolean isB5_1() {
        return b5_1;
    }

    public void setB5_1(boolean b5_1) {
        this.b5_1 = b5_1;
    }

    public boolean isB5_2() {
        return b5_2;
    }

    public void setB5_2(boolean b5_2) {
        this.b5_2 = b5_2;
    }

    public boolean isB5_3() {
        return b5_3;
    }

    public void setB5_3(boolean b5_3) {
        this.b5_3 = b5_3;
    }

    public boolean isB5_4() {
        return b5_4;
    }

    public void setB5_4(boolean b5_4) {
        this.b5_4 = b5_4;
    }

    public boolean isB5_5() {
        return b5_5;
    }

    public void setB5_5(boolean b5_5) {
        this.b5_5 = b5_5;
    }

    public boolean isB5_6() {
        return b5_6;
    }

    public void setB5_6(boolean b5_6) {
        this.b5_6 = b5_6;
    }

    public boolean isB5_7() {
        return b5_7;
    }

    public void setB5_7(boolean b5_7) {
        this.b5_7 = b5_7;
    }

    /**
     *
     * @param pressure 压力
     * @param pressure2
     * @param pressure_cha2
     * @param pressure3
     * @param pressure_cha3
     * @return  byte数组
     */
    public byte[] getBinary(String pressure, String pressure_cha, String pressure2, String pressure_cha2, String pressure3, String pressure_cha3){

        boolean[] bool0_0={b0_7,b0_6,b0_5,b0_4,b0_3,b0_2,b0_1,b0};
        boolean[] bool0_1={b1_7,b1_6,b1_5,b1_4,b1_3,b1_2,b1_1,b1_0};


        boolean[] bool1_0={ b2_7,b2_6,b2_5,b2_4,b2_3,b2_2,b2_1,b2_0};
        boolean[] bool1_1={ b3_7,b3_6,b3_5,b3_4,b3_3,b3_2,b3_1,b3_0};



        boolean[] bool2_0={ b4_7,b4_6,b4_5,b4_4,b4_3,b4_2,b4_1,b4_0};
        boolean[] bool2_1={b5_7,b5_6,b5_5,b5_4,b5_3,b5_2,b5_1,b5_0};

        boolean[] bool3_0={b6_7,b6_6,b6_5,b6_4,b6_3,b6_2,b6_1,b6_0};


        boolean[] bool4_0={b8_7,b8_6,b8_5,b8_4,b8_3,b8_2,b8_1,b8_0};

        byte aByte0_0 = StringUtils.getByte(bool0_0);
        byte aByte0_1 = StringUtils.getByte(bool0_1);
        byte aByte1_0 = StringUtils.getByte(bool1_0);
        byte aByte1_1 = StringUtils.getByte(bool1_1);
        byte aByte2_0 = StringUtils.getByte(bool2_0);
        byte aByte2_1 = StringUtils.getByte(bool2_1);
        byte aByte3_0 = StringUtils.getByte(bool3_0);
        byte aByte4_0 = StringUtils.getByte(bool4_0);

        byte[] byte11={aByte4_0};

        byte[] barr={aByte0_0,aByte0_1,aByte1_0,aByte1_1,aByte2_0,aByte2_1,aByte3_0};

        //一号碾米电机压力设定值转换
        String s = StringUtils.convertDecToHexString(pressure);
        //转换为2进制
        byte[] bytes = StringUtils.hexStringToBytes(s);
//        //合并
//        byte[] bytes1 = StringUtils.byteMerger(barr, bytes);


        //一号碾米电机压力设定值波动范围
        String s8 = StringUtils.convertDecToHexString(pressure_cha);
        //2进制转换
        byte[] bytes8 = StringUtils.hexStringToBytes(s8);

        byte[] bytes88 = StringUtils.byteMerger(byte11, bytes8);

//        byte[] bytes888 = StringUtils.byteMerger( bytes1,bytes88);


        //二号碾米电机压力设定值
        String s2 = StringUtils.convertDecToHexString(pressure2);
        //转换为2进制
        byte[] bytes2 = StringUtils.hexStringToBytes(s2);
        //合并
//        byte[] bytes22 = StringUtils.byteMerger(bytes888, bytes2);



        //2号碾米电机压力设定值波动范围
        String s2_sub = StringUtils.convertDecToHexString(pressure_cha2);
        //2进制转换
        byte[] bytes2_sub = StringUtils.hexStringToBytes(s2_sub);

        byte[] bytes22_sub = StringUtils.byteMerger(byte11, bytes2_sub);

//        byte[] bytes222_sub = StringUtils.byteMerger(bytes22, bytes22_sub);




        //二号碾米电机压力设定值
        String s3 = StringUtils.convertDecToHexString(pressure3);
        //转换为2进制
        byte[] bytes3 = StringUtils.hexStringToBytes(s3);
        //合并
//        byte[] bytes33 = StringUtils.byteMerger(bytes222_sub, bytes3);



        //2号碾米电机压力设定值波动范围
        String s3_sub = StringUtils.convertDecToHexString(pressure_cha3);
        //2进制转换
        byte[] bytes3_sub = StringUtils.hexStringToBytes(s3_sub);

        byte[] bytes33_sub = StringUtils.byteMerger(byte11, bytes3_sub);

//        byte[] bytes333_sub = StringUtils.byteMerger(bytes33, bytes33_sub);

        byte[] bytes1 = StringUtils.byteMerger(barr, bytes);
        byte[] bytes1_sub = StringUtils.byteMerger(bytes1, bytes88);

        byte[] bytes1_sub1 = StringUtils.byteMerger(bytes1_sub, byte11);


        byte[] bytes22 = StringUtils.byteMerger(bytes1_sub1, bytes2);
        byte[] bytes222_sub = StringUtils.byteMerger(bytes22, bytes22_sub);

        byte[] bytes2_sub2 = StringUtils.byteMerger(bytes222_sub, byte11);


        byte[] bytes333 = StringUtils.byteMerger(bytes2_sub2, bytes3);
        byte[] bytes333_sub = StringUtils.byteMerger(bytes333, bytes33_sub);

        byte[] bytes3_sub3 = StringUtils.byteMerger(bytes333_sub, byte11);

        LogUtils.e("开始");
        LogUtils.e(Arrays.toString(bytes1));
        LogUtils.e(Arrays.toString(bytes1_sub));
        LogUtils.e(Arrays.toString(bytes22));
        LogUtils.e(Arrays.toString(bytes222_sub));
        LogUtils.e(Arrays.toString(bytes333));
        LogUtils.e(Arrays.toString(bytes333_sub));
        LogUtils.e("结束:"+StringUtils.binary(bytes3_sub3,2));

        return bytes3_sub3;
    }



}
