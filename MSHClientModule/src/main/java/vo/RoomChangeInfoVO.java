package vo;

import util.DateUtil;
import util.RoomType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/11/12.
 */
public class RoomChangeInfoVO {

    public DateUtil start;
    public DateUtil end;
    public String hotelID;
    public RoomType type;
    public int quantity;

    public RoomChangeInfoVO(DateUtil start, DateUtil end, String hotelID, RoomType type, int quantity) {
        this.start = start;
        this.end = end;
        this.hotelID = hotelID;
        this.type = type;
        this.quantity = quantity;
    }
    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof RoomStockVO) {
            RoomChangeInfoVO roomChangeInfoVO = (RoomChangeInfoVO) o;
            return compareData(roomChangeInfoVO);
        }
        return false;
    }

    /**
     * 生成对象的hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return quantity;
    }

    /**
     * 分别比较每个数据
     *
     * @param roomChangeInfoVO
     * @return 比较结果
     */
    private boolean compareData(RoomChangeInfoVO roomChangeInfoVO) {
        return judgeEqual(start, roomChangeInfoVO.start)
                && judgeEqual(end, roomChangeInfoVO.end)
                && judgeEqual(hotelID,roomChangeInfoVO.hotelID)
                && judgeEqual(type,roomChangeInfoVO.type)
                && judgeEqual(quantity,roomChangeInfoVO.quantity);
    }
}
