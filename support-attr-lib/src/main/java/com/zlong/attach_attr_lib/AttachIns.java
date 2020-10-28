package com.zlong.attach_attr_lib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.zlong.attach_attr_lib.utils.LayoutInflaterHelper;

/**
 * Time: 2020/10/16 0016
 * Author: zoulong
 */
public class AttachIns {
    private static AttachIns mAttachIns = null;
    public AttachIns(){};

    static AttachIns get(){
        if(mAttachIns == null){
            synchronized(AttachIns.class){
                if(mAttachIns == null){
                    mAttachIns = new AttachIns();
                }
            }
        }
        return mAttachIns;
    }

    public void init(Application mApplication){
        //注册activity监听
        registerActivityLifecycleCallbacks(mApplication);
    }

    private void registerActivityLifecycleCallbacks(Application mApplication) {
        mApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                LayoutInflater mLayoutInflater = activity.getLayoutInflater();
                LayoutInflaterHelper.setFactorySetFalse(mLayoutInflater);
                LayoutInflaterHelper.setFactory2(activity, mLayoutInflater);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

}
