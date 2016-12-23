package blservice.hotelblservice;

import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelBLService_Stub implements HotelBLService, HotelBLInfo {
    public ArrayList<Hotel_DetailVO> searchHotel(FilterFlagsVO flags) {
        System.out.println("Search Success!");
        return new ArrayList<Hotel_DetailVO>();
    }


    public Hotel_DetailVO getHotel(String hotelID) {
        if (hotelID.equals("00000001")) {
            System.out.println("Get hotel Success!");
            return new Hotel_DetailVO("00000001", "酒店名称", City.NanJing, "仙林中心", Place.XianLin, 0, null, null, null, 0, 0);
        }
        return null;
    }

    public ArrayList<HotelRoomVO> getRoom(String hotelID) {
        if (hotelID.equals("00000000")) {
            System.out.println("Get hotel room Success!");
            HotelRoomVO hotelRoomVO = new HotelRoomVO("00000000", RoomType.SingleRoom, 288.8, 20, null);
            ArrayList<HotelRoomVO> hotelRoomVOs = new ArrayList<HotelRoomVO>();
            hotelRoomVOs.add(hotelRoomVO);
            return hotelRoomVOs;
        }
        return null;
    }

    public ResultMessage updateHotel(Hotel_DetailVO hvo) {
        if (hvo.ID.equals("000000")) {
            System.out.println("Update hotel Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage updateHotelRoom(HotelRoomVO rvo) {
        if (rvo != null) {
            System.out.println("Update hotel room Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage updateHotelRoomQuantity(RoomChangeInfoVO roomChangeInfoVO) {
        if (roomChangeInfoVO != null) {
            System.out.println("Update hotel room Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage addScoreToHotelByHotelID(double score, String hotelID) {
        if (hotelID != null) {
            System.out.println("Update hotel Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    @Override
    public int getAvailableQuantity(DateUtil start, DateUtil end, String hotelID, RoomType roomType) {
        return 0;
    }

    public ResultMessage addHotel(Hotel_DetailVO hvo) {
        if (hvo.ID.equals("000000")) {
            System.out.println("Add hotel Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }

    public ResultMessage addRoom(HotelRoomVO rvo) {
        if (rvo != null) {
            System.out.println("Add hotel room Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.EXIST;
    }

    public ResultMessage deleteHotel(String hotelID) {
        if (hotelID.equals("000000")) {
            System.out.println("Delete Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }

    public ResultMessage deleteHotelRoom(String hotelID, RoomType type) {
        if (hotelID.equals("000000")) {
            System.out.println("Delete Success!");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }

    @Override
    public ResultMessage setRoomWillBeCancel(String hotelID, RoomType type) {
        return null;
    }

    @Override
    public ResultMessage isOrdered(String hotelID, RoomType type) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> priceAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> priceDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> starAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> starDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> scoreAscendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public Iterator<Hotel_DetailVO> scoreDescendingSort(ArrayList<Hotel_DetailVO> hotel_detailVOs) {
        return null;
    }

    @Override
    public ArrayList<Hotel_BriefVO> searchHotelInBriefVO(FilterFlagsVO flags) {
        return null;
    }

    @Override
    public ArrayList<OrderRoomStockVO> getRoomStocks(DateUtil start, DateUtil end, String hotelID) {
        return null;
    }
}
