package blimpl.hotelblimpl;

import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import util.DateUtil;
import util.ResultMessage;
import util.RoomType;
import vo.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SilverNarcissus on 16/11/5.
 * All Done on 16/11/26
 */
public class HotelBLServiceImpl implements HotelBLService, HotelBLInfo {
    private Hotel hotel;
    private HotelRoom hotelRoom;

    protected HotelBLServiceImpl(Hotel hotel, HotelRoom hotelRoom) {
        this.hotel = hotel;
        this.hotelRoom = hotelRoom;
    }

    @Override
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags) {
        return hotel.searchHotel(flags);
    }

    @Override
    public Hotel_DetailVO getHotel(String hotelID) {
        return hotel.getHotel(hotelID);
    }

    @Override
    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        return hotelRoom.getRoom(hotelID);
    }

    @Override
    public ResultMessage updateHotel(Hotel_DetailVO hvo) {
        return hotel.updateHotel(hvo);
    }

    @Override
    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        return hotelRoom.updateHotelRoom(rvo);
    }

    @Override
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO) {
        return hotelRoom.updateHotelRoomQuantity(roomChangeInfoVO);
    }

    @Override
    public ResultMessage addHotel(Hotel_DetailVO hvo) {
        return hotel.addHotel(hvo);
    }

    @Override
    public ResultMessage addRoom(HotelRoomVO rvo) {
        return hotelRoom.addRoom(rvo);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) {
        return hotel.deleteHotel(hotelID);
    }

    @Override
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) {
        return hotelRoom.deleteHotelRoom(hotelID, type);
    }

    @Override
    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type) {
        return hotelRoom.setRoomWillBeCancelled(hotelID, type);
    }

    @Override
    public ResultMessage isOrdered(String hotelID, RoomType type) {
        return hotelRoom.isOrdered(hotelID, type);
    }

    @Override
    public Iterator<Hotel_DetailVO> priceAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.priceAscendingSort(hotel_detailVOs);
    }

    @Override
    public Iterator<Hotel_DetailVO> priceDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.priceDescendingSort(hotel_detailVOs);
    }

    @Override
    public Iterator<Hotel_DetailVO> starAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.starAscendingSort(hotel_detailVOs);
    }

    @Override
    public Iterator<Hotel_DetailVO> starDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.starDescendingSort(hotel_detailVOs);
    }

    @Override
    public Iterator<Hotel_DetailVO> scoreAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.scoreAscendingSort(hotel_detailVOs);
    }

    @Override
    public Iterator<Hotel_DetailVO> scoreDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return hotel.scoreDescendingSort(hotel_detailVOs);
    }

    @Override
    public ArrayList<Hotel_BriefVO> searchHotelInBriefVO(FilterFlagsVO flags) {
        ArrayList<Hotel_BriefVO> hotel_briefVOs = new ArrayList<Hotel_BriefVO>();
        ArrayList<Hotel_DetailVO> hotel_detailVOs = searchHotel(flags);
        for (Hotel_DetailVO hotel_DetailVO : hotel_detailVOs) {
            hotel_briefVOs.add(new Hotel_BriefVO(hotel_DetailVO));
        }

        return hotel_briefVOs;
    }

    @Override
    public ArrayList<OrderRoomStockVO> getRoomStocks(DateUtil start, DateUtil end, String hotelID) {
        return hotelRoom.getRoomStocks(start, end, hotelID);
    }

    @Override
    public ResultMessage addScoreToHotelByHotelID(double score, String hotelID) {
        return hotel.addScore(score, hotelID);
    }

    @Override
    public int getAvailableQuantity(DateUtil start, DateUtil end, String hotelID, RoomType roomType) {
        for(OrderRoomStockVO orderRoomStockVO:hotelRoom.getRoomStocks(start, end, hotelID)){
            if(orderRoomStockVO.orderRoom.type.equals(roomType)){
                return orderRoomStockVO.availableQuantity;
            }
        }
        return -1;
    }
}
