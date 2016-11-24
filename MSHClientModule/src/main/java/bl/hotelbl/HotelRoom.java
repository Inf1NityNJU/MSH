package bl.hotelbl;

import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.DateUtil;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;
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
        ArrayList<HotelRoomVO> result = new ArrayList<HotelRoomVO>();
        for (HotelRoomPO hotelRoomPO : hotelDataService.getRoom(hotelID)) {
            HotelRoomVO hotelRoomVO = roomPOToRoomVO(hotelRoomPO);
            //添加roomStock
            ArrayList<RoomStockVO> roomStockVOs = new ArrayList<RoomStockVO>();
            //
            for (RoomStockPO roomStockPO : hotelDataService.getRoomStock(hotelRoomPO.getHotelID())) {
                roomStockVOs.add(roomStockPOToRoomStockVO(roomStockPO));
            }
            hotelRoomVO.roomStockVOs = roomStockVOs;
            //写入cache
            cache.put(hotelRoomPO.getID(), hotelRoomVO);
            //保存到result
            result.add(hotelRoomVO);
        }
        return result;
    }

    /**
     * 修改指定酒店房间信息
     *
     * @param rvo
     * @return 修改成功与否
     */
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        ResultMessage resultMessage = deleteHotelRoom(rvo.hotelID, rvo.roomType);
        //
        if (resultMessage == ResultMessage.SUCCESS) {
            addRoom(rvo);
        }
        //
        return resultMessage;
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
            cache.put(hotelRoomPO.getID(), rvo);
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
        String hotelRoomID = generateID(hotelID, type.ordinal());
        ResultMessage resultMessage = hotelDataService.deleteRoom(hotelRoomID);
        if (resultMessage == ResultMessage.SUCCESS) {
            //
            cache.remove(hotelRoomID);
            //删除房间的roomStock
            for (RoomStockPO roomStockPO : hotelDataService.getRoomStock(hotelRoomID)) {
                hotelDataService.deleteRoomStock(roomStockPO.getID());
            }
        }
        return resultMessage;
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
                , hotelRoomVO.isCancelled);
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
                , null, hotelRoomPO.getIsCancelled());
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

    /**
     * 设置酒店房间将要被删除
     *
     * @param hotelID 酒店ID
     * @param type    房间类型
     * @return 设置成功与否
     */
    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type) {
        /**
         * 记录将要设置的房间
         */
        HotelRoomPO hotelRoom = null;
        //
        for (HotelRoomPO hotelRoomPO : hotelDataService.getRoom(hotelID)) {
            if (hotelRoomPO.getRoomType().equals(type)) {
                hotelRoom = hotelRoomPO;
                break;
            }
        }
        //
        if (hotelRoom == null) {
            return ResultMessage.NOT_EXIST;
        } else {
            hotelRoom.setIsCancelled(true);
            hotelDataService.updateRoom(hotelRoom);
            //更新cache
            cache.remove(hotelRoom.getID());
            cache.put(hotelRoom.getID(), roomPOToRoomVO(hotelRoom));
            //
            return ResultMessage.SUCCESS;
        }

    }

    /**
     * 判断该房间在可预订天数内是否被预订
     * ——————————————不改变hotelRoom的数据——————————————
     *
     * @param hotelID 酒店ID
     * @param type    房间类型
     * @return 房间类型不存在——NOT_EXIST<br>
     * 被预定——TRUE<br>
     * 没有被预定——FALSE
     */
    public ResultMessage isOrdered(String hotelID, RoomType type) {
        /**
         * 记录将要判断的房间
         */
        HotelRoomVO hotelRoom;
        //先检查cache
        if ((hotelRoom = cache.get(generateID(hotelID, type.ordinal()))) == null) {
            //cache中未找到
            for (HotelRoomVO hotelRoomVO : getRoom(hotelID)) {
                if (hotelRoomVO.roomType.equals(type)) {
                    hotelRoom = hotelRoomVO;
                    break;
                }
            }
        }
        if (hotelRoom == null) {
            return ResultMessage.NOT_EXIST;
        } else if (!checkChangeIsValidByVO(hotelRoom.roomStockVOs, hotelRoom.totalQuantity)) {
            return ResultMessage.TRUE;
        } else {
            return ResultMessage.FALSE;
        }
    }

    /**
     * 查看给定日期区间房间数量是否满足需求
     *
     * @param roomStockPOs 给定日期区间的房间列表
     * @param quantity     需求数量
     * @return 满足是否满足需求
     */
    private boolean checkChangeIsValidByPO(ArrayList<RoomStockPO> roomStockPOs, int quantity) {
        for (RoomStockPO stockPO : roomStockPOs) {
            if (stockPO.getAvailableQuantity() - quantity < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查看给定日期区间房间数量是否满足需求
     *
     * @param roomStockVOs 给定日期区间的房间列表
     * @param quantity     需求数量
     * @return 满足是否满足需求
     */
    private boolean checkChangeIsValidByVO(ArrayList<RoomStockVO> roomStockVOs, int quantity) {
        for (RoomStockVO stockVO : roomStockVOs) {
            if (stockVO.availableQuantity - quantity < 0) {
                return false;
            }
        }
        return true;
    }
}
