package util;

/**
 * Created by Sorumi on 16/10/12.
 */
public enum RoomType {
    SingleRoom("单人房"),
    DoubleRoom("大床房"),
    SuiteRoom("套房"),
    FamilyRoom("家庭房"),
    DoubleDouble("双人房"),
    EconomicRoom("经济房"),
    StandardRoom("标准房"),
    SuperiorRoom("高级房"),
    DeluxeRoom("奢华房"),
    BusinessRoom("商务房");

    private final String name;

    private RoomType(String name) {

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static RoomType getRoomTypeByName(String name) {
        for (RoomType type : RoomType.values()){
            if(name.equals(type.name))
                return type;
        }
        return null;
    }
}

