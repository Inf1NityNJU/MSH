package bl.hotelbl;

import dataimpl.Hotel.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Hotel {
    private HotelDataService hotelDataService;
    private Map<String,Hotel_DetailVO> cache;

    protected Hotel() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
        cache=new HashMap<String, Hotel_DetailVO>();
    }

    /**
     * 通过指定的筛选条件筛选酒店
     *
     * @param flags
     * @return 符合条件的酒店列表
     */
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags) {
        //TODO
        return null;
    }

    /**
     * 通过酒店ID查找酒店
     *
     * @param hotelID
     * @return 符合ID的酒店VO
     */
    public Hotel_DetailVO getHotel(String hotelID) {
        //先在cache中寻找
        Hotel_DetailVO hotelDetailVO=cache.get(hotelID);
        if(hotelDetailVO!=null){
            return hotelDetailVO;
        }
        //cache中未找到
        //TODO
        return null;
    }

    /**
     * 修改指定酒店信息
     *
     * @param hvo
     * @return 修改成功与否
     */
    public ResultMessage updateHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO=voToPO(hvo);
        ResultMessage resultMessage=hotelDataService.updateHotel(hotelPO);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            cache.remove(hotelPO.getID());
            cache.put(hvo.ID,hvo);
        }
        return resultMessage;
    }

    /**
     * 添加酒店信息
     *
     * @param hvo
     * @return 添加成功与否
     *
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO=voToPO(hvo);
        ResultMessage resultMessage=hotelDataService.addHotel(hotelPO);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            cache.put(hvo.ID,hvo);
        }
        return resultMessage;
    }

    /**
     * 删除酒店信息
     *
     * @param hotelID
     * @return 删除成功与否
     *
     */
    public ResultMessage deleteHotel(String hotelID) {
        ResultMessage resultMessage=hotelDataService.deleteHotel(hotelID);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            cache.remove(hotelID);
        }
        return resultMessage;
    }

    /**
     * 将hotelVO转换为hotelPO
     *
     * @param hotel_detailVO
     * @return hotelPO
     */
    private HotelPO voToPO(Hotel_DetailVO hotel_detailVO) {
        return hotel_detailVO == null ? null : new HotelPO(hotel_detailVO.ID
                , hotel_detailVO.name
                , hotel_detailVO.address
                , hotel_detailVO.place
                , hotel_detailVO.star
                , hotel_detailVO.introduction
                , hotel_detailVO.facilities);
    }


    /**
     * 将hotelPO转换为hotelVO
     * @param hotelPO
     * @return hotel_DetailVO
     */
    private Hotel_DetailVO poToVO(HotelPO hotelPO){
        return hotelPO==null? null:new Hotel_DetailVO(hotelPO.getID()
                ,hotelPO.getName()
                ,hotelPO.getAddress()
                ,hotelPO.getPlace()
                ,hotelPO.getStar()
                ,hotelPO.getIntroduction()
                ,hotelPO.getFacilities()
                ,null);
    }
}
