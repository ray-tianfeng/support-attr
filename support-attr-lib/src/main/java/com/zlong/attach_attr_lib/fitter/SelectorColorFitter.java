package com.zlong.attach_attr_lib.fitter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * Time: 2020/10/20 0020
 * Author: zoulong
 */
public class SelectorColorFitter implements IFitter{
    static List<String> supportAttr = Arrays.asList(new String[]{
            "selector_nor",
            "selector_ste",
            "selector_state"
    });
    @Override
    public void applyAttr(Context context, String attrName, AttributeSet attrs, int index) {

    }

    @Override
    public void apply(View targetView) {

    }

    @Override
    public boolean isSupport(String attrName) {
        return supportAttr.contains(attrName);
    }
}
