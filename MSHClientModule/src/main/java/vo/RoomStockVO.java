package vo;

import util.DateUtil;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/14.
 */
public class RoomStockVO {
    public int availableQuantity;
    public DateUtil date;

    public RoomStockVO(int availableQuantity, DateUtil date) {
        this.availableQuantity = availableQuantity;
        this.date = date;
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
            RoomStockVO roomStockVO = (RoomStockVO) o;
            return compareData(roomStockVO);
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
        return availableQuantity;
    }

    /**
     * 分别比较每个数据
     *
     * @param roomStockVO
     * @return 比较结果
     */
    private boolean compareData(RoomStockVO roomStockVO) {
        return judgeEqual(availableQuantity, roomStockVO.availableQuantity)
                && judgeEqual(date, roomStockVO.date);
    }
}
