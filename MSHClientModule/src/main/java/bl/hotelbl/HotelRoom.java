package bl.hotelbl;

import dataimpl.Hotel.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.DateUtil;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;
import vo.RoomChangeInfoVO;
import vo.RoomStockVO;

import java.util.*;

/**
 * Created by SilverNarcissus on 16/11/2.
 */
public class HotelRoom {
    private static final int MAX_AVAILABLE_DAYS = 30;
    private HotelDataService hotelDataService;
    private Map<String, HotelRoomVO> cache;

    protected HotelRoom() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
        cache = new HashMap<String, HotelRoomVO>();
    }

    /**
     * 通过酒店ID查找酒店房间
     *
     * @param hotelID
     * @return 符合ID的酒店房间VO
     */
    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        //TODO
        return null;
    }

    /**
     * 修改指定酒店房间信息
     *
     * @param rvo
     * @return 修改成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        //TODO
        return null;
    }

    /**
     * 更新房间数量
     *
     * @param roomChangeInfoVO
     * @return 更新成功与否
     */
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO) {
        //TODO
        return null;
    }

    /**
     * 添加酒店房间信息
     *
     * @param rvo
     * @return 添加成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage addRoom(HotelRoomVO rvo) {
        HotelRoomPO hotelRoomPO = roomVOToRoomPO(rvo);
        Calendar c = Calendar.getInstance();
        ResultMessage resultMessage = hotelDataService.addRoom(hotelRoomPO);
        //保存放在HotelRoomVO里面的roomStockVO
        ArrayList<RoomStockVO> roomStockVOs = new ArrayList<RoomStockVO>();
        //
        if (resultMessage == ResultMessage.SUCCESS) {
            for (int i = 0; i < MAX_AVAILABLE_DAYS; i++) {
                //
                RoomStockPO roomStockPO = new RoomStockPO(generateID(hotelRoomPO.getID(), i)
                        , hotelRoomPO.getHotelID()
                        , hotelRoomPO.getRoomType()
                        , hotelRoomPO.getTotalQuantity()
                        , new DateUtil().toString()
                );
                //将roomStockPO写入数据库
                hotelDataService.addRoomStock(roomStockPO);
                //
                roomStockVOs.add(roomStockPOToRoomStockVO(roomStockPO));
            }
            //保存放在HotelRoomVO里面的roomStockVO
            rvo.roomStockVOs = roomStockVOs;
            //
            cache.put(hotelRoomPO.getID(),rvo);
        }

        return resultMessage;
    }

    /**
     * 删除酒店房间信息
     *
     * @param hotelID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) {
        //TODO
        return null;
    }

    /**
     * 将hotelRoomVO转换为hotelRoomPO
     *
     * @param hotelRoomVO
     * @return hotelPO
     */
    private HotelRoomPO roomVOToRoomPO(HotelRoomVO hotelRoomVO) {
        return hotelRoomVO == null ? null : new HotelRoomPO(generateID(hotelRoomVO.hotelID, hotelRoomVO.roomType.ordinal())
                , hotelRoomVO.hotelID
                , hotelRoomVO.roomType
                , hotelRoomVO.price
                , hotelRoomVO.totalQuantity
                , false);
    }

    /**
     * 将hotelRoomPO转换为hotelRoomVO
     *
     * @param hotelRoomPO
     * @return hotelRoomVO
     */
    private HotelRoomVO roomPOToRoomVO(HotelRoomPO hotelRoomPO) {
        return hotelRoomPO == null ? null : new HotelRoomVO(hotelRoomPO.getHotelID()
                , hotelRoomPO.getRoomType()
                , hotelRoomPO.getPrice()
                , hotelRoomPO.getTotalQuantity()
                , null);
    }

    /**
     * 将roomStockPO转换为roomStockVO
     *
     * @param roomStockPO
     * @return roomStockVO
     */
    private RoomStockVO roomStockPOToRoomStockVO(RoomStockPO roomStockPO) {
        return roomStockPO == null ? null : new RoomStockVO(roomStockPO.getAvailableQuantity()
                , new DateUtil(roomStockPO.getDate()));
    }

    /**
     * 通过容器的ID生成组分的ID
     *
     * @param ID
     * @return
     */
    private String generateID(String ID, int number) {
        String cache = String.valueOf(number);
        if (cache.length() == 1) {
            cache = "0" + cache;
        }
        return ID + cache;
    }

    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type) {
        //TODO
        return null;
    }

    public ResultMessage isOrdered(String hotelID, RoomType type) {
        //TODO
        return null;
    }
}
