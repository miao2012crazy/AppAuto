package com.fresh.app.commonUtil;

import android.util.Log;

import org.greenrobot.greendao.annotation.Convert;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mr.miao on 2018/5/20.
 */

public class StringUtils {

    /**
     * byte数组转为对应的16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * byte数组转为对应的16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src, int length) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || length <= 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 十六进制编码字符串转为对应的二进制数组
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToBytes(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {

                baKeyword[i] = (byte) (Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baKeyword;
    }

    /**
     * 十六进制转ascii
     *
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    /**
     * 10进制字符串 转为16进制字符串
     * @ return
     */
    public static String convertDecToHexString(String s) {
        Log.i("", s);
        String str = Integer.toHexString(Integer.parseInt(s));
        if(str.length()%2==1){
            str = "0"+str;
        }
        return str;
    }

    /**
     * 通过做异或运算,求出校验码
     * @param cmd
     * @return
     */
    public static String xor(String cmd)
    {
        if(cmd.length()%2!=0){
            cmd = "0"+cmd;
        }
        int result = 0;
        for (int i = 0; i < cmd.length()-1; i=i+2) {
            //System.out.println(cmd.substring(i,i+2));
            result ^= Integer.valueOf(cmd.substring(i, i + 2), 16);
            System.out.println("16-->"+ Integer.valueOf(cmd.substring(i, i + 2), 16));
            System.out.println("result:"+result);
        }
        return Integer.toHexString(result);
    }

    /**
     * 以"-"拆分字符串
     * @param str
     * @return
     */
    public static String[] splitString(String str){
        return str.split("-");
    }

    public static String takeCity(String str){
        String nstr = null;
        if(str!=null){
            nstr=str.substring(0, str.length()-1);
        }
        return nstr;
    }

    /**
     * 时间戳转为日期
     * @param datestr
     * @return
     */
    public static String getSimpDate(String datestr){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String date = sdf.format(new Date(Long.parseLong(datestr) ));
        return date;
    }
    /**
     * 时间戳转为日期
     * @param smdateint
     * @return
     */
    public static String getSimpDate(long smdateint){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String date = sdf.format(new Date(smdateint));
        System.out.println(date);
        return date;
    }



    /**
     * 将一个长度为8的boolean数组（每bit代表一个boolean值）转换为byte
     * 2014-7-4 下午5:28:28
     * @param array
     * @return
     *
     */
    public static byte getByte(boolean[] array) {
        if(array != null && array.length > 0) {
            byte b = 0;
            for(int i=0;i<=7;i++) {
                if(array[i]){
                    int nn=(1<<(7-i));
                    b += nn;
                }
            }
            return b;
        }
        return 0;
    }


    //byte数组合并
    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }


    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(radix);// 这里的1代表正数
    }


    public static String binary_private(byte[] bytes){
        StringBuilder s1= new StringBuilder();
        for (int i=0;i<6;i++){
            Integer integer = Integer.valueOf(bytes[i] + "", 16);//不能处理带前缀的情况 0x
            StringBuilder s = new StringBuilder(Integer.toBinaryString(integer));
            if (s.length()<8){
                for (int j=s.length();j<8;j++){
                    s.insert(0, "0");
                }
//                s.reverse();
            }
            s1.append(s);
        }
        return String.valueOf(s1);
    }



    /**
     * 判断给定的字符串是否为null或者是空的
     *
     * @param string 给定的字符串
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }




    /**
     * 把给定的字符串用给定的字符分割
     *
     * @param string 给定的字符串
     * @param ch     给定的字符
     * @return 分割后的字符串数组
     */
    public static String[] split(String string, char ch) {
        ArrayList<String> stringList = new ArrayList<String>();
        char chars[] = string.toCharArray();
        int nextStart = 0;
        for (int w = 0; w < chars.length; w++) {
            if (ch == chars[w]) {
                stringList.add(new String(chars, nextStart, w - nextStart));
                nextStart = w + 1;
                if (nextStart ==
                        chars.length) {    //当最后一位是分割符的话，就再添加一个空的字符串到分割数组中去
                    stringList.add("");
                }
            }
        }
        if (nextStart <
                chars.length) {    //如果最后一位不是分隔符的话，就将最后一个分割符到最后一个字符中间的左右字符串作为一个字符串添加到分割数组中去
            stringList.add(new String(chars, nextStart,
                    chars.length - 1 - nextStart + 1));
        }
        return stringList.toArray(new String[stringList.size()]);
    }




    /**
     * 是否全是数字
     */
    public static boolean isAllDigital(char[] chars) {
        boolean result = true;
        for (int w = 0; w < chars.length; w++) {
            if (!Character.isDigit(chars[w])) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }


}
