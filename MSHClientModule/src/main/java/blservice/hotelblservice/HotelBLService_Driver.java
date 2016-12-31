package blservice.hotelblservice;

import blimpl.hotelblimpl.HotelBLFactory;
import org.junit.Test;
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

    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO("00000000", RoomType.DoubleRoom, 520, 10, null);
    private static final RoomType testType = RoomType.DoubleRoom;
    private HotelBLService hotelBLService = HotelBLFactory.getHotelBLService();

    //
    //driver
    @Test
    public void drive() {
        //用于保存测试返回结果
        ResultMessage testMessage;
        //1
        testMessage = hotelBLService.addHotel(TEST_HOTEL_DETAIL_VO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Add hotel info Success!");
        } else {
            System.out.println("Add hotel info Failed!");
        }

        //2
        testMessage = hotelBLService.addRoom(testHotelRoomVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Add hotel room info Success!");
        } else {
            System.out.println("Add hotel room info Failed!");
        }

        //3
        testMessage = hotelBLService.updateHotel(TEST_HOTEL_DETAIL_VO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update hotel info Success!");
        } else {
            System.out.println("Update hotel info Failed!");
        }

        //4
        testMessage = hotelBLService.updateHotelRoom(testHotelRoomVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update hotel room info Success!");
        } else {
            System.out.println("Update hotel room info Failed!");
        }


        //5
        ArrayList<Hotel_DetailVO> hotelDetailVOArrayList = hotelBLService.searchHotel(TEST_FILTER_FLAGS_VO);
        if (hotelDetailVOArrayList != null) {
            System.out.println("Search hotel Success!");
        } else {
            System.out.println("Search hotel Failed!");
        }

        //6
        Hotel_DetailVO hotelDetialVO = hotelBLService.getHotel(testHotelID);
        if (hotelDetialVO != null) {
            System.out.println("Get hotel Success!");
        } else {
            System.out.println("Get hotel Failed!");
        }

        //7
        ArrayList<HotelRoomVO> hotelRoomVOArrayList = hotelBLService.getRoom(testHotelID);
        if (hotelRoomVOArrayList != null) {
            System.out.println("Get hotel room Success!");
        } else {
            System.out.println("Get hotel room Failed!");
        }


        //8
        DateUtil start = new DateUtil(2016, 1, 5);
        DateUtil end = new DateUtil(2016, 1, 10);
        int quantity = 3;
        RoomChangeInfoVO roomChangeInfoVO = new RoomChangeInfoVO(start, end, "00000000", RoomType.DoubleRoom, quantity);
        testMessage = hotelBLService.updateHotelRoomQuantity(roomChangeInfoVO);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Update room quantity Success!");
        } else {
            System.out.println("Update room quantity Failed!");
        }

        //9
        ArrayList<OrderRoomStockVO> orderRoomStockVOs = hotelBLService.getRoomStocks(new DateUtil(2016, 12, 5), new DateUtil(2016, 12, 20), "00000000");
        if (orderRoomStockVOs != null) {
            System.out.println("get roomStock quantity Success!");
        } else {
            System.out.println("Update roomStock quantity Failed!");
        }

        //10
        ArrayList<Hotel_BriefVO> hotel_briefVOs = hotelBLService.searchHotelInBriefVO(TEST_FILTER_FLAGS_VO);
        if (hotel_briefVOs != null) {
            System.out.println("Search hotel in brief Success!");
        } else {
            System.out.println("Search hotel in brief Failed!");
        }


        //11
        testMessage = hotelBLService.deleteHotel(testHotelID);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Delete hotel Success!");
        } else {
            System.out.println("Delete hotel Failed!");
        }

        //12
        testMessage = hotelBLService.deleteHotelRoom(testHotelID, testType);
        if (testMessage == ResultMessage.SUCCESS) {
            System.out.println("Delete hotel room Success!");
        } else {
            System.out.println("Delete hotel room Failed!");
        }

        //为酒店排序做准备
        Iterator<Hotel_DetailVO> sortArray;
        ArrayList<Hotel_DetailVO> readyToBeSort = new ArrayList<>();
        readyToBeSort.add(TEST_HOTEL_DETAIL_VO);
        readyToBeSort.add(TEST_HOTEL_DETAIL_VO1);
        readyToBeSort.add(TEST_HOTEL_DETAIL_VO2);
        readyToBeSort.add(TEST_HOTEL_DETAIL_VO3);

        //13
        sortArray = hotelBLService.priceAscendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("PriceAscendingSort Success!");
        } else {
            System.out.println("PriceAscendingSort Failed!");
        }

        //14
        sortArray = hotelBLService.priceDescendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("PriceDescendingSort Success!");
        } else {
            System.out.println("PriceDescendingSort Failed!");
        }

        //15
        sortArray = hotelBLService.scoreAscendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("ScoreAscendingSort Success!");
        } else {
            System.out.println("ScoreAscendingSort Failed!");
        }

        //16
        sortArray = hotelBLService.scoreDescendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("ScoreDescendingSort Success!");
        } else {
            System.out.println("ScoreDescendingSort Failed!");
        }

        //17
        sortArray = hotelBLService.starAscendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("StarAscendingSort Success!");
        } else {
            System.out.println("StarAscendingSort Failed!");
        }

        //18
        sortArray = hotelBLService.starDescendingSort(readyToBeSort);
        if (sortArray.hasNext()) {
            System.out.println("StarDescendingSort Success!");
        } else {
            System.out.println("StarDescendingSort Failed!");
        }

    }
}
