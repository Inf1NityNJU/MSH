package po;

import util.Place;

/**
 * Created by Sorumi on 16/10/11.
 */
public class HotelPO {
    /**
     * 酒店编号
     */
    private String ID;
    /**
     * 酒店名称
     */
    private String name;
    /**
     * 酒店地址
     */
    private String address;
    /**
     * 酒店所属商圈
     */
    private Place place;
    /**
     * 酒店星级
     */
    private int star;
    /**
     * 酒店简介
     */
    private String introduction;
    /**
     * 酒店设施
     */
    private String facilities;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    /**
     *
     * @param ID
     * @param name
     * @param address
     * @param place
     * @param star
     * @param introduction
     * @param facilities
     */
    public HotelPO(String ID, String name, String address, Place place, int star, String introduction, String facilities) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.place = place;
        this.star = star;
        this.introduction = introduction;
        this.facilities = facilities;
    }

    public HotelPO(){

    }
}
