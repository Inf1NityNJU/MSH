package blservice.hotelblservice;

import util.InfoInvalidException;
import util.ResultMessage;
import vo.AssessmentVO;
import vo.HotelRoomVO;
import vo.RoomChangeInfoVO;

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
    public ResultMessage addScoreToHotelByHotelID(double score,String hotelID);
}
