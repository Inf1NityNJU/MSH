package vo;

import util.DateUtil;

/**
 * Created by Sorumi on 16/12/17.
 */
public class Assessment_HotelVO {

    /**
     * 客户名称
     */
    public String clientName;

    /**
     * 服务评分
     */
    public int serviceScore;

    /**
     * 设施评分
     */
    public int facilityScore;

    /**
     * 卫生评分
     */
    public int healthScore;

    /**
     * 位置评分
     */
    public int locationScore;

    /**
     * 评价
     */
    public String comment;

    /**
     * 日期
     */
    public DateUtil date;

    public Assessment_HotelVO(String clientName, int serviceScore, int facilityScore, int healthScore, int locationScore, String comment, DateUtil date) {
        this.clientName = clientName;
        this.serviceScore = serviceScore;
        this.facilityScore = facilityScore;
        this.healthScore = healthScore;
        this.locationScore = locationScore;
        this.comment = comment;
        this.date = date;
    }
}
