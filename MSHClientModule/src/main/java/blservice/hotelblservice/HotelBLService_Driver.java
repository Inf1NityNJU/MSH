package blservice.hotelblservice;

import util.*;
import vo.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelBLService_Driver {
    //Test constant
    private static final String testHotelID = "00000000";
    private static final FilterFlagsVO TEST_FILTER_FLAGS_VO = new FilterFlagsVO(City.NanJing, Place.XianLin, "te", RoomType.SingleRoom, 250, 400, new DateUtil(2016, 12, 9), new DateUtil(2016, 12, 20), 2, 4, 3, 5, null);
    private static final Hotel_DetailVO TEST_HOTEL_DETAIL_VO = new Hotel_DetailVO("00000000", "Driver Test", City.NanJing, "Center Park", Place.XianLin, 3, "The test hotel", "All", null, 4.5, 2);
    private static final Hotel_DetailVO TEST_HOTEL_DETAIL_VO1 = new Hotel_DetailVO("00000001", "Driver Test1", City.NanJing, "Center Park", Place.XianLin, 2, "The test hotel", "All", null, 4, 5);
    private static final Hotel_DetailVO TEST_HOTEL_DETAIL_VO2 = new Hotel_DetailVO("00000002", "Driver Test2", City.NanJing, "Center Park", Place.XianLin, 5, "The test hotel", "All", null, 3.8, 6);
    private static final Hotel_DetailVO TEST_HOTEL_DETAIL_VO3 = new Hotel_DetailVO("00000003", "Driver Test3", City.NanJing, "Center Park", Place.XianLin, 4, "The test hotel", "All", null, 4, 1);

    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO("0000000008", RoomType.DeluxeRoom, 520, 10, null);
    private static final RoomType testType = RoomType.SingleRoom;

    //
    //driver
    public void drive(HotelBLService hotelBLService) {

        //1
        ArrayList<Hotel_DetailVO> hotelDetailVOArrayList = hotelBLService.searchHotel(TEST_FILTER_FLAGS_VO);
        if (hotelDetailVOArrayList != null) {
            System.out.println("Search hotel Success!");
        } else {
            System.out.println("Search hotel Failed!");
        }

        //2
        Hotel_DetailVO hotelDetialVO = hotelBLService.getHotel(testHotelID);
        if (hotelDetialVO != null) {
            System.out.println("Get hotel Success!");
        } else {
            System.out.println("Get hotel Failed!");
        }

        //3
        ArrayList<HotelRoomVO> hotelRoomVOArrayList = hotelBLService.getRoom(testHotelID);
        if (hotelRoomVOArrayList != null) {
            System.out.println("Get hotel room Success!");
        } else {
            System.out.println("Get hotel room Failed!");
        }

        //4
        ResultMessage testMessage = hotelBLService.updateHotel(TEST_HOTEL_DETAIL_VO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update hotel info Success!");
        } else {
            System.out.println("Update hotel info Failed!");
        }

        //5
        testMessage = hotelBLService.updateHotelRoom(testHotelRoomVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update hotel room info Success!");
        } else {
            System.out.println("Update hotel room info Failed!");
        }

        //6
        testMessage = hotelBLService.deleteHotel(testHotelID);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Delete hotel Success!");
        } else {
            System.out.println("Delete hotel Failed!");
        }

        //7
        testMessage = hotelBLService.deleteHotelRoom(testHotelID, testType);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Delete hotel room Success!");
        } else {
            System.out.println("Delete hotel room Failed!");
        }

        //8
        testMessage = hotelBLService.addHotel(TEST_HOTEL_DETAIL_VO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Add hotel info Success!");
        } else {
            System.out.println("Add hotel info Failed!");
        }

        //9
        testMessage = hotelBLService.addRoom(testHotelRoomVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Add hotel room info Success!");
        } else {
            System.out.println("Add hotel room info Failed!");
        }

        Iterator<Hotel_DetailVO> sortArray;
        ArrayList<Hotel_DetailVO> readToBeSort = new ArrayList<>();
        readToBeSort.add(TEST_HOTEL_DETAIL_VO);
        readToBeSort.add(TEST_HOTEL_DETAIL_VO1);
        readToBeSort.add(TEST_HOTEL_DETAIL_VO2);
        readToBeSort.add(TEST_HOTEL_DETAIL_VO3);

        //10
        sortArray = hotelBLService.priceAscendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("PriceAscendingSort Success!");
        } else {
            System.out.println("PriceAscendingSort Failed!");
        }

        //11
        sortArray = hotelBLService.priceDescendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("PriceDescendingSort Success!");
        } else {
            System.out.println("PriceDescendingSort Failed!");
        }

        //12
        sortArray = hotelBLService.scoreAscendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("ScoreAscendingSort Success!");
        } else {
            System.out.println("ScoreAscendingSort Failed!");
        }

        //13
        sortArray = hotelBLService.scoreDescendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("ScoreDescendingSort Success!");
        } else {
            System.out.println("ScoreDescendingSort Failed!");
        }

        //14
        sortArray = hotelBLService.starAscendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("StarAscendingSort Success!");
        } else {
            System.out.println("StarAscendingSort Failed!");
        }

        //15
        sortArray = hotelBLService.starDescendingSort(readToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("StarDescendingSort Success!");
        } else {
            System.out.println("StarDescendingSort Failed!");
        }

        //16
        DateUtil start = new DateUtil(2016, 1, 5);
        DateUtil end = new DateUtil(2016, 1, 10);
        int quantity = 3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleDouble, quantity);
        testMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update room quantity Success!");
        } else {
            System.out.println("Update room quantity Failed!");
        }

        //17
        ArrayList<OrderRoomStockVO> orderRoomStockVOs = hotelBLService.getRoomStocks(new DateUtil(2016, 12, 5), new DateUtil(2016, 12, 20), "00000000");
        if (orderRoomStockVOs != null) {
            System.out.println("get roomStock quantity Success!");
        } else {
            System.out.println("Update roomStock quantity Failed!");
        }

        //18
        ArrayList<Hotel_BriefVO> hotel_briefVOs = hotelBLService.searchHotelInBriefVO(TEST_FILTER_FLAGS_VO);
        if (hotel_briefVOs != null) {
            System.out.println("Search hotel in brief Success!");
        } else {
            System.out.println("Search hotel in brief Failed!");
        }
    }
}
