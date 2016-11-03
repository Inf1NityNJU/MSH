package vo;

/**
 * Created by SilverNarcissus on 16/10/22.
 */
public class Hotel_BriefVO {
    public String ID;
    public String name;
    public String address;
    public int star;
    public AssessmentVO assessmentVO;

    public Hotel_BriefVO(String ID, String name, String address, AssessmentVO assessmentVO) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.star = star;
        this.assessmentVO=assessmentVO;
    }
}
