package blservice.hotelblservice;

import util.InfoInvalidException;
import util.ResultMessage;
import vo.HotelRoomVO;
import vo.RoomChangeInfoVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public interface HotelBLInfo {

    /**
     * 取得指定酒店的房间信息
     * @param hotelID
     * @return
     */
    public ArrayList<HotelRoomVO> getRoom(String hotelID);

    /**
     * 更新房间数量
     * @param roomChangeInfoVO
     * @return 更新成功与否
     */
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO);
}
