package util;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2016/11/24.
 */
public enum City {
    NanJing("南京", new Place[]{Place.XianLin, Place.TangShan, Place.XinJieKou, Place.Airport, Place.RailwayStation}),
    ShangHai("上海", new Place[]{Place.SongSquare, Place.JiaYunPort, Place.Airport, Place.RailwayStation}),
    BeiJing("北京", new Place[]{Place.Center, Place.WanDa, Place.Airport, Place.RailwayStation}),
    GuangZhou("广州", new Place[]{Place.NewRing, Place.InfRoad, Place.Airport, Place.RailwayStation}),
    LanZhou("兰州", new Place[]{Place.XueStore, Place.Airport, Place.RailwayStation}),
    GuiYang("贵阳", new Place[]{Place.SiJiaCenter, Place.Airport, Place.RailwayStation});

    private final String name;
    private final Place[] places;

    private City(String name, Place[] places) {
        this.name = name;
        this.places = places;
    }

    public Place[] getPlaces() {
        return this.places;
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
    public static City getCityByName(String name) {
        for (City city : City.values()) {
            if (name.equals(city.name))
                return city;
        }
        return null;
    }

    public static ArrayList<String> getNames(City[] cities) {
        ArrayList<String> names = new ArrayList<>();

        for (City city : cities) {
            names.add(city.name);
        }
        return names;
    }
}
