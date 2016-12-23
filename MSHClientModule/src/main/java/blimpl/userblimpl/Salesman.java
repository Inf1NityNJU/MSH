package blimpl.userblimpl;

import po.LevelPO;
import po.SalesmanPO;
import util.ResetState;
import util.ResultMessage;
import vo.LevelVO;
import vo.SalesmanVO;
import vo.SalesmanVO_Register;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/10/30.
 */
public class Salesman extends User {

    public Salesman() {
        super();
    }

    /**
     * 修改密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public ResetState resetPassword(String account, String oldPassword, String newPassword) {
        if (userClientNetwork.resetPassword(account, oldPassword, newPassword) == ResultMessage.SUCCESS) {
            return ResetState.RESET_SUCCESS;
        } else {
            return ResetState.RESET_FAIL;
        }
    }

    /**
     * 增加网站营销人员
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        SalesmanVO_Register salesmanVO = (SalesmanVO_Register) userVO;
        SalesmanPO salesmanPO = new SalesmanPO(
                null,
                salesmanVO.salesmanName,
                salesmanVO.account,
                salesmanVO.password);
        return userClientNetwork.addSalesman(salesmanPO);
    }

    /**
     * 根据ID查找网站营销人员
     *
     * @param salesmanID
     * @return 符合ID的SalesmanVO
     */
    public SalesmanVO searchByID(String salesmanID) {
        SalesmanPO salesmanPO = userClientNetwork.searchSalesmanByID(salesmanID);
        if (salesmanPO == null) {
            return null;
        } else {
            return new SalesmanVO(
                    salesmanPO.getSalesmanID(),
                    salesmanPO.getSalesmanName(),
                    salesmanPO.getAccount());
        }
    }

    /**
     * 更新网站营销人员
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        SalesmanVO salesmanVO = (SalesmanVO) userVO;
        SalesmanPO tmpPO = userClientNetwork.searchSalesmanByID(salesmanVO.salesmanID);
        SalesmanPO SalesmanPO = new SalesmanPO(
                salesmanVO.salesmanID,
                salesmanVO.salesmanName,
                tmpPO.getAccount(),
                tmpPO.getPassword());
        return userClientNetwork.updateSalesman(salesmanVO.salesmanID, SalesmanPO);
    }

    /**
     * 删除网站营销人员
     *
     * @param salesmanID
     * @return 是否删除成功
     */
    public ResultMessage delete(String salesmanID) {
        return userClientNetwork.deleteSalesman(salesmanID);
    }

    /**
     * 根据关键词搜索网站营销人员
     *
     * @param keyword
     * @return 符合关键词的所有网站营销人员
     */
    public ArrayList<SalesmanVO> search(String keyword) {
        ArrayList<SalesmanPO> salesmanPOs = userClientNetwork.searchSalesman(keyword);
        ArrayList<SalesmanVO> salesmanVOs = new ArrayList<SalesmanVO>();
        salesmanPOs.forEach(salesmanPO -> salesmanVOs.add(new SalesmanVO(
                salesmanPO.getSalesmanID(),
                salesmanPO.getSalesmanName(),
                salesmanPO.getAccount())));
        return salesmanVOs;
    }

    /**
     * 增加一条等级信息
     *
     * @return
     */
    public ResultMessage addLevel(LevelVO levelVO) {
        LevelPO levelPO = new LevelPO(levelVO.level, Integer.parseInt(levelVO.level), Integer.parseInt(levelVO.credit));
        return userClientNetwork.addLevel(levelPO);
    }

    /**
     * 更新一条等级信息
     *
     * @return
     */
    public ResultMessage updateLevel(LevelVO levelVO) {
        LevelPO levelPO = new LevelPO(levelVO.level, Integer.parseInt(levelVO.level), Integer.parseInt(levelVO.credit));
        return userClientNetwork.updateLevel(levelVO.level, levelPO);
    }

    /**
     * 删除一条等级信息
     *
     * @return
     */
    public ResultMessage deleteLevel(String ID) {
        return userClientNetwork.deleteLevel(ID);
    }

    /**
     * 得到所有等级信息
     *
     * @return
     */
    public ArrayList<LevelVO> getAllLevel() {
        ArrayList<LevelPO> levelPOs = userClientNetwork.getAllLevel();
        ArrayList<LevelVO> levelVOs = new ArrayList<LevelVO>();
        if (levelPOs.size() > 0) {
            levelPOs.forEach(levelPO -> levelVOs.add(new LevelVO(levelPO.getLevel() + "", levelPO.getCredit() + "")));
            levelVOs.sort((LevelVO l1, LevelVO l2) -> Integer.parseInt(l1.level) - Integer.parseInt(l2.level));
            return levelVOs;
        } else {
            return null;
        }
    }

}
