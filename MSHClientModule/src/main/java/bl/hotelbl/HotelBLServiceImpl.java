package bl.hotelbl;

import blservice.hotelblservice.HotelBLInfo;
import blservice.hotelblservice.HotelBLService;
import util.ResultMessage;
import util.RoomType;
import vo.*;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelBLServiceImpl implements HotelBLService,HotelBLInfo {
    private Hotel hotel;
    private HotelRoom hotelRoom;

    protected HotelBLServiceImpl(Hotel hotel,HotelRoom hotelRoom){
        this.hotel=hotel;
        this.hotelRoom=hotelRoom;
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
    public ResultMessage addAssessment(AssessmentVO assessmentVO) {
        return null;
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
        return hotelRoom.deleteHotelRoom(hotelID,type);
    }

    @Override
    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type) {
        return hotelRoom.setRoomWillBeCancel(hotelID,type);
    }

    @Override
    public ResultMessage isOrdered(String hotelID, RoomType type) {
        return hotelRoom.isOrdered(hotelID,type);
    }
}
