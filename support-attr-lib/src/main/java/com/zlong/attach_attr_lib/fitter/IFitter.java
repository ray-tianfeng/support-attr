package com.zlong.attach_attr_lib.fitter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Time: 2020/10/15 0015
 * Author: zoulong
 */
public interface IFitter {
    public void applyAttr(Context context, String attrName, AttributeSet attrs, int index);
    public void apply(View targetView);
    public boolean isSupport(String attrName);
}
