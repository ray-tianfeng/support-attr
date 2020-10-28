package com.zlong.attach_attr_lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.zlong.attach_attr_lib.fitter.IFitter;
import com.zlong.attach_attr_lib.fitter.FitterFactory;

import java.util.ArrayList;

/**
 * Time: 2020/10/15 0015
 * Author: zoulong
 */
public class AttrAutoHelper {
    static ArrayList<IFitter> fitters = new ArrayList<>();
    public static void apply(View view, Context context, AttributeSet attrs){
        if(view == null || context == null || attrs == null || attrs.getAttributeCount() == 0) return;
        for(int index = 0; index < attrs.getAttributeCount(); index ++){
            String attrName = attrs.getAttributeName(index);
            IFitter abstractFitter = getFitter(attrName);
            if(abstractFitter == null) continue;
            abstractFitter.applyAttr(context, attrName, attrs, index);
        }
        for(IFitter abstractFitter : fitters){
            abstractFitter.apply(view);
        }
        fitters.clear();
    }

    private static IFitter getFitter(String attrName){
        for(IFitter abstractFitter : fitters){
            if(abstractFitter.isSupport(attrName)) return abstractFitter;
        }
        IFitter abstractFitter = FitterFactory.createFitterByAttrName(attrName);
        if(abstractFitter != null){
            fitters.add(abstractFitter);
        }
        return abstractFitter;
    }
}
