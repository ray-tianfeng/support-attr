package com.zlong.attach_attr_lib;

import java.util.HashMap;

/**
 * Time: 2020/10/15 0015
 * Author: zoulong
 */
class AttrContainer {
    public String attrType;
    public HashMap<String, String> attrKV = new HashMap<>();

    public AttrContainer(String attrType) {
        this.attrType = attrType;
    }
}
