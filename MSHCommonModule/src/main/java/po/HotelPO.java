package po;

import util.City;
import util.Place;

import java.io.Serializable;

/**
 * Created by Sorumi on 16/10/11.
 */
public class HotelPO implements Serializable {
    /**
     * 酒店编号
     */
    private String ID;
    /**
     * 酒店名称
     */
    private String name;
    /**
     * 酒店所属城市
     */
    private City city;
    /**
     * 酒店所属商圈
     */
    private Place place;
    /**
     * 酒店地址
     */
    private String address;
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
    /**
     * 酒店评分
     */
    private double score;
    /**
     * 评分数量
     */
    private int scoreAmount;

    public HotelPO(String ID, String name, City city, Place place, String address, int star, String introduction, String facilities, double score, int scoreAmount) {
        this(ID, name, address, place, star, introduction, facilities);
        this.score = score;
        this.scoreAmount = scoreAmount;
        this.city = city;
    }

    public HotelPO(String ID, String name, String address, Place place, int star, String introduction, String facilities) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.place = place;
        this.star = star;
        this.introduction = introduction;
        this.facilities = facilities;
        this.city = City.NanJing;
    }

    public HotelPO() {

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(int scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof HotelPO) {
            HotelPO hotelPO = (HotelPO) o;
            return hotelPO.getID().equals(ID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
