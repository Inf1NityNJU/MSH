package vo;

import po.OrderPO;

/**
 * Created by Sorumi on 16/10/12.
 */
public class BillVO {


    /**
     * 使用的网站促销策略
     */
    public PromotionVO websitePromotion;

    /**
     * 使用的酒店促销策略
     */
    public PromotionVO hotelPromotion;

    /**
     * 原价
     */
    public double originPrice;

    /**
     * 总价
     */
    public double totalPrice;

    public BillVO(PromotionVO websitePromotion, PromotionVO hotelPromotion, double originPrice, double totalPrice) {

        this.websitePromotion = websitePromotion;
        this.hotelPromotion = hotelPromotion;
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
    }

    public BillVO(OrderPO orderPO) {
        if (orderPO.getWebsitePromotionName() != null) {
            this.websitePromotion = new PromotionVO(orderPO.getWebsitePromotionName(), null, orderPO.getWebsitePromotionDiscount());
        }

        if (orderPO.getHotelPromotionName() != null) {
            this.hotelPromotion = new PromotionVO(orderPO.getHotelPromotionName(), null, orderPO.getHotelPromotionDiscount());
        }

        this.originPrice = orderPO.getOriginPrice();
        this.totalPrice = orderPO.getTotalPrice();
    }
}
