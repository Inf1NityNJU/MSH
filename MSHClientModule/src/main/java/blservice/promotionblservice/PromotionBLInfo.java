package blservice.promotionblservice;

import util.DateUtil;
import vo.OrderRoomVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/11.
 */
public interface PromotionBLInfo {
    /**
     * 得到该订单可以得到的最低酒店促销折扣
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return
     */
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID);

    /**
     * 得到该订单可以得到的最低网站促销折扣
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return
     */
    public double getMinWebProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID);
}
