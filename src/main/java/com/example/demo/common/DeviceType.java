package com.example.demo.common;

import java.util.HashMap;
import java.util.Map;

public class DeviceType {
    public static  Map<String,String> type = new HashMap<>();
    static {
        type.put("led","led");
        type.put("label","标签");
        type.put("logo","logo");
        type.put("code","条形码");
        type.put("aoi","aoi");
        type.put("socket","插座");
    }

}
