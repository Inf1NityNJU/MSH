package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_EnterpriseVO extends Promotion_HotelVO{

    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;

    /**
     * 策略涉及的公司名称
     */
    public String enterpriseName;


    /**
     *
     * @param promotionName 策略名称
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     * @param enterpriseName 策略涉及的公司名称
     */
    public Promotion_EnterpriseVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String enterpriseName, String hotelID) {
        super(promotionName, promotionType, promotionDiscount, hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.enterpriseName = enterpriseName;
    }

    public Promotion_EnterpriseVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, String enterpriseName, String hotelID) {
        super(promotionID, promotionName, promotionType, promotionDiscount, hotelID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.enterpriseName = enterpriseName;
    }

    public Promotion_EnterpriseVO(PromotionPO promotionPO){
        super(promotionPO);
        this.startDate = new DateUtil(promotionPO.getStartDate());
        this.endDate = new DateUtil(promotionPO.getEndDate());
        this.enterpriseName = promotionPO.getCompanyName();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Promotion_EnterpriseVO) {
            Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) o;
            return compareData(promotion_enterpriseVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_EnterpriseVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName, this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.enterpriseName,this.enterpriseName);
    }

    @Override
    public PromotionPO toPO() {
        if(this.promotionID==null) {
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    this.enterpriseName, this.hotelID, 0, null, null, 0);
        }else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    this.enterpriseName, this.hotelID, 0, null, null, 0);
        }

    }
}
