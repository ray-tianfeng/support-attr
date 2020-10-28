package com.zlong.attach_attr_lib.fitter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.zlong.attach_attr_lib.utils.ResourcesUtils;
import java.util.Arrays;
import java.util.List;

/**
 * Time: 2020/10/15 0015
 * Author: zoulong
 */
public class ShapeFitter implements IFitter {
    static List<String> supportAttr = Arrays.asList(new String[]{
            "shape_solid",
            "shape_corners",
            "shape_type",
            "shape_corners_lt",
            "shape_corners_rt",
            "shape_corners_lb",
            "shape_corners_rb",
            "shape_stroke_dashWidth",
            "shape_stroke_color",
            "shape_stroke_dashGap",
            "shape_stroke_width"
    });
    GradientDrawable gradientDrawable = new GradientDrawable();
    float[] cornerRadii = new float[8];
    public ShapeFitter() {
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(0);
        gradientDrawable.setColor(Color.TRANSPARENT);
    }

    Stroke mStroke = new Stroke();
    @Override
    public void applyAttr(Context context, String attrName, AttributeSet attrs, int index) {
        switch (attrName){
            case "shape_type":
                String attrValue = attrs.getAttributeValue(index);
                if(attrValue.equals("2")) gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                else if(attrValue.equals("1")) gradientDrawable.setShape(GradientDrawable.OVAL);
                else if(attrValue.equals("3")) gradientDrawable.setShape(GradientDrawable.LINE);
                else if(attrValue.equals("4")) gradientDrawable.setShape(GradientDrawable.RING);
                break;
            case "shape_solid":
                attrValue = attrs.getAttributeValue(index);
                int color = ResourcesUtils.getColor(context, attrValue);
                gradientDrawable.setColor(color);
                break;
            case "shape_corners":
                attrValue = attrs.getAttributeValue(index);
                float attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                cornerRadii[0] = attrValuePx;
                cornerRadii[1] = attrValuePx;
                cornerRadii[2] = attrValuePx;
                cornerRadii[3] = attrValuePx;
                cornerRadii[4] = attrValuePx;
                cornerRadii[5] = attrValuePx;
                cornerRadii[6] = attrValuePx;
                cornerRadii[7] = attrValuePx;
                gradientDrawable.setCornerRadii(cornerRadii);
                break;
            case "shape_corners_lt":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                cornerRadii[0] = attrValuePx;
                cornerRadii[1] = attrValuePx;
                gradientDrawable.setCornerRadii(cornerRadii);
                break;
            case "shape_corners_rt":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                cornerRadii[2] = attrValuePx;
                cornerRadii[3] = attrValuePx;
                gradientDrawable.setCornerRadii(cornerRadii);
                break;
            case "shape_corners_rb":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                cornerRadii[4] = attrValuePx;
                cornerRadii[5] = attrValuePx;
                gradientDrawable.setCornerRadii(cornerRadii);
                break;
            case "shape_corners_lb":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                cornerRadii[6] = attrValuePx;
                cornerRadii[7] = attrValuePx;
                gradientDrawable.setCornerRadii(cornerRadii);
                break;
            case "shape_stroke_width":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                mStroke.width = attrValuePx;
                break;
            case "shape_stroke_dashWidth":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                mStroke.dashWidth = attrValuePx;
                break;
            case "shape_stroke_color":
                attrValue = attrs.getAttributeValue(index);
                color = ResourcesUtils.getColor(context, attrValue);
                mStroke.color = color;
                break;
            case "shape_stroke_dashGap":
                attrValue = attrs.getAttributeValue(index);
                attrValuePx = ResourcesUtils.getDimSize(context, attrValue);
                mStroke.dashGap = attrValuePx;
                break;
        }
    }

    @Override
    public void apply(View targetView) {
        if((mStroke.width != 0 && mStroke.color != 0)){
            gradientDrawable.setStroke((int)mStroke.width, mStroke.color, mStroke.dashWidth, mStroke.dashGap);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) targetView.setBackground(gradientDrawable);
        else targetView.setBackgroundDrawable(gradientDrawable);
    }

    @Override
    public boolean isSupport(String attrName) {
        return supportAttr.contains(attrName);
    }

    public class Stroke{
        float width;
        int color;
        float dashWidth;
        float dashGap;
    }
}
