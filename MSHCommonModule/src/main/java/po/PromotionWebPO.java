package po;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by vivian on 16/11/11.
 */
public class PromotionWebPO {
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
     * 策略涉及的商圈
     */
    private Place place;

    /**
     * 执行策略所需的最低用户等级
     */
    private int clientGrade;

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public DateUtil getStartDate() {
        return startDate;
    }

    public void setStartDate(DateUtil startDate) {
        this.startDate = startDate;
    }

    public DateUtil getEndDate() {
        return endDate;
    }

    public void setEndDate(DateUtil endDate) {
        this.endDate = endDate;
    }

    public double getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(double promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getClientGrade() {
        return clientGrade;
    }

    public void setClientGrade(int clientGrade) {
        this.clientGrade = clientGrade;
    }

    public PromotionWebPO(String promotionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, Place place, int clientGrade) {

        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionDiscount = promotionDiscount;
        this.place = place;
        this.clientGrade = clientGrade;
    }
}
