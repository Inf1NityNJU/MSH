package blservice.hotelblservice;

import util.DateUtil;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.*;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface HotelBLInfo {

    /**
     * 更新房间数量
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
     * 得到指定日期的房间库存和房间价格
     * @param start 开始日期
     * @param end 结束日期
     * @param hotelID 酒店ID
     * @param roomType 房间类型
     * @return 房间库存和价格列表
     */
//    public ArrayList<RoomStockWithPriceVO> getRoomStocks(DateUtil start, DateUtil end, String hotelID, RoomType roomType);


    public OrderRoomStockVO getRoomStocks(DateUtil start, DateUtil end, String hotelID, RoomType roomType);

}
