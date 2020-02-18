package cn.xkyln.util;

import cn.xkyln.bean.Area;

import java.util.ArrayList;
import java.util.List;

class CollectionUtilsTest {

    @org.junit.jupiter.api.Test
    void deepCopy() {
        Area area1 = new Area("北京","bj001");
        Area area2 = new Area("天津","tj002");
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(area1);
        areas.add(area2);

        List<Area> areaList = CollectionUtils.deepCopy(areas, Area.class);
        areaList.get(0).setAreaCode("cq003");
        areaList.get(0).setAreaName("重庆");
        System.out.println(areas);
        System.out.println(areaList);
    }


}