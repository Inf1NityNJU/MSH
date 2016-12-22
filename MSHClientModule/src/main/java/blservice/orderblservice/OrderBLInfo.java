package blservice.orderblservice;

import vo.Assessment_HotelVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2016/11/25.
 */
public interface OrderBLInfo {

    /**
     * 通过客户ID得到其预订过的酒店ID
     * 注：接受方认为这些ID不重复
     *
     * @param clientID 客户ID
     * @return 客户预定过的酒店ID
     */
    public ArrayList<String> getBookedHotelIDByClientID(String clientID);

    /**
     * 客户是否预定过该酒店
     * @param hotelID
     * @param clientID
     * @return
     */
    public boolean isBookedHotelByClient(String hotelID, String clientID);

    /**
     * 通过酒店ID得到该酒店的评价
     *
     * @param hotelID
     * @return
     */
    public ArrayList<Assessment_HotelVO> getAssessmentByHotelID(String hotelID);
}
