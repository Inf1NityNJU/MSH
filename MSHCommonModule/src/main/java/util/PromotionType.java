package util;

/**
 * Created by vivian on 16/10/12.
 */
public enum PromotionType {
    Hotel_Birthday("生日折扣", "12B7F3"),//生日类
    Hotel_SpecilaDate("特定期间折扣","FC537D"),//酒店特定日期类
    Hotel_RoomQuantity("房间数量折扣","00CCCC"),//房间数量类
    Hotel_Enterprise("合作企业折扣","BC52FD"),//企业客户类
    Web_ClientGrade("会员等级折扣","12B7F3"),//客户等级类
    Web_SpecilPlace("会员商圈折扣","BC52FD"),//商圈类
    Web_SpecilaDate("特定期间折扣","FC537D");//企业特定日期类

    private String type;
    private String color;

    private PromotionType(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public String getColor() {
        return this.color;
    }
}
