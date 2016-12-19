package blservice.promotionblservice;

import util.City;
import util.DateUtil;
import util.Place;
import vo.OrderRoomVO;
import vo.Promotion_HotelVO;
import vo.Promotion_WebVO;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/11.
 */
public interface PromotionBLInfo {
    /**
     * 获得网站最小促销策略
     * @param date 下订单日期
     * @param clientGrade 客户等级
     * @param place 所在商圈
     * @return
     */
    public Promotion_WebVO getMinWebProm(DateUtil date, int clientGrade, City city, Place place);

    /**
     * 获得最小酒店促销策略
     * @param hotelID 酒店编号
     * @param date 下订单日期
     * @param birthday 客户生日
     * @param enterpriseName 客户所属企业名称
     * @param roomQuantity 所定房间数量
     * @return
     */
    public Promotion_HotelVO getMinHotelProm(String hotelID, DateUtil date, DateUtil birthday, String enterpriseName, int roomQuantity);
}
