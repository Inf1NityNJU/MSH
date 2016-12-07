package blservice.hotelblservice;

import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public interface HotelBLService {
    /**
     * 通过指定的筛选条件筛选酒店
     *
     * @param flags
     * @return 符合条件的酒店VO列表
     */
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags);

    /**
     * 通过酒店ID查找酒店
     *
     * @param hotelID
     * @return 符合ID的酒店VO
     *
     */
    public Hotel_DetailVO getHotel(String hotelID);

    /**
     * 通过酒店ID查找酒店房间
     *
     * @param hotelID
     * @return 符合ID的酒店房间VO
     */
    public ArrayList<HotelRoomVO> getRoom(String hotelID);

    /**
     * 修改指定酒店信息
     *
     * @param hvo
     * @return 修改成功与否
     *
     */
    public ResultMessage updateHotel(Hotel_DetailVO hvo);

    /**
     * 修改指定酒店房间信息
     *
     * @param rvo
     * @return 修改成功与否
     *
     */
    public ResultMessage updateHotelRoom(HotelRoomVO rvo);

    /**
     * 更新房间数量
     * @param roomChangeInfoVO
     * @return 更新成功与否
     */
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO);

    /**
     * 添加酒店信息
     *
     * @param hvo
     * @return 添加成功与否
     *
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo);

    /**
     * 添加酒店房间信息
     *
     * @param rvo
     * @return 添加成功与否
     */
    public ResultMessage addRoom(HotelRoomVO rvo);

    /**
     * 删除酒店信息
     *
     * @param hotelID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotel(String hotelID);

    /**
     * 删除酒店房间信息
     *
     * @param hotelID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type);

    /**
     * 设置酒店房间将要被删除
     * @param hotelID
     * @param type
     * @return 设置成功与否
     */
    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type);

    /**
     * 查看酒店房间是否被预订过
     * @param hotelID
     * @return 查询结果
     */
    public ResultMessage isOrdered(String hotelID,RoomType type);

    /**
     * 按照价格升序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);
    /**
     * 按照价格降序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);
    /**
     * 按照星级升序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);
    /**
     * 按照星级降序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);
    /**
     * 按照评分升序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);
    /**
     * 按照评分降序排列酒店
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs);

    /**
     * 按条件搜索酒店
     * @param flags 搜索条件
     * @return 简明格式的酒店VO
     */
    public ArrayList<Hotel_BriefVO> searchHotelInBriefVO(FilterFlagsVO flags);


    /**
     * 找到对应酒店的对应日期的所有可用房间状况
     * @param start 起始日期
     * @param end 结束日期
     * @param hotelID 酒店ID
     * @return 所有可用房间状况
     */
    public ArrayList<OrderRoomStockVO> getRoomStocks(DateUtil start, DateUtil end, String hotelID);


}
