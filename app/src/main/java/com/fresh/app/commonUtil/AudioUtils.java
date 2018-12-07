package com.fresh.app.commonUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * 描述：语音播放工具类
 * 作者：卜俊文
 * 创建：2016/8/19 09:07
 * 邮箱：344176791@qq.com
 */
public class AudioUtils {

    private static AudioUtils audioUtils;

    private static SpeechSynthesizer mySynthesizer;

    public AudioUtils() {
    }

    /**
     * 描述:单例
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/19 14:38
     */
    public static AudioUtils getInstance() {
        if (audioUtils == null) {
            synchronized (AudioUtils.class) {
                if (audioUtils == null) {
                    audioUtils = new AudioUtils();
                }
            }
        }
        return audioUtils;
    }

    private static InitListener myInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("mySynthesiezer:", "InitListener init() code = " + code);
        }
    };


    /**
     * 描述:初始化语音配置
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/19 14:38
     */
    public static void init(Context context) {
        //处理语音合成关键类
        mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
        //设置发音人
        mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "vixy");
        //设置音调
        mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
        //设置音量
        mySynthesizer.setParameter(SpeechConstant.VOLUME, "50");
    }

    /**
     * 描述:根据传入的文本转换音频并播放
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/19 14:39
     */
    public void speakText(String content) {

        int code = mySynthesizer.startSpeaking(content, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {
                Log.e("miao","开始朗读");
            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {
                Log.e("miao","开始朗读");
            }

            @Override
            public void onSpeakPaused() {
                Log.e("miao","开始朗读");
            }

            @Override
            public void onSpeakResumed() {
                Log.e("miao","开始朗读");
            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {
                Log.e("miao",i+"");
            }

            @Override
            public void onCompleted(SpeechError speechError) {
                LogUtils.e(speechError.getErrorDescription());
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }
}
