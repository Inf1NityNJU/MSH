package ui.viewcontroller.salesman;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.user.LevelCellController;
import vo.LevelVO;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Kray on 2016/11/30.
 */
public class LevelListViewController {

    private LevelManagementViewController levelManagementViewController;

    private UserBLService userBLService;
    private ArrayList<LevelVO> levelVOs;

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[]{};
    private Node[] cells = new Node[]{};

    public void setLevelManagementViewController(LevelManagementViewController levelManagementViewController) {
        this.levelManagementViewController = levelManagementViewController;
    }

    @FXML
    public void initialize() {
        showLevelList();
    }

    @FXML
    public void clickAddButton() {
        System.out.println("Add Level");

        try {
            FXMLLoader cellLoader = new FXMLLoader();
            cellLoader.setLocation(getClass().getResource("/component/user/LevelCell.fxml"));
            HBox clientCell = cellLoader.load();

            LevelCellController levelCellController = cellLoader.getController();
            levelCellController.setLevelVO(new LevelVO((levelVOs.size()+1)+"", ""));
            levelCellController.setLevelListViewController(this);

            contentVBox.getChildren().add(levelVOs.size(), clientCell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLevelList(){

        contentVBox.getChildren().clear();

        userBLService = UserBLFactory.getUserBLServiceImpl_Salesman();
        levelVOs = userBLService.getAllLevel();

        levelVOs.sort(new LevelComparator());

        if (levelVOs == null) {
            System.out.println("NO LEVEL INFO");
        } else {
            try {

                cellLoaders = new FXMLLoader[levelVOs.size()];
                cells = new Node[levelVOs.size()];

                for (int i = levelVOs.size() - 1; i >= 0; i--) {

                    FXMLLoader cellLoader = new FXMLLoader();
                    cellLoader.setLocation(getClass().getResource("/component/user/LevelCell.fxml"));
                    HBox clientCell = cellLoader.load();

                    cellLoaders[i] = cellLoader;
                    cells[i] = clientCell;

                    LevelVO levelVO = levelVOs.get(i);
                    FXMLLoader loader = cellLoaders[i];

                    LevelCellController levelCellController = loader.getController();
                    levelCellController.setLevelVO(levelVO);
                    levelCellController.setLevelListViewController(this);

                    contentVBox.getChildren().add(0, clientCell);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private class LevelComparator implements Comparator<LevelVO> {

        public int compare(LevelVO l1, LevelVO l2) {
            return Integer.parseInt(l1.level) - Integer.parseInt(l2.level);
        }

    }


}
