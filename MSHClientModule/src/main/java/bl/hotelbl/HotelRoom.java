package bl.hotelbl;

import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/2.
 */
public class HotelRoom {

    /**
     * 通过酒店ID查找酒店房间
     * @param hotelID
     * @return 符合ID的酒店房间VO
     */
    public ArrayList<HotelRoomVO> getRoom(String hotelID){
        return null;
    }

    /**
     * 修改指定酒店房间信息
     * @param rvo
     * @return 修改成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage updateHotelRoomInfo(HotelRoomVO rvo) throws InfoInvalidException {
        return  null;
    }

    /**
     * 添加酒店房间信息
     * @param rvo
     * @return 添加成功与否
     * @throws InfoInvalidException
     */
    public ResultMessage addRoom(HotelRoomVO rvo) throws InfoInvalidException {
        return  null;
    }

    /**
     * 删除酒店房间信息
     * @param hotelID
     * @return 删除成功与否
     * @throws HotelNotFoundException
     */
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) throws HotelNotFoundException {
        return  null;
    }
}
