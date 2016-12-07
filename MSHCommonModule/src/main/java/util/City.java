package util;

/**
 * Created by SilverNarcissus on 2016/11/24.
 */
public enum City {
    NanJing("南京", new Place[]{Place.XianLin, Place.TangShan, Place.XinJieKou}),
    ShangHai("上海", new Place[]{}),
    BeiJing("北京", new Place[]{}),
    GuangZhou("广州", new Place[]{}),
    LanZhou("兰州", new Place[]{}),
    GuiYang("贵阳", new Place[]{});

    private final String name;
    private final Place[] places;

    private City(String name, Place[] places) {
        this.name = name;
        this.places = places;
    }

    public Place[] getPlaces(){
        return this.places;
    }


    public String getName() {
        return name;
    }

    public static City getCityByName(String name) {
        for (City city : City.values()){
            if(name.equals(city.name))
                return city;
        }
        return null;
    }
}
