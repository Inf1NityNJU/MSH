package vo;

import util.Place;

/**
 * Created by Kray on 2016/10/12.
 */
public class Hotel_DetailVO {
    public String ID;
    public String name;
    public String address;
    public Place place;
    public int star;
    public String introduction;
    public String facilities;
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

