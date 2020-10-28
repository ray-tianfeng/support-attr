package com.zlong.attach_attr_lib.fitter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Time: 2020/10/15 0015
 * Author: zoulong
 */
public class FitterFactory {
    private static List<Class<? extends IFitter>> fitters = Arrays.asList((Class<? extends IFitter> []) new Class[]{
            ShapeFitter.class,
            SelectorDrawableFitter.class
    });
    private static List<String> supportAttrs = new ArrayList<>();
    static {
        try {
            for(Class<? extends IFitter> fitterClass : fitters){
                Field supportAttrField = fitterClass.getDeclaredField("supportAttr");
                if(supportAttrField == null) throw new Error(fitterClass.getName() + "is'nt static supportAttr field");
                supportAttrField.setAccessible(true);
                List<String> supportAttr = (List<String>) supportAttrField.get(null);
                supportAttrs.addAll(supportAttr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IFitter createFitterByAttrName(String attrName){
        try {
            if(!supportAttrs.contains(attrName)) return null;
            for(Class<? extends IFitter> fitterClass : fitters){
                Field supportAttrField = fitterClass.getDeclaredField("supportAttr");
                supportAttrField.setAccessible(true);
                if(((List<String>) supportAttrField.get(null)).contains(attrName)) return fitterClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
