package vo;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_ClientGradeVO extends Promotion_WebVO{

    /**
     * 执行策略所需的最低用户等级
     */
    public int clientGrade;

    /**
     *
     * @param promotionName 策略名称
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     * @param clientGrade 执行策略所需的最低用户等级
     */
    public Promotion_ClientGradeVO(String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, int clientGrade) {
        super(promotionName, promotionType, promotionDiscount, startDate, endDate);
        this.clientGrade = clientGrade;
    }

    public Promotion_ClientGradeVO(String promotionID, String promotionName, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate, int clientGrade) {
        super(promotionID, promotionName, promotionType, promotionDiscount, startDate, endDate);
        this.clientGrade = clientGrade;
    }

    public Promotion_ClientGradeVO(PromotionPO promotionPO){
        super(promotionPO);
        this.clientGrade = promotionPO.getClientGrade();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Promotion_ClientGradeVO) {
            Promotion_ClientGradeVO promotion_ClientGradeVO = (Promotion_ClientGradeVO) o;
            return compareData(promotion_ClientGradeVO);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_ClientGradeVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionName,this.promotionName)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount)
                && judgeEqual(pvo.clientGrade,this.clientGrade);
    }

    @Override
    public PromotionPO toPO() {
        if (promotionID==null){
            return new PromotionPO(this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", "", 0, null, null, this.clientGrade);
        }else {
            return new PromotionPO(this.promotionID, this.promotionName, this.promotionType, this.promotionDiscount,
                    this.startDate.toString(), this.endDate.toString(),
                    "", "", 0, null, null, this.clientGrade);
        }

    }

}
