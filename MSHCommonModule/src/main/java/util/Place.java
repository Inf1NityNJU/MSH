package util;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/14.
 */
public enum Place {
    XianLin("仙林"),
    TangShan("汤山"),
    XinJieKou("新街口"),
    WanDa("万达"),
    InfRoad("无限步行街"),
    Center("市中心"),
    SongSquare("宋氏广场"),
    XueStore("薛家铺"),
    SiJiaCenter("思佳中心"),
    JiaYunPort("佳韵港"),
    NewRing("新环区"),
    Airport("机场区"),
    RailwayStation("火车站区");
//    Unnecessary("不需要(不应该作为选项出现)");

    private final String name;

    Place(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    /**
     * 通过名称获取枚举值
     *
     * @param name 枚举名称
     * @return 枚举值
     */
    public static Place getPlaceByName(String name) {
        for (Place place : Place.values()) {
            if (name.equals(place.name))
                return place;
        }
        return null;
    }

    public static ArrayList<String> getNames(Place[] places) {
        ArrayList<String> names = new ArrayList<>();

        for (Place place : places) {
            names.add(place.name);
        }
        return names;
    }
}
