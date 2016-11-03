package po;

import util.Place;

/**
 * Created by Sorumi on 16/10/11.
 */
public class HotelPO {
    private String ID;
    private String name;
    private String address;
    private Place place;
    private int star;
    private String introduction;
    private String Facilities;

    /**
     *
     * @return
     */
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

    public int getStars() {
        return star;
    }

    public void setStars(int stars) {
        this.star = stars;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
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
        Facilities = facilities;
    }
}
