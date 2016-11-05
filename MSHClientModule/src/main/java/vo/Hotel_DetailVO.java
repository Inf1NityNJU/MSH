package vo;

import util.Place;

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
     * 酒店地址
     */
    public String address;
    /**
     * 酒店所属商圈
     */
    public Place place;
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

    public Hotel_DetailVO(String ID, String name, String address, Place place, int star, String introduction, String facilities, AssessmentVO assessmentVO) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.place = place;
        this.star = star;
        this.introduction = introduction;
        this.facilities = facilities;
        this.assessmentVO=assessmentVO;
    }
}

