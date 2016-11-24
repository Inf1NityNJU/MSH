package bl.promotionbl;

import blservice.promotionblservice.PromotionBLService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.promotiondataservice.PromotionDataService_Stub;
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
    private PromotionDataService promotionDataService = new PromotionDataService_Stub();

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
    public ArrayList<Promotion_HotelVO> search(PromotionType promotionType){
        ArrayList<PromotionPO> promotionPOs = promotionDataService.searchPromotionsByType(promotionType);

        return null;
    }

    /**
     * 搜索某个酒店的所有促销策略
     * @param HotelID
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<Promotion_HotelVO> searchHotelPromotions(String HotelID){
        return null;
    }

    /**
     * 搜索所有的网站促销策略
     * @return 符合条件的策略VO的列表
     */
    public ArrayList<Promotion_WebVO> searchWebPromotions(){
        return null;
    }

    private PromotionVO POToVO(PromotionPO promotionPO){
        switch (promotionPO.getPromotionType()){
            case Hotel_Birthday:
                return new Promotion_BirthdayVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),promotionPO.getHotelID(),this.date(promotionPO.getBirthday()));
            case Hotel_SpecilaDate:
                return new Promotion_HotelSpecialDateVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getHotelID());
            case Hotel_RoomQuantity:
                return new Promotion_RoomQuantityVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getHotelID(),promotionPO.getRoomQuantity());
            case Hotel_Company:
                return new Promotion_CompanyVO(promotionPO.getPromotionID(),promotionPO.getPromotionType(),promotionPO.getPromotionDiscount(),this.date(promotionPO.getStartDate()),this.date(promotionPO.getEndDate()),promotionPO.getCompanyName(),promotionPO.getHotelID());
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
