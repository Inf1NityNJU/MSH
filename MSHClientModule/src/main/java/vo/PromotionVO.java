package vo;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by Kray on 2016/10/12.
 */
public class PromotionVO {

    /**
     * 策略编号
     */
    private String promotionID;

    /**
     * 策略类型
     */
    private PromotionType promotionType;

    /**
     * 策略执行开始日期
     */
    private DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    private DateUtil endDate;

    /**
     * 策略折扣
     */
    private double promotionDiscount;

    /**
     * 策略涉及的公司名称
     */
    private String companyName;

    /**
     * 策略涉及的酒店编号
     */
    private String hotelID;

    /**
     * 策略涉及的商圈
     */
    private Place place;

    /**
     * 执行策略所需的最低用户等级
     */
    private int clientGrade;

    /**
     * 执行策略所需的最低房间数量
     */
    private int roomQuantity;

    /**
     *
     * @param promotionID
     * @param promotionType
     * @param startDate
     * @param endDate
     * @param promotionDiscount
     * @param companyName
     * @param hotelID
     * @param place
     * @param clientGrade
     * @param roomQuantity
     */
    public PromotionVO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, String companyName, String hotelID, Place place, int clientGrade, int roomQuantity) {
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.companyName = companyName;
        this.hotelID = hotelID;
        this.place = place;
        this.clientGrade = clientGrade;
        this.roomQuantity = roomQuantity;
    }
}
