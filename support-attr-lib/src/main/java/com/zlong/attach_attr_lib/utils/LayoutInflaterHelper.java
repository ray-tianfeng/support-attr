package com.zlong.attach_attr_lib.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import com.zlong.attach_attr_lib.LayoutFactory;
import java.lang.reflect.Field;

/**
 * Time: 2020/9/24 0024
 * Author: zoulong
 */
public class LayoutInflaterHelper {

    public static void setFactorySetFalse(LayoutInflater layoutInflater){
        try {
            //系统默认 LayoutInflater只能设置一次factory，所以利用反射解除限制
            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
            mFactorySet.setAccessible(true);
            mFactorySet.setBoolean(layoutInflater, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setFactory2(Activity targetActivity, LayoutInflater layoutInflater){
        try {
            LayoutFactory mLayoutFactory = new LayoutFactory();
            layoutInflater.setFactory2(mLayoutFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
