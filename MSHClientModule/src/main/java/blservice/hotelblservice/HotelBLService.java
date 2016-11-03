package blservice.hotelblservice;

import util.HotelNotFoundException;
import util.InfoInvalidException;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public interface HotelBLService {
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags);

    public Hotel_DetailVO getHotel(String hotelID) throws HotelNotFoundException;

    public ArrayList<HotelRoomVO> getRoom(String hotelID);

    public ResultMessage updateHotelInfo(Hotel_DetailVO hvo) throws InfoInvalidException;

    public ResultMessage updateHotelRoomInfo(HotelRoomVO rvo) throws InfoInvalidException;

    public ResultMessage addHotel(Hotel_DetailVO hvo) throws InfoInvalidException;

    public ResultMessage addRoom(HotelRoomVO rvo) throws InfoInvalidException;

    public ResultMessage deleteHotel(String hotelID) throws HotelNotFoundException;

    public ResultMessage deleteHotelRoom(String hotelID) throws HotelNotFoundException;
}
