package com.zlong.attach_attr_lib.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Time: 2020/10/16 0016
 * Author: zoulong
 */
public class ResourcesUtils {
    public static float getDimSize(Context mContext, String value){
        if(value.indexOf("dip") != -1){
            value = value.replace("dip", "");
            return DensityUtils.dip2px(mContext, Float.parseFloat(value));
        }else{
            value = value.replace("px", "");
            return Float.parseFloat(value);
        }
    }

    public static int getColor(Context mContext, String value){
        if(value.startsWith("#")) return Color.parseColor(value);
        else if(value.startsWith("@")){
            value = value.replace("@", "");
            return mContext.getResources().getColor(Integer.parseInt(value));
        }
        return Color.TRANSPARENT;
    }

    public static Drawable getDrawable(Context mContext, String value){
        if(value.startsWith("#")) return new ColorDrawable(Color.parseColor(value));
        else if(value.startsWith("@")){
            value = value.replace("@", "");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                return mContext.getResources().getDrawable(Integer.parseInt(value), mContext.getTheme());
            }else{
                return mContext.getResources().getDrawable(Integer.parseInt(value));
            }
        }
        return null;
    }
}
