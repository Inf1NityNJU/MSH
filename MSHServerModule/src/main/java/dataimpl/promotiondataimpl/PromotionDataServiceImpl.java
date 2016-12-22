package dataimpl.promotiondataimpl;

import datahelper.DataHelper;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/11/22.
 */
public class PromotionDataServiceImpl implements PromotionDataService{
    private DataHelper<PromotionPO> promotionDataHelper;

    protected PromotionDataServiceImpl(DataHelper<PromotionPO> dataHelper){
        this.promotionDataHelper = dataHelper;
    }


    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        String promotionID = "";
        if (promotionpo.getPromotionType()==PromotionType.Web_SpecilaDate
                ||promotionpo.getPromotionType()==PromotionType.Web_ClientGrade
                ||promotionpo.getPromotionType()==PromotionType.Web_SpecilPlace){
            promotionID = getWebPromotionID();
        }else {
            promotionID = getHotelPromotionID();
        }
        promotionpo.setPromotionID(promotionID);
        return promotionDataHelper.save(promotionpo);
    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        return promotionDataHelper.delete("promotionID",promotionID);
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        return promotionDataHelper.update(newPromotionPO);
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return promotionDataHelper.exactlyQuery("promotionID", promotionID);
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) {
        ArrayList<PromotionPO> promotionPOs = promotionDataHelper.fullMatchQuery("promotionType", promotionType);
        return promotionPOs;
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) {
        ArrayList<PromotionPO> promotionPOs = promotionDataHelper.fullMatchQuery("hotelID", hotelID);
        return promotionPOs;
    }

    @Override
    public ArrayList<PromotionPO> searchWebPromotions() {
        ArrayList<PromotionPO> promotionPOs1 = promotionDataHelper.fullMatchQuery("promotionType", PromotionType.Web_ClientGrade);
        ArrayList<PromotionPO> promotionPOs2 = promotionDataHelper.fullMatchQuery("promotionType", PromotionType.Web_SpecilaDate);
        ArrayList<PromotionPO> promotionPOs3 = promotionDataHelper.fullMatchQuery("promotionType", PromotionType.Web_SpecilPlace);
        promotionPOs1.addAll(promotionPOs2);
        promotionPOs1.addAll(promotionPOs3);
        return promotionPOs1;
    }

    private String getHotelPromotionID(){
        ArrayList<PromotionPO> promotionPOs = promotionDataHelper.prefixMatchQuery("promotionID", "1");
        if(promotionPOs.size()==0){
            return "10001";
        }else {
            String tempID = promotionPOs.get(promotionPOs.size()-1).getPromotionID();
            int count = Integer.parseInt(tempID)+1;
            return count+"";
        }
    }

    private String getWebPromotionID(){
        ArrayList<PromotionPO> promotionPOs = promotionDataHelper.prefixMatchQuery("promotionID", "2");
        if(promotionPOs.size()==0){
            return "20001";
        }else {
            String tempID = promotionPOs.get(promotionPOs.size()-1).getPromotionID();
            int count = Integer.parseInt(tempID)+1;
            return count+"";
        }
    }


}
