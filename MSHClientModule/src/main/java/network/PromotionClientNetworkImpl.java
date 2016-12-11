package network;

import network.promotionnetworkservice.PromotionServerNetworkService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class PromotionClientNetworkImpl implements PromotionClientNetworkService {
    private PromotionServerNetworkService promotionServerNetworkService;

    public PromotionClientNetworkImpl() {
        try {
            promotionServerNetworkService = (PromotionServerNetworkService) Naming.lookup("PromotionServerNetWorkService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        try {
            return promotionServerNetworkService.addPromotion(promotionpo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        try {
            return promotionServerNetworkService.deletePromotion(promotionID);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        try {
            return promotionServerNetworkService.updatePromotion(newPromotionPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        try {
            return promotionServerNetworkService.searchByPromotionID(promotionID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) {
        try {
            return promotionServerNetworkService.searchPromotionsByType(promotionType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) {
        try {
            return promotionServerNetworkService.searchHotelPromotions(hotelID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<PromotionPO> searchWebPromotions() {
        try {
            return promotionServerNetworkService.searchWebPromotions();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
