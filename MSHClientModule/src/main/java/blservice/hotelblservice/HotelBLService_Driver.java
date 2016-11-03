package blservice.hotelblservice;

import util.HotelNotFoundException;
import util.ResultMessage;
import vo.FilterFlagsVO;
import vo.HotelRoomVO;
import vo.Hotel_DetailVO;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelBLService_Driver {
    //Test constant
    private static final String testHotelID = "000000";
    private static final FilterFlagsVO TEST_FILTER_FLAGS_VO = new FilterFlagsVO(null,null,null,0,0,null,null,0,0,0,0,null);
    private static final Hotel_DetailVO TEST_HOTEL_DETIAL_VO = new Hotel_DetailVO(null,null,null,null,0,null,null,null);
    private static final HotelRoomVO testHotelRoomVO = new HotelRoomVO(null,0,0,null);
    //
    //dirver
    public void drive(HotelBLService hotelBLService){

        ArrayList<Hotel_DetailVO> hotelDetialVOArrayList = hotelBLService.searchHotel(TEST_FILTER_FLAGS_VO);
        if (hotelDetialVOArrayList != null){
            System.out.println("Search hotel Success!");
        }
        else {
            System.out.println("Search hotel Failed!");
        }

        try {
            Hotel_DetailVO hotelDetialVO = hotelBLService.getHotel(testHotelID);
            if (hotelDetialVO != null){
                System.out.println("Get hotel Success!");
            }
            else {
                System.out.println("Get hotel Failed!");
            }
        } catch (HotelNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<HotelRoomVO> hotelRoomVOArrayList = hotelBLService.getRoom(testHotelID);
        if (hotelRoomVOArrayList != null){
            System.out.println("Get hotel room Success!");
        }
        else {
            System.out.println("Get hotel room Failed!");
        }

        try {

            ResultMessage testMessage = hotelBLService.updateHotelInfo(TEST_HOTEL_DETIAL_VO);
            if(testMessage == ResultMessage.SUCCESS){
                System.out.println("Update hotel info Success!");
            }
            else {
                System.out.println("Update hotel info Failed!");
            }

            testMessage = hotelBLService.updateHotelRoomInfo(testHotelRoomVO);
            if(testMessage == ResultMessage.SUCCESS){
                System.out.println("Update hotel room info Success!");
            }
            else {
                System.out.println("Update hotel room info Failed!");
            }

            testMessage = hotelBLService.addHotel(TEST_HOTEL_DETIAL_VO);
            if(testMessage == ResultMessage.SUCCESS){
                System.out.println("Add hotel info Success!");
            }
            else {
                System.out.println("Add hotel info Failed!");
            }

            testMessage = hotelBLService.addRoom(testHotelRoomVO);
            if(testMessage == ResultMessage.SUCCESS){
                System.out.println("Add hotel room info Success!");
            }
            else {
                System.out.println("Add hotel room info Failed!");
            }

            testMessage = hotelBLService.deleteHotel(testHotelID);
            if(testMessage == ResultMessage.SUCCESS){
                System.out.println("Delete hotel Success!");
            }
            else {
                System.out.println("Delete hotel Failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
