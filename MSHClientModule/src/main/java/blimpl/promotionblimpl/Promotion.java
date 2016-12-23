package blimpl.promotionblimpl;

//import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
//import dataservice.promotiondataservice.PromotionClientNetworkService;
import network.PromotionClientNetworkImpl;
import network.PromotionClientNetworkService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;
import vo.*;


import java.util.ArrayList;

/**
 * Created by vivian on 16/11/2.
 */
public class Promotion{
//    private PromotionClientNetworkService promotionClientNetworkService = PromotionDataServiceFactory.getPromotionDataService();

    private PromotionClientNetworkService promotionClientNetworkService;

    public Promotion(){
        promotionClientNetworkService = new PromotionClientNetworkImpl();
    }
    /**
     * 增加一个促销策略
     * @param promotionVO
     * @return 添加成功与否
     */
    public ResultMessage add(PromotionVO promotionVO){
        PromotionPO promotionPO = promotionVO.toPO();
        return promotionClientNetworkService.addPromotion(promotionPO);
    }

    /**
     * 删除一个促销策略
     * @param promotionID
     * @return 删除成功与否
     */
    public ResultMessage delete(String promotionID){
        return promotionClientNetworkService.deletePromotion(promotionID);
    }

    /**
     * 更新一个促销策略
     * @param promotionVO
     * @return 更新成功与否
     */
    public ResultMessage update(PromotionVO promotionVO){
        PromotionPO promotionPO = promotionVO.toPO();
        return promotionClientNetworkService.updatePromotion(promotionPO);
    }

    /**
     * 精确查找某个策略
     * @param promotionID
     * @return 符合条件的策略VO
     */
    public PromotionVO searchByID(String promotionID) {
        PromotionPO promotionPO = promotionClientNetworkService.searchByPromotionID(promotionID);
        return this.POToVO(promotionPO);
    }

    /**
     * 搜索某一类型的所有策略
     * @param promotionType
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<PromotionVO> search(PromotionType promotionType){
        ArrayList<PromotionPO> promotionPOs = promotionClientNetworkService.searchPromotionsByType(promotionType);
        ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        for(int i=0;i<promotionPOs.size();i++){
            promotionVOs.add(POToVO(promotionPOs.get(i)));
        }
        return promotionVOs;
    }

    /**
     * 搜索某个酒店的所有促销策略
     * @param HotelID
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<PromotionVO> searchHotelPromotions(String HotelID){
        ArrayList<PromotionPO> promotionPOs = promotionClientNetworkService.searchHotelPromotions(HotelID);
        ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        for(int i=0;i<promotionPOs.size();i++){
            promotionVOs.add(POToVO(promotionPOs.get(i)));
        }
        return promotionVOs;
    }

    /**
     * 搜索所有的网站促销策略
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<PromotionVO> searchWebPromotions(){
        ArrayList<PromotionPO> promotionPOs = promotionClientNetworkService.searchWebPromotions();
        ArrayList<PromotionVO> promotionVOs = new ArrayList<PromotionVO>();
        for(int i=0;i<promotionPOs.size();i++){
            promotionVOs.add(POToVO(promotionPOs.get(i)));
        }
        return promotionVOs;
    }

    /**
     * 将PO转换成易于显示的VO对象
     * @param promotionPO
     * @return
     */
    private PromotionVO POToVO(PromotionPO promotionPO){
        switch (promotionPO.getPromotionType()){
            case Hotel_Birthday:
                return new Promotion_BirthdayVO(promotionPO);
            case Hotel_SpecilaDate:
                return new Promotion_HotelSpecialDateVO(promotionPO);
            case Hotel_RoomQuantity:
                return new Promotion_RoomQuantityVO(promotionPO);
            case Hotel_Enterprise:
                return new Promotion_EnterpriseVO(promotionPO);
            case Web_ClientGrade:
                return new Promotion_ClientGradeVO(promotionPO);
            case Web_SpecilPlace:
                return new Promotion_SpecialPlaceVO(promotionPO);
            case Web_SpecilaDate:
                return new Promotion_WebSpecialDateVO(promotionPO);
            default:return null;
        }
    }


}
