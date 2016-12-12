package network.promotionnetwork;

import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
import dataservice.promotiondataservice.PromotionDataService;
import network.promotionnetworkservice.PromotionServerNetworkService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class PromotionServerNetworkImpl extends UnicastRemoteObject implements PromotionServerNetworkService {
    private PromotionDataService promotionDataService;

    public PromotionServerNetworkImpl() throws RemoteException {
    }


    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.addPromotion(promotionpo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.deletePromotion(promotionID);
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.updatePromotion(newPromotionPO);
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.searchByPromotionID(promotionID);
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.searchPromotionsByType(promotionType);
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.searchHotelPromotions(hotelID);
    }

    @Override
    public ArrayList<PromotionPO> searchWebPromotions() throws RemoteException{
        promotionDataService = PromotionDataServiceFactory.getPromotionDataService();
        return promotionDataService.searchWebPromotions();
    }
}
