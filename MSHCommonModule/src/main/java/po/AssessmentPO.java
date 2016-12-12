package po;

import java.io.Serializable;

/**
 * Created by Sorumi on 16/11/1.
 */
public class AssessmentPO implements Serializable {

    /**
     * 订单ID
     */
    private String orderID;

    /**
     * 服务评分
     */
    private int serviceScore;

    /**
     * 设施评分
     */
    private int facilityScore;

    /**
     * 卫生评分
     */
    private int healthScore;

    /**
     * 位置评分
     */
    private int locationScore;

    /**
     * 评价
     */
    private String comments;

    /**
     * 作出评价的客户ID
     */
    private String clientID;


    public AssessmentPO() {

    }


    public AssessmentPO(String orderID, int serviceScore, int facilityScore, int healthScore, int locationScore, String comments, String clientID) {
        this.orderID = orderID;
        this.serviceScore = serviceScore;
        this.facilityScore = facilityScore;
        this.healthScore = healthScore;
        this.locationScore = locationScore;
        this.comments = comments;
        this.clientID = clientID;
    }

    public String getOrderID() {

        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(int serviceScore) {
        this.serviceScore = serviceScore;
    }

    public int getFacilityScore() {
        return facilityScore;
    }

    public void setFacilityScore(int facilityScore) {
        this.facilityScore = facilityScore;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getLocationScore() {
        return locationScore;
    }

    public void setLocationScore(int locationScore) {
        this.locationScore = locationScore;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comment) {
        this.comments = comment;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AssessmentPO) {
            AssessmentPO assessmentPO = (AssessmentPO) o;
            return assessmentPO.getOrderID().equals(orderID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return orderID.hashCode();
    }
}
