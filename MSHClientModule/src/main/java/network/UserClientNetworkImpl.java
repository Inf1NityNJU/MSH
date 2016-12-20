package network;

import network.usernetworkservice.UserServerNetworkService;
import po.*;
import util.LoginState;
import util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/23.
 */
public class UserClientNetworkImpl implements UserClientNetworkService {

    private UserServerNetworkService userServerNetworkService;

    public UserClientNetworkImpl() {
        while (userServerNetworkService == null) {
            try {
                userServerNetworkService = (UserServerNetworkService) Naming.lookup("UserServerNetworkService");
            } catch (NotBoundException e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.err.println("Client.network.userServerNetworkService: Not bound, trying to connect");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                break;
            } catch (RemoteException e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.err.println("Client.network.userServerNetworkService: No service, trying to connect");
            }
        }

    }

    public LoginState login(String account, String password) {
        try {
            return userServerNetworkService.login(account, password);
        } catch (Exception e) {
            e.printStackTrace();
            return LoginState.LOGIN_FAIL;
        }
    }

    public LoginState logout() {
        try {
            return userServerNetworkService.logout();
        } catch (Exception e) {
            e.printStackTrace();
            return LoginState.LOGIN_FAIL;
        }
    }

    public ResultMessage resetPassword(String account, String oldPassword, String newPassword) {
        try {
            return userServerNetworkService.resetPassword(account, oldPassword, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage addClient(ClientPO clientPO) {
        try {
            return userServerNetworkService.addClient(clientPO);
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

    public ResultMessage deleteStaff(String staffID) {
        try {
            return userServerNetworkService.deleteStaff(staffID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<StaffPO> searchStaff(String keyword) {
        try {
            return userServerNetworkService.searchStaff(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addSalesman(SalesmanPO salesmanPO) {
        try {
            return userServerNetworkService.addSalesman(salesmanPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SalesmanPO searchSalesmanByID(String salesmanID) {
        try {
            return userServerNetworkService.searchSalesmanByID(salesmanID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateSalesman(String salesmanID, SalesmanPO salesmanPO) {
        try {
            return userServerNetworkService.updateSalesman(salesmanID, salesmanPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteSalesman(String salesmanID) {
        try {
            return userServerNetworkService.deleteSalesman(salesmanID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<SalesmanPO> searchSalesman(String keyword) {
        try {
            return userServerNetworkService.searchSalesman(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addCreditRecord(String clientID, CreditPO creditPO) {
        try {
            return userServerNetworkService.addCreditRecord(clientID, creditPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CreditPO> searchCreditByID(String clientID) {
        try {
            return userServerNetworkService.searchCreditByID(clientID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StaffPO getStaffByHotelID(String hotelID) {
        try {
            return userServerNetworkService.getStaffByHotelID(hotelID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addLevel(LevelPO levelPO) {
        try {
            return userServerNetworkService.addLevel(levelPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateLevel(String ID, LevelPO levelPO) {
        try {
            return userServerNetworkService.updateLevel(ID, levelPO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage deleteLevel(String ID) {
        try {
            return userServerNetworkService.deleteLevel(ID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LevelPO getLevel(String level) {
        try {
            return userServerNetworkService.getLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<LevelPO> getAllLevel() {
        try {
            return userServerNetworkService.getAllLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getLevelByCredit(int credit) {
        try {
            return userServerNetworkService.getLevelByCredit(credit);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
