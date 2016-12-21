package dataimpl.orderdataimpl;

import datahelper.DataHelper;
import dataservice.orderdataservice.OrderDataService;
import po.AssessmentPO;
import po.OrderPO;
import po.OrderRoomPO;
import util.*;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/11/28.
 */
public class OrderDataServiceImpl implements OrderDataService {

    private DataHelper<OrderPO> orderDataHelper;
    private DataHelper<OrderRoomPO> orderRoomDataHelper;
    private DataHelper<AssessmentPO> assessmentDataHelper;

    protected OrderDataServiceImpl(DataHelper<OrderPO> orderDataHelper, DataHelper<OrderRoomPO> orderRoomDataHelper, DataHelper<AssessmentPO> assessmentDataHelper) {
        this.orderDataHelper = orderDataHelper;
        this.orderRoomDataHelper = orderRoomDataHelper;
        this.assessmentDataHelper = assessmentDataHelper;
    }

    @Override
    public ResultMessage addOrder(OrderPO opo) {
        return orderDataHelper.save(opo);
    }

    @Override
    public ResultMessage updateOrder(OrderPO opo) {
        return orderDataHelper.update(opo);
    }

    @Override
    public ResultMessage addOrderRoom(OrderRoomPO orpo) {
        return orderRoomDataHelper.save(orpo);
    }

    @Override
    public OrderPO searchOrderByOrderID(String orderID) {
        return orderDataHelper.exactlyQuery("orderID", orderID);
    }

    @Override
    public ArrayList<OrderPO> searchOrder(OrderState orderState) {
        ArrayList<CriteriaClause> queries = new ArrayList<>();

        if (orderState != null) {
            CriteriaClauseImpl stateQuery = CriteriaClauseImpl.createSingleValueQuery("state", orderState, QueryMethod.Full);
            queries.add(stateQuery);
        }

        return orderDataHelper.multiCriteriaQuery(queries);
    }

    @Override
    public ArrayList<OrderPO> searchOrderByClientID(String clientID, OrderState orderState) {
        ArrayList<CriteriaClause> queries = new ArrayList<>();

        CriteriaClauseImpl clientIDQuery = CriteriaClauseImpl.createSingleValueQuery("clientID", clientID, QueryMethod.Full);
        queries.add(clientIDQuery);

        if (orderState != null) {
            CriteriaClauseImpl stateQuery = CriteriaClauseImpl.createSingleValueQuery("state", orderState, QueryMethod.Full);
            queries.add(stateQuery);
        }

        return orderDataHelper.multiCriteriaQuery(queries);
    }

    @Override
    public ArrayList<OrderPO> searchOrderByHotelID(String hotelID, OrderState orderState) {
        ArrayList<CriteriaClause> queries = new ArrayList<>();

        CriteriaClauseImpl hotelIDQuery = CriteriaClauseImpl.createSingleValueQuery("hotelID", hotelID, QueryMethod.Full);
        queries.add(hotelIDQuery);

        if (orderState != null) {
            CriteriaClauseImpl stateQuery = CriteriaClauseImpl.createSingleValueQuery("state", orderState, QueryMethod.Full);
            queries.add(stateQuery);
        }

        return orderDataHelper.multiCriteriaQuery(queries);
    }

    @Override
    public ArrayList<OrderRoomPO> searchOrderRoomByOrderID(String orderID) {
        return orderRoomDataHelper.fullMatchQuery("orderID", orderID);
    }

    @Override
    public ResultMessage addAssessment(AssessmentPO assessment) {
        return assessmentDataHelper.save(assessment);
    }

    @Override
    public ResultMessage updateAssessment(AssessmentPO assessment) {
        return assessmentDataHelper.update(assessment);
    }

    @Override
    public AssessmentPO searchAssessmentByOrderID(String orderID) {
        return assessmentDataHelper.exactlyQuery("orderID", orderID);
    }

    @Override
    public int searchOrderQuantity(String orderIDPrefix) {
        return orderDataHelper.prefixMatchQuery("orderID", orderIDPrefix).size();
    }
}
