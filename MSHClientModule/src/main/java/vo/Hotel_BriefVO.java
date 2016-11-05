package vo;

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
    public AssessmentVO assessmentVO;

    public Hotel_BriefVO(String ID, String name, String address, AssessmentVO assessmentVO) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.star = star;
        this.assessmentVO=assessmentVO;
    }
}
