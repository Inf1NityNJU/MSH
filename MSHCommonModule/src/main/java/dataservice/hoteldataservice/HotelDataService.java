package dataservice.hoteldataservice;

import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public interface HotelDataService {

    /**
     * 精确匹配搜索酒店
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value);

    /**
     * 前缀匹配搜索酒店
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value);

    /**
     * 完全匹配搜索酒店
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelPO> fullSearchHotel(String field, Object value);

    /**
     * 后缀匹配搜索酒店
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value);

    /**
     * 模糊匹配搜索酒店
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value);

    /**
     * 范围匹配搜索酒店
     *
     * @param field 搜索域名
     * @param min   搜索下限
     * @param max   搜索上限
     * @return 搜索结果
     */
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max);

    /**
     * 通过ID得到酒店
     *
     * @param hotelID 酒店ID
     * @return 得到的酒店
     */
    public HotelPO getHotel(String hotelID);

    /**
     * 得到指定酒店的全部房间
     *
     * @param hotelID 指定酒店ID
     * @return 该酒店的全部房间
     */
    public ArrayList<HotelRoomPO> getRoom(String hotelID);

    /**
     * 更新酒店信息
     *
     * @param hotelPO 新的酒店信息
     * @return 更新成功与否
     */
    public ResultMessage updateHotel(HotelPO hotelPO);

    /**
     * 更新酒店房间信息
     *
     * @param hotelRoomPO 新的酒店房间信息
     * @return 更新成功与否
     */
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO);

    /**
     * 添加酒店
     *
     * @param hotelPO 需要添加的酒店
     * @return 加添成功与否
     */
    public ResultMessage addHotel(HotelPO hotelPO);

    /**
     * 添加酒店房间
     *
     * @param hotelRoomPO 需要添加的酒店房间
     * @return 加添成功与否
     */
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO);

    /**
     * 删除酒店信息
     *
     * @param hotelID 需要删除的酒店ID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotel(String hotelID);

    /**
     * 删除酒店房间
     *
     * @param roomID 需要删除的酒店房间ID
     * @return 删除成功与否
     */
    public ResultMessage deleteRoom(String roomID);

    /**
     * 添加房间库存
     *
     * @param roomStockPO 需要添加的房间库存
     * @return 添加成功与否
     */
    public ResultMessage addRoomStock(RoomStockPO roomStockPO);

    /**
     * 更新房间库存
     *
     * @param roomStockPO 需要更新的房间库存
     * @return 更新成功与否
     */
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO);

    /**
     * 删除房间库存
     *
     * @param roomStockID 需要删除的房间库存ID
     * @return 删除成功与否
     */
    public ResultMessage deleteRoomStock(String roomStockID);

    /**
     * 得到指定房间的库存
     *
     * @param hotelRoomID 指定房间的ID
     * @return 房间库存列表
     */
    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID);

    /**
     * 得到酒店房间
     *
     * @param hotelRoomID 酒店房间ID
     * @return 酒店房间
     */
    public HotelRoomPO getRoomByID(String hotelRoomID);

    /**
     * 完全匹配搜索酒店房间
     *
     * @param field 搜索域名
     * @param value 搜索域值
     * @return 搜索结果
     */
    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value);

    /**
     * 多条件搜索酒店
     *
     * @param criteriaClauses 多条件集合
     * @return 搜索结果
     */
    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses);

    /**
     * 多条件搜索酒店房间
     *
     * @param criteriaClauses 多条件集合
     * @return 搜索结果
     */
    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses);

    /**
     * 多条件搜索房间库存
     *
     * @param criteriaClauses 多条件集合
     * @return 搜索结果
     */
    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses);

}
