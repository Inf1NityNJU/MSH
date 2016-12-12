package dataservice.hoteldataservice;

import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/10/12.
 */
public class HotelDataService_Stub implements HotelDataService{

    @Override
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> fullSearchHotel(String field, Object value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) {
        return null;
    }

    public HotelPO getHotel(String hotelID) {
        System.out.println("Get hotel Success!");
        return new HotelPO(null,null,null,null,0,null,null);
    }

    public ArrayList<HotelRoomPO> getRoom(String hotelID){
        System.out.println("Get hotel room Success!");
        return new ArrayList<HotelRoomPO>();
    }

    public ResultMessage updateHotel(HotelPO hvo){
        System.out.println("Update hotel Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateRoom(HotelRoomPO rvo){
        System.out.println("Update hotel room Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage addHotel(HotelPO hvo){
        System.out.println("Add hotel Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage addRoom(HotelRoomPO rvo){
        System.out.println("Add hotel room Success!");
        return ResultMessage.SUCCESS;
    }

    public ResultMessage deleteHotel(String hotelID) {
        System.out.println("Delete Success!");
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        return null;
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) {
        return null;
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) {
        return null;
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) {
        return null;
    }

    @Override
    public ArrayList<RoomStockPO> getRoomStock(String roomStockID) {
        return null;
    }

    @Override
    public HotelRoomPO getRoomByID(String hotelRoomID) {
        return null;
    }

    @Override
    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses) {
        return null;
    }

    @Override
    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses) {
        return null;
    }

    @Override
    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses) {
        return null;
    }
}
