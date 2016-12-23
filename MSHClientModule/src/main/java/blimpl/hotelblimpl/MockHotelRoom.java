package blimpl.hotelblimpl;


import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/2.
 * All Done on 16/11/26
 */
public class MockHotelRoom extends HotelRoom {
    /**
     * 通过酒店ID查找酒店房间(mock)
     * @param hotelID
     * @return 符合ID的酒店房间VO
     */
    @Override
    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        if (hotelID.equals("000000")) {
            System.out.println("Get hotel room Success!");
            HotelRoomVO v=new HotelRoomVO("00000000",RoomType.DoubleRoom,350.0,5,null);
            ArrayList<HotelRoomVO> hotelRoomVOArrayList=new ArrayList<HotelRoomVO>();
            hotelRoomVOArrayList.add(v);
            return hotelRoomVOArrayList;
        }
        return null;
    }

    /**
     * 修改指定酒店房间信息(mock)
     * @param rvo
     * @return 修改成功与否
     */
    @Override
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        if (rvo != null) {
            System.out.println("Update hotel room Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    /**
     * 添加酒店房间信息(mock)
     * @param rvo
     * @return 添加成功与否
     */
    @Override
    public ResultMessage addRoom(HotelRoomVO rvo) {
        if (rvo != null) {
            System.out.println("Add hotel room Success!");
            return ResultMessage.SUCCESS;
        }
        return  ResultMessage.EXIST;
    }

    /**
     * 删除酒店房间信息(mock)
     * @param hotelID
     * @return 删除成功与否
     */
    @Override
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) {
        if (hotelID.equals("000000")) {
            System.out.println("Delete Success!");
            return ResultMessage.SUCCESS;
        }
        return  ResultMessage.NOT_EXIST;
    }
}
