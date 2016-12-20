package network.usernetworkservice;

import com.sun.org.apache.regexp.internal.RE;
import dataservice.userdataservice.UserDataService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public interface UserServerNetworkService extends Remote {

    public LoginState login(String account, String password) throws RemoteException;

    public LoginState logout() throws RemoteException;

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) throws RemoteException;

    public ResultMessage addClient(ClientPO clientPO) throws RemoteException;

    public ClientPO searchClientByID(String clientID) throws RemoteException;

    public ArrayList<ClientPO> searchClient(String keyword) throws RemoteException;

    public ResultMessage updateClient(String clientID, ClientPO clientPO) throws RemoteException;

    public ResultMessage deleteClient(String clientID) throws RemoteException;

    public ResultMessage addStaff(StaffPO staffPO) throws RemoteException;

    public StaffPO searchStaffByID(String staffID) throws RemoteException;

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) throws RemoteException;

    public ResultMessage deleteStaff(String staffID) throws RemoteException;

    public ArrayList<StaffPO> searchStaff(String keyword) throws RemoteException;

    public ResultMessage addSalesman(SalesmanPO salesmanPO) throws RemoteException;

    public SalesmanPO searchSalesmanByID(String salesmanID) throws RemoteException;

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) throws RemoteException;

    public ResultMessage deleteSalesman(String salesmanID) throws RemoteException;

    public ArrayList<SalesmanPO> searchSalesman(String keyword) throws RemoteException;

    public StaffPO getStaffByHotelID(String hotelID) throws RemoteException;

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) throws RemoteException;

    public ArrayList<CreditPO> searchCreditByID(String clientID) throws RemoteException;

    public ResultMessage addLevel(LevelPO levelPO) throws RemoteException;

    public ResultMessage updateLevel(String ID, LevelPO levelPO) throws RemoteException;

    public ResultMessage deleteLevel(String ID) throws RemoteException;

    public LevelPO getLevel(String level) throws RemoteException;

    public ArrayList<LevelPO> getAllLevel() throws RemoteException;

    public int getLevelByCredit(int credit) throws RemoteException;
}
