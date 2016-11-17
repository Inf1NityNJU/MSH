package blservice.hotelblservice;

import util.*;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;
import vo.RoomChangeInfoVO;

import java.util.ArrayList;

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
     * @throws HotelNotFoundException
     */
    public Hotel_DetailVO getHotel(String hotelID) throws HotelNotFoundException;

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
     * @throws InfoInvalidException
     */
    public ResultMessage updateHotel(Hotel_DetailVO hvo) throws InfoInvalidException;

    /**
     * 修改指定酒店房间信息
     *
     * @param rvo
     * @return 修改成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) throws InfoInvalidException;

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
     * @throws InfoInvalidException
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo) throws InfoInvalidException;

    /**
     * 添加酒店房间信息
     *
     * @param rvo
     * @return 添加成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage addRoom(HotelRoomVO rvo) throws InfoInvalidException;

    /**
     * 删除酒店信息
     *
     * @param hotelID
     * @return 删除成功与否
     * @throws HotelNotFoundException
     */
    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException;

    /**
     * 删除酒店房间信息
     *
     * @param hotelID
     * @return 删除成功与否
     * @throws HotelNotFoundException
     */
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) throws HotelNotFoundException;
}
