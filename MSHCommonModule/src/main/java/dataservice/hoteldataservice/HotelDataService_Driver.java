package dataservice.hoteldataservice;

import po.HotelPO;
import po.HotelRoomPO;
import util.ResultMessage;
import util.RoomType;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelDataService_Driver {
    //Test constant


    private static final String testHotelID = "000000";
    private static final HotelPO testHotelPO = new HotelPO(null,null,null,null,0,null,null);
    private static final HotelRoomPO testHotelRoomPO = new HotelRoomPO(null, RoomType.DoubleRoom,200,1);


    //driver
    public void drive(HotelDataService hotelDataService){

        HotelPO hotelPO = hotelDataService.getHotel(testHotelID);
        if(hotelPO != null){
            System.out.println("Get hotel Success!");
        }
        else {
            System.out.println("Get hotel Failed!");
        }

        ArrayList<HotelRoomPO> hotelRoomPOArrayList = hotelDataService.getRoom(testHotelID);
        if(hotelRoomPOArrayList != null){
            System.out.println("Get hotel room Success!");
        }
        else {
            System.out.println("Get hotel room Failed!");
        }

        ResultMessage testMessage = hotelDataService.updateHotel(testHotelPO);
        if(testMessage == ResultMessage.SUCCESS){
            System.out.println("Update hotel Success!");
        }
        else {
            System.out.println("Update hotel Failed!");
        }

        testMessage = hotelDataService.updateRoom(testHotelRoomPO);
        if(testMessage == ResultMessage.SUCCESS){
            System.out.println("Update hotel room Success!");
        }
        else {
            System.out.println("Update hotel room Failed!");
        }

        testMessage = hotelDataService.addHotel(testHotelPO);
        if(testMessage == ResultMessage.SUCCESS){
            System.out.println("Add hotel Success!");
        }
        else {
            System.out.println("Add hotel Failed!");
        }

        testMessage = hotelDataService.addRoom(testHotelRoomPO);
        if(testMessage == ResultMessage.SUCCESS){
            System.out.println("Add hotel room Success!");
        }
        else {
            System.out.println("Add hotel room Failed!");
        }

        testMessage = hotelDataService.deleteHotel(testHotelID);
        if(testMessage == ResultMessage.SUCCESS){
            System.out.println("Delete hotel Success!");
        }
        else {
            System.out.println("Delete hotel Failed!");
        }
    }
}
