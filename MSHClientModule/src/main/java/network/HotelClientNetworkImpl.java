package network;

import network.hotelnetworkservice.HotelServerNetworkService;
import po.HotelPO;
import po.HotelRoomPO;
import po.RoomStockPO;
import util.CriteriaClause;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 *
 */
public class HotelClientNetworkImpl implements HotelClientNetworkService {
    /**
     * 再次尝试连接的时间间隔
     */
    private static final int SLEEP_TIME = 2000;
    /**
     * 传递过来的数据持久化接口
     */
    private HotelServerNetworkService hotelServerNetworkService;

    public HotelClientNetworkImpl() {
        while (hotelServerNetworkService==null) {
            try {
                hotelServerNetworkService = (HotelServerNetworkService) Naming.lookup("HotelServerNetworkService");
            } catch (NotBoundException e) {
                sleep();
                System.err.println("Client.network.hotelServerNetworkService: Not bound, trying to connect");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                break;
            } catch (RemoteException e) {
                sleep();
                System.err.println("Client.network.hotelServerNetworkService: No service, trying to connect");
            }
        }
    }

    /**
     * 线程睡眠一段时间
     */
    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public ArrayList<HotelPO> exactlySearchHotel(String field, Object value) {
        try {
            return hotelServerNetworkService.exactlySearchHotel(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> prefixSearchHotel(String field, String value) {
        try {
            return hotelServerNetworkService.prefixSearchHotel(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> fullSearchHotel(String field, Object value) {
        try {
            return hotelServerNetworkService.fullSearchHotel(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> suffixSearchHotel(String field, String value) {
        try {
            return hotelServerNetworkService.suffixSearchHotel(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> fuzzySearchHotel(String field, String value) {
        try {
            return hotelServerNetworkService.fuzzySearchHotel(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> rangeSearchHotel(String field, Object min, Object max) {
        try {
            return hotelServerNetworkService.rangeSearchHotel(field, min, max);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HotelPO getHotel(String hotelID) {
        try {
            return hotelServerNetworkService.getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelRoomPO> getRoom(String hotelID) {
        try {
            return hotelServerNetworkService.getRoom(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage updateHotel(HotelPO hotelPO) {
        try {
            return hotelServerNetworkService.updateHotel(hotelPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage updateRoom(HotelRoomPO hotelRoomPO) {
        try {
            return hotelServerNetworkService.updateRoom(hotelRoomPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage addHotel(HotelPO hotelPO) {
        try {
            return hotelServerNetworkService.addHotel(hotelPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage addRoom(HotelRoomPO hotelRoomPO) {

        try {
            return hotelServerNetworkService.addRoom(hotelRoomPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage deleteHotel(String hotelID) {
        try {
            return hotelServerNetworkService.deleteHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage deleteRoom(String roomID) {
        try {
            return hotelServerNetworkService.deleteRoom(roomID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage addRoomStock(RoomStockPO roomStockPO) {
        try {
            return hotelServerNetworkService.addRoomStock(roomStockPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage updateRoomStock(RoomStockPO roomStockPO) {
        try {
            return hotelServerNetworkService.updateRoomStock(roomStockPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage deleteRoomStock(String roomStockID) {
        try {
            return hotelServerNetworkService.deleteRoomStock(roomStockID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ArrayList<RoomStockPO> getRoomStock(String hotelRoomID) {
        try {
            return hotelServerNetworkService.getRoomStock(hotelRoomID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HotelRoomPO getRoomByID(String hotelRoomID) {

        try {
            return hotelServerNetworkService.getRoomByID(hotelRoomID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelRoomPO> fullSearchHotelRoom(String field, Object value) {
        try {
            return hotelServerNetworkService.fullSearchHotelRoom(field, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> multiSearchHotel(ArrayList<CriteriaClause> criteriaClauses) {
        try {
            return hotelServerNetworkService.multiSearchHotel(criteriaClauses);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotelRoomPO> multiSearchHotelRoom(ArrayList<CriteriaClause> criteriaClauses) {
        try {
            return hotelServerNetworkService.multiSearchHotelRoom(criteriaClauses);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<RoomStockPO> multiSearchRoomStockPO(ArrayList<CriteriaClause> criteriaClauses) {
        try {
            return hotelServerNetworkService.multiSearchRoomStockPO(criteriaClauses);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
