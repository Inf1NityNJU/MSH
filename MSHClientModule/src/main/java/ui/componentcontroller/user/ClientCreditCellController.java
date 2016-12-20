package ui.componentcontroller.user;

import component.statebutton.StateButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.CreditVO;

/**
 * Created by Kray on 2016/11/29.
 */
public class ClientCreditCellController {

    private CreditVO creditVO;

    @FXML
    private Label dateLabel;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label deltaLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private StateButton actionButton;

    public void setCreditVO(CreditVO creditVO) {
        this.creditVO = creditVO;

        dateLabel.setText(creditVO.date.toString());
        if (creditVO.orderID.charAt(0) != '-') {
//            orderIDLabel.setText(creditVO.orderID.substring(0, creditVO.orderID.length()-1));
            orderIDLabel.setText(creditVO.orderID);
        } else {
            orderIDLabel.setText("/");
        }
        deltaLabel.setText(creditVO.deltaCredit + "");
        resultLabel.setText(creditVO.resultCredit + "");
        //CREDIT ACTION
        actionButton.setText(creditVO.creditAction.getName());
        actionButton.setColorProperty(creditVO.creditAction.getColor());
    }
}
