package com.zlong.attach_attr_lib;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.zlong.attach_attr_lib.utils.ViewProducer;

/**
 * Time: 2020/9/23 0023
 * Author: zoulong
 */
public class LayoutFactory implements LayoutInflater.Factory2 {
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = ViewProducer.createViewFromTag(context, name, attrs);
        AttrAutoHelper.apply(view, context, attrs);
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }
}
