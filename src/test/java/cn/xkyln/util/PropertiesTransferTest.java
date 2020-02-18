package cn.xkyln.util;


import cn.xkyln.bean.Area;

import java.util.HashMap;
import java.util.Map;

public class PropertiesTransferTest {

    @org.junit.jupiter.api.Test
    public void beanToMap() {
        Area area1 = new Area("北京", "bj");
        Map<String, Object> areaMap = PropertiesTransfer.beanToMap(area1);
        System.out.println(area1);
        System.out.println(areaMap);
    }

    @org.junit.jupiter.api.Test
    public void mapToBean() {
        Map<String, Object> areaMap1 = new HashMap<>();
        areaMap1.put("areaName", "上海");
        areaMap1.put("areaCode", "sh");
        Area area1 = PropertiesTransfer.mapToBean(areaMap1, Area.class);
        System.out.println(areaMap1);
        System.out.println(area1);
    }
}