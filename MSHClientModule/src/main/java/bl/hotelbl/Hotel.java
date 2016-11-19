package bl.hotelbl;

import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.Hotel_DetailVO;

import java.util.*;

/**
 * Created by Sorumi on 16/10/30.
 */
public class Hotel {
    private HotelDataService hotelDataService;
    private Map<String, Hotel_DetailVO> cache;

    protected Hotel() {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
        cache = new HashMap<String, Hotel_DetailVO>();
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
        PricePair pricePair = findMinAndMaxPrice(hotelID);
        //先在cache中寻找
        Hotel_DetailVO hotelDetailVO = cache.get(hotelID);
        if (hotelDetailVO != null) {
            hotelDetailVO.minPrice = pricePair.minPrice;
            hotelDetailVO.maxPrice = pricePair.maxPrice;
            return hotelDetailVO;
        }
        //cache中未找到
        HotelPO hotelPO = hotelDataService.getHotel(hotelID);
        Hotel_DetailVO hotel_detailVO = poToVO(hotelPO);
        //
        if(hotel_detailVO==null){
            System.out.println("！！！！！！！");
        }
        //
        cache.put(hotelPO.getID(), hotel_detailVO);
        //
        hotel_detailVO.maxPrice = pricePair.maxPrice;
        hotel_detailVO.minPrice = pricePair.minPrice;
        return hotel_detailVO;
    }

    /**
     * 修改指定酒店信息
     *
     * @param hvo
     * @return 修改成功与否
     */
    public ResultMessage updateHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO = voToPO(hvo);
        ResultMessage resultMessage = hotelDataService.updateHotel(hotelPO);
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            cache.remove(hotelPO.getID());
            cache.put(hvo.ID, hvo);
        }
        return resultMessage;
    }

    /**
     * 添加酒店信息
     *
     * @param hvo
     * @return 添加成功与否
     */
    public ResultMessage addHotel(Hotel_DetailVO hvo) {
        HotelPO hotelPO = voToPO(hvo);
        ResultMessage resultMessage = hotelDataService.addHotel(hotelPO);
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
            cache.put(hvo.ID, hvo);
        }
        return resultMessage;
    }

    /**
     * 删除酒店信息
     *
     * @param hotelID
     * @return 删除成功与否
     */
    public ResultMessage deleteHotel(String hotelID) {
        ResultMessage resultMessage = hotelDataService.deleteHotel(hotelID);
        if (resultMessage.equals(ResultMessage.SUCCESS)) {
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
     *
     * @param hotelPO
     * @return hotel_DetailVO
     */
    private Hotel_DetailVO poToVO(HotelPO hotelPO) {
        return hotelPO == null ? null : new Hotel_DetailVO(hotelPO.getID()
                , hotelPO.getName()
                , hotelPO.getAddress()
                , hotelPO.getPlace()
                , hotelPO.getStar()
                , hotelPO.getIntroduction()
                , hotelPO.getFacilities()
                , null);
    }

    /**
     * 寻找指定酒店的最小和最大房间价格
     *
     * @param hotelID 指定酒店的ID
     * @return 最大最小价格对
     */
    private PricePair findMinAndMaxPrice(String hotelID) {
        double min = Double.MAX_VALUE;
        double max = 0;
        for (HotelRoomPO hotelRoomPO : hotelDataService.getRoom(hotelID)) {
            min = Double.min(min, hotelRoomPO.getPrice());
            max = Double.max(max, hotelRoomPO.getPrice());
        }
        //
        if (min == Double.MAX_VALUE) {
            min = 0;
        }
        //
        return new PricePair(min, max);
    }

    /**
     * 按照价格升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new PriceAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照价格降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> priceDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new PriceDescendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照星级升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照星级降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> starDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreDescendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照评分升序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreAscendingComparator());
        return hotel_detailVOs.iterator();
    }

    /**
     * 按照评分降序排列酒店
     *
     * @param hotel_detailVOs 需要被排列的酒店列表
     * @return 被排列的酒店列表
     */
    public Iterator<Hotel_DetailVO> scoreDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        Collections.sort(hotel_detailVOs, new ScoreDescendingComparator());
        return hotel_detailVOs.iterator();
    }

}
