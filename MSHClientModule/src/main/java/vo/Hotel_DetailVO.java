package vo;

import util.City;
import util.Place;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by Kray on 2016/10/12.
 */
public class Hotel_DetailVO {
    /**
     * 酒店编号
     */
    public String ID;
    /**
     * 酒店名称
     */
    public String name;
    /**
     * 酒店所属城市
     */
    public City city;
    /**
     * 酒店所属商圈
     */
    public Place place;
    /**
     * 酒店地址
     */
    public String address;
    /**
     * 酒店星级
     */
    public int star;
    /**
     * 酒店简介
     */
    public String introduction;
    /**
     * 酒店设施
     */
    public String facilities;
    /**
     * 酒店评分评价
     */
    public AssessmentVO assessmentVO;
    /**
     * 最小房间价格
     */
    public double minPrice;
    /**
     * 最大房间价格
     */
    public double maxPrice;
    /**
     * 酒店评分
     */
    public double score;
    /**
     * 评分数量
     */
    public int scoreAmount;


    public Hotel_DetailVO(String ID, String name, City city, String address, Place place, int star, String introduction, String facilities, AssessmentVO assessmentVO, double score, int scoreAmount) {
        this.ID = ID;
        this.name = name;
        this.city = city;
        this.place = place;
        this.address = address;
        this.star = star;
        this.introduction = introduction;
        this.facilities = facilities;
        this.assessmentVO = assessmentVO;
        this.score = score;
        this.scoreAmount = scoreAmount;
    }

    /**
     * 比较两个VO
     *
     * @param o
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Hotel_DetailVO) {
            Hotel_DetailVO h_dvo = (Hotel_DetailVO) o;
            return compareData(h_dvo);
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
     * @param h_dvo
     * @return 比较结果
     */
    private boolean compareData(Hotel_DetailVO h_dvo) {
        return judgeEqual(ID, h_dvo.ID)
                && judgeEqual(name, h_dvo.name)
                && judgeEqual(address, h_dvo.address)
                && judgeEqual(place, h_dvo.place)
                && judgeEqual(star, h_dvo.star)
                && judgeEqual(introduction, h_dvo.introduction)
                && judgeEqual(facilities, h_dvo.facilities)
                && judgeEqual(assessmentVO, h_dvo.assessmentVO);
    }

    @Override
    public String toString() {
        String result = "ID: " + ID + " City: " + city + " Place: " + place + " Address: " + address +
                " Name: " + name + " Star: " + star + " minPrice: " + minPrice +
                " Facilities: "+facilities+" Introduction: "+introduction+
                " maxPrice: " + maxPrice + " score: " + score + " scoreAmount: " + scoreAmount;
        return result;
    }
}

