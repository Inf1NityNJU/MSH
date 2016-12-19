package vo;

import po.PromotionPO;
import util.City;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_SpecialPlaceVO extends Promotion_WebVO {


    /**
     * 策略涉及的城市
     */
    public City city;

    /**
     * 策略涉及的商圈
     */
    public Place place;


    /**
     * @param promotionName     策略名称
     * @param promotionType     策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate         策略起始日期
     * @param endDate           策略截止日期
     * @param place             策略涉及的商圈
     */
    public Promotion_SpecialPlaceVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, City city, Place place) {
        super(promotionName, promotionType, promotionDiscount, startDate, endDate);
        this.city = city;
        this.place = place;
    }

    public Promotion_SpecialPlaceVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, City city, Place place) {
        super(promotionID, promotionName, promotionType, promotionDiscount, startDate, endDate);
        this.city = city;
        this.place = place;
    }

    public Promotion_SpecialPlaceVO(PromotionPO promotionPO) {
        super(promotionPO);
        this.place = promotionPO.getPlace();
        this.city = promotionPO.getCity();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Promotion_SpecialPlaceVO) {
            Promotion_SpecialPlaceVO promotion_SpecialPlaceVO = (Promotion_SpecialPlaceVO) o;
            return compareData(promotion_SpecialPlaceVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_SpecialPlaceVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate, this.startDate)
                && judgeEqual(pvo.endDate, this.endDate)
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount)
                && judgeEqual(pvo.city, this.city)
                && judgeEqual(pvo.place, this.place);
    }

    @Override
    public PromotionPO toPO() {
        if (this.promotionID == null) {
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", "", 0, this.city, this.place, 0);

        } else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", "", 0, this.city, this.place, 0);

        }
    }
}
