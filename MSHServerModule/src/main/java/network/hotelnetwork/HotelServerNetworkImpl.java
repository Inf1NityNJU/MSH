package network.hotelnetwork;

import dataimpl.hoteldataimpl.HotelDataServiceFactory;
import dataservice.hoteldataservice.HotelDataService;
import network.hotelnetworkservice.HotelServerNetworkService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class HotelServerNetworkImpl extends UnicastRemoteObject implements HotelServerNetworkService {
    /**
     * 用于实现酒店信息持久化的接口
     */
    private HotelDataService hotelDataService;

    public HotelServerNetworkImpl() throws RemoteException {
        hotelDataService = HotelDataServiceFactory.getHotelDataService();
    }


    @Override
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value) throws RemoteException {
        return hotelDataService.exactlySearchHotel(field, value);
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) throws RemoteException {
        return hotelDataService.prefixSearchHotel(field, value);
    }

    @Override
    public ArrayList<HotelPO> fullSearchHotel(String field, Object value) throws RemoteException {
        return hotelDataService.fullSearchHotel(field, value);
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) throws RemoteException {
        return hotelDataService.suffixSearchHotel(field, value);
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) throws RemoteException {
        return hotelDataService.fuzzySearchHotel(field, value);
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) throws RemoteException {
        return hotelDataService.rangeSearchHotel(field, min, max);
    }

    @Override
    public HotelPO getHotel(String hotelID) throws RemoteException {
        return hotelDataService.getHotel(hotelID);
    }

    @Override
    public ArrayList<HotelRoomPO> getRoom(String hotelID) throws RemoteException {
        return hotelDataService.getRoom(hotelID);
    }

    @Override
    public ResultMessage updateHotel(HotelPO hotelPO) throws RemoteException {
        return hotelDataService.updateHotel(hotelPO);
    }

    @Override
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) throws RemoteException {
        return hotelDataService.updateRoom(hotelRoomPO);
    }

    @Override
    public ResultMessage addHotel(HotelPO hotelPO) throws RemoteException {
        return hotelDataService.addHotel(hotelPO);
    }

    @Override
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) throws RemoteException {
        return hotelDataService.addRoom(hotelRoomPO);
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) throws RemoteException {
        return hotelDataService.deleteHotel(hotelID);
    }

    @Override
    public ResultMessage deleteRoom(String roomID) throws RemoteException {
        return hotelDataService.deleteRoom(roomID);
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) throws RemoteException {
        return hotelDataService.addRoomStock(roomStockPO);
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) throws RemoteException {
        return hotelDataService.updateRoomStock(roomStockPO);
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) throws RemoteException {
        return hotelDataService.deleteRoomStock(roomStockID);
    }

    @Override
    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID) throws RemoteException {
        return hotelDataService.getRoomStock(hotelRoomID);
    }

    @Override
    public HotelRoomPO getRoomByID(String hotelRoomID) throws RemoteException {
        return hotelDataService.getRoomByID(hotelRoomID);
    }

    @Override
    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value) throws RemoteException {
        return hotelDataService.fullSearchHotelRoom(field, value);
    }

    @Override
    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException {
        return hotelDataService.multiSearchHotel(criteriaClauses);
    }

    @Override
    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException {
        return hotelDataService.multiSearchHotelRoom(criteriaClauses);
    }

    @Override
    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses) throws RemoteException {
        return hotelDataService.multiSearchRoomStockPO(criteriaClauses);
    }
}
