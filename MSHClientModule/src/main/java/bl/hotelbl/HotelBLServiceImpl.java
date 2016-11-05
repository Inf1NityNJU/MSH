package bl.hotelbl;

import blservice.hotelblservice.HotelBLService;
import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import util.RoomType;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/5.
 */
public class HotelBLServiceImpl implements HotelBLService {
    private Hotel hotel;
    private HotelRoom hotelRoom;
    public HotelBLServiceImpl(boolean isMock){
        if(isMock){
            hotel=new MockHotel();
            hotelRoom=new MockHotelRoom();
        }
        else{
            hotel=new Hotel();
            hotelRoom=new HotelRoom();
        }
    }

    @Override
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags) {
        return hotel.searchHotel(flags);
    }

    @Override
    public Hotel_DetailVO getHotel(String hotelID) throws HotelNotFoundException {
        return hotel.getHotel(hotelID);
    }

    @Override
    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        return hotelRoom.getRoom(hotelID);
    }

    @Override
    public ResultMessage updateHotelInfo(Hotel_DetailVO hvo) throws InfoInvalidException {
        return hotel.updateHotelInfo(hvo);
    }

    @Override
    public ResultMessage updateHotelRoomInfo(HotelRoomVO rvo) throws InfoInvalidException {
        return hotelRoom.updateHotelRoomInfo(rvo);
    }

    @Override
    public ResultMessage addHotel(Hotel_DetailVO hvo) throws InfoInvalidException {
        return hotel.addHotel(hvo);
    }

    @Override
    public ResultMessage addRoom(HotelRoomVO rvo) throws InfoInvalidException {
        return hotelRoom.addRoom(rvo);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException {
        return hotel.deleteHotel(hotelID);
    }

    @Override
    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) throws HotelNotFoundException {
        return hotelRoom.deleteHotelRoom(hotelID,type);
    }
}
