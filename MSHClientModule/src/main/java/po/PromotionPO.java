package po;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by SilverNarcissus on 16/10/11.
 */
public class PromotionPO {

    private String promotionID;
    private PromotionType promotionType;
    private DateUtil startDate;
    private DateUtil endDate;
    private double promotionDiscount;
    private String companyName;
    private String hotelID;
    private Place place;
    private int clientGrade;
    private int roomQuantity;


    public PromotionPO(String promptionID, PromotionType promotionType, DateUtil startDate, DateUtil endDate, double promotionDiscount, String companyName, String hotelID, Place place, int clientGrade, int roomQuantity) {
        this.promotionID = promptionID;
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

    public String getPromptionID() {
        return promotionID;
    }

    public void setPromptionID(String promptionID) {
        this.promotionID = promptionID;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
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

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}
