package network.promotionnetworkservice;

import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface PromotionServerNetworkService extends Remote {
    public ResultMessage addPromotion(PromotionPO promotionpo) throws RemoteException;

    public ResultMessage deletePromotion(String promotionID) throws RemoteException;

    public ResultMessage updatePromotion(PromotionPO newPromotionPO) throws RemoteException;

    public PromotionPO searchByPromotionID(String promotionID) throws RemoteException;

    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) throws RemoteException;

    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) throws RemoteException;

    public ArrayList<PromotionPO> searchWebPromotions() throws RemoteException;
}
