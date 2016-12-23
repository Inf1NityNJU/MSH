package blimpl.promotionblimpl;

import po.PromotionPO;
import vo.*;

/**
 * Created by SilverNarcissus on 2016/12/22.
 * Done on 2016/12/22
 */
class PromotionFactory {
    /**
     * 以生日策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO a_buildByBirthdayVO(PromotionPO promotionPO) {
        return new Promotion_BirthdayVO(promotionPO);
    }

    /**
     * 以特定日期策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO b_buildBySpecialDayVO(PromotionPO promotionPO) {
        return new Promotion_HotelSpecialDateVO(promotionPO);
    }

    /**
     * 以房间数量策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO c_buildByRoomQuantityVO(PromotionPO promotionPO) {
        return new Promotion_RoomQuantityVO(promotionPO);
    }

    /**
     * 以企业策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO d_buildByEnterpriseVO(PromotionPO promotionPO) {
        return new Promotion_EnterpriseVO(promotionPO);
    }

    /**
     * 以客户等级策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO e_buildByClientGradeVO(PromotionPO promotionPO) {
        return new Promotion_ClientGradeVO(promotionPO);
    }

    /**
     * 以指定商圈策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO f_buildBySpecialPlaceVO(PromotionPO promotionPO) {
        return new Promotion_SpecialPlaceVO(promotionPO);
    }

    /**
     * 以特殊日期策略转换
     *
     * @param promotionPO 需要转换的PO
     * @return 转换得到的VO
     */
    public static PromotionVO g_buildBySpecialDateVO(PromotionPO promotionPO) {
        return new Promotion_WebSpecialDateVO(promotionPO);
    }

}
