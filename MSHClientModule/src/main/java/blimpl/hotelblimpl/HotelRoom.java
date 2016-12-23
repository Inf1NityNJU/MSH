package blimpl.hotelblimpl;

import network.HotelClientNetworkImpl;
import network.HotelClientNetworkService;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.*;
import vo.*;

import java.util.*;

/**
 * Created by SilverNarcissus on 16/11/2.
 * All Done on 16/11/26
 */
public class HotelRoom {
    private static final int MAX_AVAILABLE_DAYS = 30;
    /**
     * 用于访问data层的接口
     */
    private HotelClientNetworkService hotelClientNetworkService;
    /**
     * 用于缓存HotelRoomVO
     */
    private Map<String, HotelRoomVO> cache;

    protected HotelRoom() {
        hotelClientNetworkService = new HotelClientNetworkImpl();
        cache = new HashMap<String, HotelRoomVO>();
    }

    /**
     * 查看给定日期区间房间数量是否满足需求
     *
     * @param roomStockPOs 给定日期区间的房间列表
     * @param quantity     需求数量
     * @return 满足是否满足需求
     */
    private boolean checkChangeIsValidByPO(ArrayList<RoomStockPO> roomStockPOs, int quantity, int max) {
        for (RoomStockPO stockPO : roomStockPOs) {
            if (stockPO.getAvailableQuantity() - quantity < 0 || stockPO.getAvailableQuantity() - quantity > max) {
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
    private boolean checkChangeIsValidByVO(ArrayList<RoomStockVO> roomStockVOs, int quantity, int max) {
        for (RoomStockVO stockVO : roomStockVOs) {
            if (stockVO.availableQuantity - quantity < 0 || stockVO.availableQuantity - quantity > max) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过酒店ID查找酒店房间
     *
     * @param hotelID 需要查找房间的酒店ID
     * @return 符合ID的酒店房间VO
     */
    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        ArrayList<HotelRoomVO> result = new ArrayList<HotelRoomVO>();
        for (HotelRoomPO hotelRoomPO : hotelClientNetworkService.getRoom(hotelID)) {
            HotelRoomVO hotelRoomVO = roomPOToRoomVO(hotelRoomPO);
            //添加roomStock
            ArrayList<RoomStockVO> roomStockVOs = new ArrayList<RoomStockVO>();
            //
            for (RoomStockPO roomStockPO : hotelClientNetworkService.getRoomStock(hotelRoomPO.getID())) {
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
     * @param rvo 新的房间信息
     * @return 修改成功与否
     */
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        //房间已经被预订过
        if (isOrdered(rvo.hotelID, rvo.roomType).equals(ResultMessage.TRUE)) {
            return ResultMessage.INVALID;
        }
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
     * @param roomChangeInfoVO 房间更新信息
     * @return 更新成功与否
     */
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO) {
        //得到符合条件的房间库存
        ArrayList<RoomStockPO> roomStockPOs = getRoomStockPOs(roomChangeInfoVO);

        int quantity = roomChangeInfoVO.quantity;
        //为更新cache做准备
        ArrayList<RoomStockVO> roomStockVOs = new ArrayList<RoomStockVO>();
        //
        if (checkChangeIsValidByPO(roomStockPOs
                , quantity
                , hotelClientNetworkService.getRoomByID(
                        ToolKit.generateID(roomChangeInfoVO.hotelID, roomChangeInfoVO.type.ordinal()))
                        .getTotalQuantity())) {

            for (RoomStockPO roomStockPO : roomStockPOs) {
                roomStockPO.setAvailableQuantity(roomStockPO.getAvailableQuantity() - quantity);
                hotelClientNetworkService.updateRoomStock(roomStockPO);
                roomStockVOs.add(roomStockPOToRoomStockVO(roomStockPO));
            }
        } else {
            return ResultMessage.INVALID;
        }
        //更新cache
        HotelRoomPO roomPO = hotelClientNetworkService.getRoomByID(ToolKit.generateID(roomChangeInfoVO.hotelID, roomChangeInfoVO.type.ordinal()));
        HotelRoomVO roomVO = roomPOToRoomVO(roomPO);
        roomVO.roomStockVOs = roomStockVOs;
        cache.remove(roomPO.getID());
        cache.put(roomPO.getID(), roomVO);
        //
        return ResultMessage.SUCCESS;
    }

    /**
     * 得到符合条件的房间库存
     *
     * @param roomChangeInfoVO 查询条件
     * @return 符合条件的房间库存列表
     */
    private ArrayList<RoomStockPO> getRoomStockPOs(RoomChangeInfoVO roomChangeInfoVO) {
        //多条件查询房间库存
        ArrayList<CriteriaClause> criteriaClauses = new ArrayList<CriteriaClause>();
        //酒店ID
        criteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("hotelID", roomChangeInfoVO.hotelID, QueryMethod.Full));
        //房间类型
        criteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("roomType", roomChangeInfoVO.type, QueryMethod.Full));
        //起止日期
        criteriaClauses.add(CriteriaClauseImpl.createRangeValueQuery("date", roomChangeInfoVO.start.toString(), roomChangeInfoVO.end.toString(), QueryMethod.Range));
        //得到指定房间的需要被预订的库存
        //保存要修改的数量
        return hotelClientNetworkService.multiSearchRoomStockPO(criteriaClauses);
    }

    /**
     * 添加酒店房间信息
     *
     * @param rvo 新的房间信息
     * @return 添加成功与否
     */
    public ResultMessage addRoom(HotelRoomVO rvo) {
        HotelRoomPO hotelRoomPO = roomVOToRoomPO(rvo);
        ResultMessage resultMessage = hotelClientNetworkService.addRoom(hotelRoomPO);
        //保存放在HotelRoomVO里面的roomStockVO
        ArrayList<RoomStockVO> roomStockVOs = new ArrayList<RoomStockVO>();
        //
        DateUtil today = new DateUtil();
        if (resultMessage == ResultMessage.SUCCESS) {
            for (int i = 0; i < MAX_AVAILABLE_DAYS; i++) {
                //
                RoomStockPO roomStockPO = new RoomStockPO(ToolKit.generateID(hotelRoomPO.getID(), i)
                        , hotelRoomPO.getHotelID()
                        , hotelRoomPO.getRoomType()
                        , hotelRoomPO.getTotalQuantity()
                        , today.toString()
                );
                //将roomStockPO写入数据库
                hotelClientNetworkService.addRoomStock(roomStockPO);
                //
                roomStockVOs.add(roomStockPOToRoomStockVO(roomStockPO));
                today.increase();
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
     * @param hotelID 需要删除的房间所属酒店的ID
     * @param type    需要删除的房间类型
     * @return 删除成功与否
     */
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) {
        //房间已经被预订过
        if (isOrdered(hotelID, type).equals(ResultMessage.TRUE)) {
            return ResultMessage.INVALID;
        }
        //
        String hotelRoomID = ToolKit.generateID(hotelID, type.ordinal());
        ResultMessage resultMessage = hotelClientNetworkService.deleteRoom(hotelRoomID);
        if (resultMessage == ResultMessage.SUCCESS) {
            //
            cache.remove(hotelRoomID);
            //删除房间的roomStock
            for (RoomStockPO roomStockPO : hotelClientNetworkService.getRoomStock(hotelRoomID)) {
                hotelClientNetworkService.deleteRoomStock(roomStockPO.getID());
            }
        }
        return resultMessage;
    }

    /**
     * 将hotelRoomVO转换为hotelRoomPO
     *
     * @param hotelRoomVO 需要转换的RoomVO
     * @return hotelPO
     */
    private HotelRoomPO roomVOToRoomPO(HotelRoomVO hotelRoomVO) {
        return hotelRoomVO == null ? null : new HotelRoomPO(ToolKit.generateID(hotelRoomVO.hotelID, hotelRoomVO.roomType.ordinal())
                , hotelRoomVO.hotelID
                , hotelRoomVO.roomType
                , hotelRoomVO.price
                , hotelRoomVO.totalQuantity
                , hotelRoomVO.isCancelled);
    }

    /**
     * 将hotelRoomPO转换为hotelRoomVO
     *
     * @param hotelRoomPO 需要转换的RoomPO
     * @return hotelRoomVO
     */
    private HotelRoomVO roomPOToRoomVO(HotelRoomPO hotelRoomPO) {
        return hotelRoomPO == null ? null : new HotelRoomVO(hotelRoomPO.getHotelID()
                , hotelRoomPO.getRoomType()
                , hotelRoomPO.getPrice()
                , hotelRoomPO.getTotalQuantity()
                , null
                , hotelRoomPO.getIsCancelled());
    }

    /**
     * 将roomStockPO转换为roomStockVO
     *
     * @param roomStockPO 需要转换的roomStockPO
     * @return roomStockVO
     */
    private RoomStockVO roomStockPOToRoomStockVO(RoomStockPO roomStockPO) {
        return roomStockPO == null ? null : new RoomStockVO(roomStockPO.getAvailableQuantity()
                , new DateUtil(roomStockPO.getDate()));
    }


    /**
     * 设置酒店房间将要被删除
     *
     * @param hotelID 酒店ID
     * @param type    房间类型
     * @return 设置成功与否
     */
    public ResultMessage setRoomWillBeCancelled(String hotelID, RoomType type) {
        /*
         * 记录将要设置的房间
         */
        HotelRoomPO hotelRoom = null;
        //
        for (HotelRoomPO hotelRoomPO : hotelClientNetworkService.getRoom(hotelID)) {
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
            hotelClientNetworkService.updateRoom(hotelRoom);
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
        /*
         * 记录将要判断的房间
         */
        HotelRoomVO hotelRoom;
        //先检查cache
        if ((hotelRoom = cache.get(ToolKit.generateID(hotelID, type.ordinal()))) == null) {
            //cache中未找到
            ArrayList<CriteriaClause> roomStockCriteriaClauses = new ArrayList<CriteriaClause>();
            roomStockCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("hotelID", hotelID, QueryMethod.Full));
            roomStockCriteriaClauses.add(CriteriaClauseImpl.createSingleValueQuery("roomType", type, QueryMethod.Full));
            ArrayList<RoomStockPO> roomStockPOs = hotelClientNetworkService.multiSearchRoomStockPO(roomStockCriteriaClauses);
            //如果找不到对应的房间，返回失败
            if (roomStockPOs.isEmpty()) {
                return ResultMessage.NOT_EXIST;
            }
            //
            HotelRoomPO hotelRoomPO = hotelClientNetworkService.getRoomByID(ToolKit.generateID(hotelID, type.ordinal()));
            if (!checkChangeIsValidByPO(roomStockPOs, hotelRoomPO.getTotalQuantity(), hotelRoomPO.getTotalQuantity())) {
                return ResultMessage.TRUE;
            } else {
                return ResultMessage.FALSE;
            }
        }
        //cache中找到
        else {
            if (!checkChangeIsValidByVO(hotelRoom.roomStockVOs, hotelRoom.totalQuantity, hotelRoom.totalQuantity)) {
                return ResultMessage.TRUE;
            } else {
                return ResultMessage.FALSE;
            }
        }
    }

    /**
     * 得到指定日期的房间库存和房间价格
     *
     * @param start   开始日期
     * @param end     结束日期
     * @param hotelID 酒店ID
     * @return 房间库存和价格列表
     */
    public ArrayList<OrderRoomStockVO> getRoomStocks(DateUtil start, DateUtil end, String hotelID) {
        //保存结果
        ArrayList<OrderRoomStockVO> result = new ArrayList<OrderRoomStockVO>();
        //
        for (HotelRoomPO hotelRoomPO : hotelClientNetworkService.getRoom(hotelID)) {
            //先得到符合日期区间的roomStockPO
            ArrayList<RoomStockPO> roomStockPOs = getRoomStockPOs(new RoomChangeInfoVO(start, end, hotelID, hotelRoomPO.getRoomType(), 0));
            //寻找最小房间量
            int minQuantity = Integer.MAX_VALUE;
            for (RoomStockPO roomStockPO : roomStockPOs) {
                minQuantity = Math.min(roomStockPO.getAvailableQuantity(), minQuantity);
            }
            result.add(new OrderRoomStockVO(hotelRoomPO.getRoomType(),hotelRoomPO.getPrice(),minQuantity));
        }
        return result;
    }
}
