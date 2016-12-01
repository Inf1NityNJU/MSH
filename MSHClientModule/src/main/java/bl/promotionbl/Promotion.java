package bl.promotionbl;

//import dataimpl.promotiondataimpl.PromotionDataServiceFactory;
//import dataservice.promotiondataservice.PromotionDataService;
import network.PromotionClientNetworkImpl;
import network.PromotionDataService;
import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;
import util.ResultMessage;
import vo.*;


import java.util.ArrayList;

/**
 * Created by vivian on 16/11/2.
 */
public class Promotion{
//    private PromotionDataService promotionDataService = PromotionDataServiceFactory.getPromotionDataService();

    private PromotionDataService promotionDataService;

    public Promotion(){
        promotionDataService = new PromotionClientNetworkImpl();
    }
    /**
     * 增加一个促销策略
     * @param promotionVO
     * @return 添加成功与否
     */
    public ResultMessage add(PromotionVO promotionVO){
        PromotionPO promotionPO = promotionVO.toPO();
        return promotionDataService.addPromotion(promotionPO);
    }

    /**
     * 删除一个促销策略
     * @param promotionID
     * @return 删除成功与否
     */
    public ResultMessage delete(String promotionID){
        return promotionDataService.deletePromotion(promotionID);
    }

    /**
     * 更新一个促销策略
     * @param promotionVO
     * @return 更新成功与否
     */
    public ResultMessage update(PromotionVO promotionVO){
        PromotionPO promotionPO = promotionVO.toPO();
        return promotionDataService.updatePromotion(promotionPO);
    }

    /**
     * 精确查找某个策略
     * @param promotionID
     * @return 符合条件的策略VO
     */
    public PromotionVO searchByID(String promotionID) {
        PromotionPO promotionPO = promotionDataService.searchByPromotionID(promotionID);
        return this.POToVO(promotionPO);
    }

    /**
     * 搜索某一类型的所有策略
     * @param promotionType
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<PromotionVO> search(PromotionType promotionType){
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(promotionType);
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
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchHotelPromotions(HotelID);
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
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchWebPromotions();
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
                return new Promotion_BirthdayVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),promotionPO.getHotelID());
            case Hotel_SpecilaDate:
                return new Promotion_HotelSpecialDateVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getHotelID());
            case Hotel_RoomQuantity:
                return new Promotion_RoomQuantityVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getHotelID(),promotionPO.getRoomQuantity());
            case Hotel_Enterprise:
                return new Promotion_EnterpriseVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getCompanyName(),promotionPO.getHotelID());
            case Web_ClientGrade:
                return new Promotion_ClientGradeVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getClientGrade());
            case Web_SpecilPlace:
                return new Promotion_SpecialPlaceVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getPlace());
            case Web_SpecilaDate:
                return new Promotion_WebSpecialDateVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()));
            default:return null;
        }
    }

    private DateUtil date(String s){
        int first = Integer.valueOf(s.substring(0,4));
        int second = Integer.valueOf(s.substring(5,7));
        int third = Integer.valueOf(s.substring(8,10));
        return new DateUtil(first,second,third);
    }

}
