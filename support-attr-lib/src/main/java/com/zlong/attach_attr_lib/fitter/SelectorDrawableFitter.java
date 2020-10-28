package com.zlong.attach_attr_lib.fitter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.zlong.attach_attr_lib.utils.ResourcesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time: 2020/10/19 0019
 * Author: zoulong
 */
public class SelectorDrawableFitter implements IFitter{
    static List<String> supportAttr = Arrays.asList(new String[]{
            "selector_nor",
            "selector_ste",
            "selector_state"
    });
    Drawable normalDrawable;
    Drawable stateDrawable;
    List<Integer> states = new ArrayList<>();
    @Override
    public void applyAttr(Context context, String attrName, AttributeSet attrs, int index) {
        switch (attrName){
            case "selector_nor":
                String attrValue = attrs.getAttributeValue(index);
                normalDrawable = ResourcesUtils.getDrawable(context, attrValue);
                break;
            case "selector_ste":
                attrValue = attrs.getAttributeValue(index);
                stateDrawable = ResourcesUtils.getDrawable(context, attrValue);
                break;
            case "selector_state":
                int attrValueInt = attrs.getAttributeIntValue(index, 0);
                Map<Integer, Integer> attrValueMap = new HashMap<>();
                attrValueMap.put(0x30, android.R.attr.state_pressed);
                attrValueMap.put(0x50, android.R.attr.state_checked);
                attrValueMap.put(0x03, android.R.attr.state_selected);
                attrValueMap.put(0x05, android.R.attr.state_checkable);
                int[] keys = new int[attrValueMap.size()];
                int kIndex = 0;
                for(Integer key : attrValueMap.keySet()){
                    keys[kIndex++] = key;
                }
                ArrayList<ArrayList<Integer>> subList = getSubArray(keys, keys.length);
                for(ArrayList<Integer> subs : subList){
                    int orResult = 0;
                    for(Integer sub : subs){
                        orResult = sub | orResult;
                    }
                    if(orResult == attrValueInt){
                        for(Integer sub : subs){
                            states.add(attrValueMap.get(sub));
                        }
                        break;
                    }
                }
                break;
        }
    }

    private static ArrayList<ArrayList<Integer>> getSubArray(int[] arr,int length) {
        ArrayList<ArrayList<Integer>> bList = new ArrayList<>();
        int mark = 0;
        int nEnd = 1 << length;
        for (mark = 0; mark < nEnd; mark++) {
            ArrayList<Integer> aList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (((1 << i)&mark) != 0) {
                    aList.add(arr[i]);
                }
            }
            if(aList.size() != 0)
            bList.add(aList);
        }
        return bList;
    }

    @Override
    public void apply(View targetView) {
        if(normalDrawable == null || stateDrawable == null || states.size() == 0) return;
        StateListDrawable stateListDrawable = new StateListDrawable();
        int[] nor = new int[states.size()];
        int[] state = new int[states.size()];
        for(int i = 0; i < states.size(); i++){
            state[i] = states.get(i);
            nor[i] = -states.get(i);
        }
//        int[] nor = new int[1];
//        int[] state = new int[1];
//        nor[0] = -android.R.attr.state_pressed;
//        state[0] = android.R.attr.state_pressed;
        stateListDrawable.addState(nor, normalDrawable);
        stateListDrawable.addState(state, stateDrawable);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) targetView.setBackground(stateListDrawable);
        else targetView.setBackgroundDrawable(stateListDrawable);
    }

    @Override
    public boolean isSupport(String attrName) {
        return supportAttr.contains(attrName);
    }
}
