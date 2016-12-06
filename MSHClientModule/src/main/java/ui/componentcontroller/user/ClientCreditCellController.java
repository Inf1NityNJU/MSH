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
            orderIDLabel.setText(creditVO.orderID);
        } else {
            orderIDLabel.setText("/");
        }
        deltaLabel.setText(creditVO.deltaCredit + "");
        resultLabel.setText(creditVO.resultCredit + "");
        //CREDIT ACTION
        switch (creditVO.creditAction) {
            case ADD_CREDIT:
                actionButton.setText("执行订单");
                actionButton.setColorProperty("12B7F3");
                break;
            case REVOKE_CREDIT:
                actionButton.setText("申诉成功");
                actionButton.setColorProperty("F8E81C");
                break;
            case DEDUCT_CREDIT:
                actionButton.setText("撤销订单");
                actionButton.setColorProperty("FC537D");
                break;
            case INIT_CREDIT:
                actionButton.setText("初始信用");
                actionButton.setColorProperty("00CCCC");
                break;
            case RECHARGE_CREDIT:
                actionButton.setText("信用充值");
                actionButton.setColorProperty("BC52FD");
                break;
            case OVERDUE_CREDIT:
                actionButton.setText("订单超时");
                actionButton.setColorProperty("FD9C4B");
                break;
        }
    }
}
