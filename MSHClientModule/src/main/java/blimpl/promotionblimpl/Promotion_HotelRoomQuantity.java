package blimpl.promotionblimpl;

import util.DateUtil;
import vo.PromotionVO;
import vo.Promotion_RoomQuantityVO;

/**
 * Created by vivian on 16/11/25.
 */
public class Promotion_HotelRoomQuantity extends ConcretePromotion {
    public Promotion_HotelRoomQuantity(DateUtil dateUtil) {
        super(dateUtil);
    }

    @Override
    public double getPromotionDiscount(PromotionVO promotionVO, Object o) {
        double discount = 0;
        if (o instanceof Integer) {
            int currentRoomQuantity = (Integer) o;
            Promotion_RoomQuantityVO promotion_roomQuantityVO = (Promotion_RoomQuantityVO) promotionVO;
            if (dateUtil.isInRange(promotion_roomQuantityVO.startDate, promotion_roomQuantityVO.endDate)) {
                if (promotion_roomQuantityVO.roomQuantity <= currentRoomQuantity) {
                    discount = promotion_roomQuantityVO.promotionDiscount;
                }
            }
        }
        return discount;
    }
}
