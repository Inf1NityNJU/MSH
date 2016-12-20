package network.usernetwork;

import dataimpl.userdataimpl.UserDataServiceFactory;
import dataservice.userdataservice.UserDataService;
import network.usernetworkservice.UserServerNetworkService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/27.
 */
public class UserServerNetworkImpl extends UnicastRemoteObject implements UserServerNetworkService {

    private UserDataService userDataService;

    public UserServerNetworkImpl() throws RemoteException {

    }

    public LoginState login(String account, String password) {
        userDataService = UserDataServiceFactory.getClientDataService();
        if (userDataService.login(account, password) == LoginState.LOGIN_SUCCESS_Client) {
            return LoginState.LOGIN_SUCCESS_Client;
        }
        userDataService = UserDataServiceFactory.getStaffDataService();
        if (userDataService.login(account, password) == LoginState.LOGIN_SUCCESS_Staff) {
            return LoginState.LOGIN_SUCCESS_Staff;
        }
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        if (userDataService.login(account, password) == LoginState.LOGIN_SUCCESS_Salesman) {
            return LoginState.LOGIN_SUCCESS_Salesman;
        }
        return LoginState.LOGIN_FAIL;
    }

    //TODO
    public LoginState logout() {
        return LoginState.LOGOUT;
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        userDataService = UserDataServiceFactory.getClientDataService();
        if (userDataService.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        }
        userDataService = UserDataServiceFactory.getStaffDataService();
        if (userDataService.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        }
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        if (userDataService.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage addClient(ClientPO clientPO) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.addClient(clientPO);
    }

    public ClientPO searchClientByID(String clientID) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.searchClientByID(clientID);
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.searchClient(keyword);
    }

    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.updateClient(clientID, clientPO);
    }

    public ResultMessage deleteClient(String clientID) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.deleteClient(clientID);
    }

    public ResultMessage addStaff(StaffPO staffPO) {
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.addStaff(staffPO);
    }

    public StaffPO searchStaffByID(String staffID) {
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.searchStaffByID(staffID);
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.updateStaff(staffID, staffPO);
    }

    public ResultMessage deleteStaff(String staffID) {
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.deleteStaff(staffID);
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.searchStaff(keyword);
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.addSalesman(salesmanPO);
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.searchSalesmanByID(salesmanID);
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.updateSalesman(salesmanID, salesmanPO);
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.deleteSalesman(salesmanID);
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.searchSalesman(keyword);
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.addCreditRecord(clientID, creditPO);
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.searchCreditByID(clientID);
    }

    public StaffPO getStaffByHotelID(String hotelID){
        userDataService = UserDataServiceFactory.getStaffDataService();
        return userDataService.getStaffByHotelID(hotelID);
    }

    public ResultMessage addLevel(LevelPO levelPO) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.addLevel(levelPO);
    }

    public ResultMessage updateLevel(String ID, LevelPO levelPO) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.updateLevel(ID, levelPO);
    }

    public ResultMessage deleteLevel(String ID) {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.deleteLevel(ID);
    }

    public LevelPO getLevel(String level) {
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.getLevel(level);
    }

    public ArrayList<LevelPO> getAllLevel() {
        userDataService = UserDataServiceFactory.getSalesmanDataService();
        return userDataService.getAllLevel();
    }

    public int getLevelByCredit(int credit){
        userDataService = UserDataServiceFactory.getClientDataService();
        return userDataService.getLevelByCredit(credit);
    }

}
