package com.zlong.attach_attr_lib.utils;

import android.content.Context;

/**
 * Time: 2020/10/16 0016
 * Author: zoulong
 */
public class DensityUtils {

    public static float dip2px(Context mContext, float dip){
        return mContext.getResources().getDisplayMetrics().density * dip + 0.5f;
    }
}
