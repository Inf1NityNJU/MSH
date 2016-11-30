package ui.componentcontroller.user;

import component.rectbutton.RectButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vo.LevelVO;

/**
 * Created by Kray on 2016/11/30.
 */
public class LevelCellController {

    private final static int WIDTH = 70;

    private LevelVO levelVO;

    @FXML
    private Label levelLabel;

    @FXML
    private Label creditLabel;

    @FXML
    private TextField creditText;

    @FXML
    private RectButton actionButton;

    public void setLevelVO(LevelVO levelVO) {
        this.levelVO = levelVO;

        levelLabel.setText(levelVO.level);
        creditLabel.setText(levelVO.credit);
        creditText.setText(levelVO.credit);

        creditLabel.setVisible(true);
        creditLabel.setPrefWidth(WIDTH);
        creditLabel.setMinWidth(WIDTH);
        creditLabel.setMaxWidth(WIDTH);

        creditText.setVisible(false);
        creditText.setPrefWidth(0);
        creditText.setMinWidth(0);
        creditText.setMaxWidth(0);
    }

    @FXML
    public void clickEditButton(){
        if(actionButton.getText().equals("编 辑")) {
            actionButton.setText("保 存");
            creditLabel.setVisible(false);
            creditLabel.setPrefWidth(0);
            creditLabel.setMinWidth(0);
            creditLabel.setMaxWidth(0);

            creditText.setVisible(true);
            creditText.setPrefWidth(WIDTH);
            creditText.setMinWidth(WIDTH);
            creditText.setMaxWidth(WIDTH);
        }else{
            actionButton.setText("编 辑");
            creditLabel.setVisible(true);
            creditLabel.setPrefWidth(WIDTH);
            creditLabel.setMinWidth(WIDTH);
            creditLabel.setMaxWidth(WIDTH);

            creditText.setVisible(false);
            creditText.setPrefWidth(0);
            creditText.setMinWidth(0);
            creditText.setMaxWidth(0);
        }
    }
}
