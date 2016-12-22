package blservice.hotelblservice;

import util.DateUtil;
import util.ResultMessage;
import util.RoomType;
import vo.*;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface HotelBLInfo {

    /**
     * 更新酒店房间数量
     *
     * @param roomChangeInfoVO
     * @return 更新成功与否
     */
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO);

    /**
     * 增加酒店评分
     * @param score 本次评分的平均分
     * @param hotelID 需要增加评分的酒店
     * @return 增加结果
     */
    public ResultMessage addScoreToHotelByHotelID(double score, String hotelID);

    /**
     * 得到指定日期内的最小可用房间数量
     * @param start 起始日期
     * @param end 结束日期
     * @param hotelID 酒店ID
     * @param roomType 房间类型
     * @return 最小可用房间数量
     */
    public int getAvailableQuantity(DateUtil start, DateUtil end, String hotelID, RoomType roomType);

    /**
     * 通过酒店ID查找酒店
     *
     * @param hotelID
     * @return 符合ID的酒店VO
     *
     */
    public Hotel_DetailVO getHotel(String hotelID);
}
