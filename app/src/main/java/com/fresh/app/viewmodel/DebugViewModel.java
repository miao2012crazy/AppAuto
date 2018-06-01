package com.fresh.app.viewmodel;

import com.fresh.app.databinding.ActivityDebugBinding;
import com.fresh.app.view.IDebugView;

/**
 * Created by mr.miao on 2018/5/15.
 */

public class DebugViewModel {
    private final IDebugView mDebugView;
    private final ActivityDebugBinding mActivityDebugBinding;

    public DebugViewModel(IDebugView debugView, ActivityDebugBinding activityDebugBinding) {
        this.mDebugView=debugView;
        this.mActivityDebugBinding=activityDebugBinding;
    }



}
