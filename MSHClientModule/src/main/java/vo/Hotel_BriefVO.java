package vo;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by SilverNarcissus on 16/10/22.
 */
public class Hotel_BriefVO {
    /**
     * 酒店编号
     */
    public String ID;
    /**
     * 酒店名称
     */
    public String name;
    /**
     * 酒店地址
     */
    public String address;
    /**
     * 酒店星级
     */
    public int star;
    /**
     * 酒店评分评价
     */
    public double score;

    /**
     * 最小价格
     */
    public double minPrice;

    public Hotel_BriefVO(String ID, String name, String address, int star, double score, double minPrice) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.star = star;
        this.score = score;
        this.minPrice = minPrice;
    }

    public Hotel_BriefVO(Hotel_DetailVO hotel_detailVO) {
        this(hotel_detailVO.ID, hotel_detailVO.name, hotel_detailVO.address, hotel_detailVO.star, hotel_detailVO.score, hotel_detailVO.minPrice);
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Hotel_BriefVO) {
            Hotel_BriefVO h_bvo = (Hotel_BriefVO) o;
            return compareData(h_bvo);
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
        return star;
    }

    /**
     * 分别比较每个数据
     *
     * @param h_bvo
     * @return 比较结果
     */
    private boolean compareData(Hotel_BriefVO h_bvo) {
        return judgeEqual(ID, h_bvo.ID)
                && judgeEqual(name, h_bvo.name)
                && judgeEqual(address, h_bvo.address)
                && judgeEqual(star, h_bvo.star)
                && judgeEqual(score, h_bvo.score);
    }

    @Override
    public String toString() {
        String result = "ID: " + ID  + " Address: " + address +
                " Name: " + name + " Star: " + star + " minPrice: " + minPrice ;
        return result;
    }
}
