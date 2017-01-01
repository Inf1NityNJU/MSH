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

    /**
     * 增加一种新的营销策略
     * @param pvo
     * @return
     */
    public ResultMessage addPromotion(PromotionPO pvo) throws RemoteException;

    /**
     * 删除制定营销策略
     * @param promotionID
     * @return
     */
    public ResultMessage deletePromotion(String promotionID) throws RemoteException;

    /**
     * 更新某条酒店/网站促销策略的信息
     * @param newPvo
     * @return
     */
    public ResultMessage updatePromotion(PromotionPO newPvo) throws RemoteException;

    /**
     * 查找某条酒店/网站促销策略
     * @param promotionID
     * @return
     */
    public PromotionPO searchByPromotionID(String promotionID) throws RemoteException;

    /**
     * 搜索某个种类的所有促销策略
     * @param promotionType
     * @return
     */
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) throws RemoteException;

    /**
     * 查看某个酒店的所有促销策略
     * @param HotelID
     * @return
     */
    public ArrayList<PromotionPO> searchHotelPromotions(String HotelID) throws RemoteException;

    /**
     * 查看网站的所有促销策略
     * @return
     */
    public ArrayList<PromotionPO> searchWebPromotions() throws RemoteException;
}
