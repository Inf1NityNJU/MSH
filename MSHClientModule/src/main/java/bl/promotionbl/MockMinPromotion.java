package bl.promotionbl;

import util.DateUtil;
import vo.OrderRoomVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/2.
 */
public class MockMinPromotion extends MinPromotion {
    private double minWebProm;
    private double minHotelProm;

    /**
     * 获得最小网站促销策略
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return 符合条件的最小网站促销策略
     */
    public double getMinWebProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID){
        return 0.80;
    }

    /**
     * 获得最小酒店促销策略
     * @param date
     * @param rvo
     * @param clientID
     * @param hotelID
     * @return 符合条件的最小酒店促销策略
     */
    public double getMinHotelProm(DateUtil date, ArrayList<OrderRoomVO> rvo, String clientID, String hotelID){
        return 0.80;
    }


}
