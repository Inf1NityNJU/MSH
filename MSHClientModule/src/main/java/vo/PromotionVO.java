package vo;

import util.DateUtil;
import util.Place;
import util.PromotionType;

/**
 * Created by Kray on 2016/10/12.
 */
public class PromotionVO {

    public String promotionID;
    public PromotionType promotionType;
    public DateUtil startDate;
    public DateUtil endDate;
    public double promotionDiscount;
    public String companyName;
    public String hotelID;
    public Place place;
    public int clientGrade;
    public int roomQuantity;

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
