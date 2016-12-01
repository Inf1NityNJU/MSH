package network;

import network.usernetwork.UserServerNetworkService;
import po.*;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/23.
 */
public class UserClientNetworkImpl
//        implements UserBLService, UserBLInfo, LevelService
{
    private UserServerNetworkService userServerNetworkService;

    public UserClientNetworkImpl() {
        try {
            userServerNetworkService = (UserServerNetworkService) Naming.lookup("UserServerNetworkService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ResultMessage addClient(ClientPO clientPO, CreditPO creditPO) {
        try {
            return userServerNetworkService.addClient(clientPO, creditPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ClientPO searchClientByID(String clientID) {
        try {
            return userServerNetworkService.searchClientByID(clientID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateClient(String clientID, ClientPO clientPO) {
        try {
            return userServerNetworkService.updateClient(clientID, clientPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteClient(String clientID) {
        try {
            return userServerNetworkService.deleteClient(clientID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ClientPO> searchClient(String keyword) {
        try {
            return userServerNetworkService.searchClient(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addStaff(StaffPO staffPO) {
        try {
            return userServerNetworkService.addStaff(staffPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StaffPO searchStaffByID(String staffID) {
        try {
            return userServerNetworkService.searchStaffByID(staffID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateStaff(String staffID, StaffPO staffPO) {
        try {
            return userServerNetworkService.updateStaff(staffID, staffPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteStaff(String staffID){
        try {
            return userServerNetworkService.deleteStaff(staffID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<StaffPO> searchStaff(String keyword){
        try {
            System.out.println("++++");
            return userServerNetworkService.searchStaff(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO){
        try {
            return userServerNetworkService.addSalesman(salesmanPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SalesmanPO searchSalesmanByID(String salesmanID){
        try {
            return userServerNetworkService.searchSalesmanByID(salesmanID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO){
        try {
            return userServerNetworkService.updateSalesman(salesmanID, salesmanPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteSalesman(String salesmanID){
        try {
            return userServerNetworkService.deleteSalesman(salesmanID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword){
        try {
            return userServerNetworkService.searchSalesman(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO){
        try {
            return userServerNetworkService.addCreditRecord(clientID, creditPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID){
        try {
            return userServerNetworkService.searchCreditByID(clientID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addLevel(LevelPO levelPO){
        try {
            return userServerNetworkService.addLevel(levelPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateLevel(String ID, LevelPO levelPO){
        try {
            return userServerNetworkService.updateLevel(ID, levelPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteLevel(String ID){
        try {
            return userServerNetworkService.deleteLevel(ID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LevelPO getLevel(String level){
        try {
            return userServerNetworkService.getLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<LevelPO> getAllLevel(){
        try {
            return userServerNetworkService.getAllLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
