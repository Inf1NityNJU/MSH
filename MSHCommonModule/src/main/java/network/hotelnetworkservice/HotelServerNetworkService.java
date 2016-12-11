package network.hotelnetworkservice;

import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 *
 */
public interface HotelServerNetworkService extends Remote{
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value) throws RemoteException;

    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) throws RemoteException;

    public ArrayList<HotelPO> fullSearchHotel(String field, Object value) throws RemoteException;

    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) throws RemoteException;

    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) throws RemoteException;

    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) throws RemoteException;

    public HotelPO getHotel(String hotelID) throws RemoteException;

    public ArrayList<HotelRoomPO> getRoom(String hotelID) throws RemoteException;

    public ResultMessage updateHotel(HotelPO hotelPO) throws RemoteException;

    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) throws RemoteException;

    public ResultMessage addHotel(HotelPO hotelPO) throws RemoteException;

    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) throws RemoteException;

    public ResultMessage deleteHotel(String hotelID) throws RemoteException;

    public ResultMessage deleteRoom(String roomID) throws RemoteException;

    public ResultMessage addRoomStock(RoomStockPO roomStockPO) throws RemoteException;

    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) throws RemoteException;

    public ResultMessage deleteRoomStock(String roomStockID) throws RemoteException;

    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID) throws RemoteException;

    public HotelRoomPO getRoomByID(String hotelRoomID) throws RemoteException;

    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value) throws RemoteException;

    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException;

    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException;

    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException;

}
