package ui.componentcontroller.hotel;

import blimpl.blfactory.BLFactoryImpl;
import blservice.orderblservice.OrderBLService;
import blservice.userblservice.UserBLInfo;
import component.ratestarpane.RateStarPane;
import component.starlabel.StarLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.viewcontroller.client.ClientHotelListViewController;
import vo.Hotel_BriefVO;
import vo.Hotel_DetailVO;
import vo.OrderVO;

import java.util.ArrayList;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelCellController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private StarLabel starLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private RateStarPane rateScorePane;

    @FXML
    private Label minPriceLabel;

    @FXML
    private Label unexecutedLabel;

    @FXML
    private Label executedLabel;

    @FXML
    private Label cancelledLabel;

    @FXML
    private Label abnormalLabel;

    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Client();
    private OrderBLService orderBLService = new BLFactoryImpl().getOrderBLService();

    private ClientHotelListViewController clientHotelListViewController;
    private Hotel_DetailVO hotel;

    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    public void setHotel(Hotel_DetailVO hotel) {
        this.hotel = hotel;
        int num = hotel.ID.charAt(7) - '0';
        imageView.setImage(new Image(getClass().getResource("/images/hotel/" + num + ".png").toExternalForm()));

        nameLabel.setText(hotel.name);
        addressLabel.setText("地址：" + hotel.address);
        starLabel.setStar(hotel.star);
        scoreLabel.setText(String.valueOf(hotel.score));
        rateScorePane.setScore((int) hotel.score);
        minPriceLabel.setText("￥" + String.valueOf(hotel.minPrice));

        // Orders
        String clientID = userBLInfo.getCurrentClientID();
        ArrayList<OrderVO> orderVOs = orderBLService.searchClientHotelOrder(clientID, hotel.ID);

        int count[] = {0, 0, 0, 0};

        for (OrderVO orderVO : orderVOs) {
            int n = orderVO.state.getNum();
            count[n]++;
        }

        unexecutedLabel.setText(count[0] > 0 ? "未执行 × " + count[0] + " " : "");
        executedLabel.setText(count[1] > 0 ? "已执行 × " + count[1] + " " : "");
        cancelledLabel.setText(count[2] > 0 ? "已撤销 × " + count[2] + " " : "");
        abnormalLabel.setText(count[3] > 0 ? "异常 × " + count[3] + " " : "");

    }

    @FXML
    public void showHotelDetail() {
        clientHotelListViewController.showHotelDetail(hotel.ID);
    }
}
