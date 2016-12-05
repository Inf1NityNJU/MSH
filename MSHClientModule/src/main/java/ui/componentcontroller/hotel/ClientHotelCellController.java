package ui.componentcontroller.hotel;

import component.ratestarpane.RateStarPane;
import component.starlabel.StarLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.viewcontroller.client.ClientHotelListViewController;
import vo.Hotel_BriefVO;

/**
 * Created by Sorumi on 16/12/2.
 */
public class ClientHotelCellController {

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


    private ClientHotelListViewController clientHotelListViewController;
    private Hotel_BriefVO hotel;

    public void setClientHotelListViewController(ClientHotelListViewController clientHotelListViewController) {
        this.clientHotelListViewController = clientHotelListViewController;
    }

    public void setHotel(Hotel_BriefVO hotel) {
        this.hotel = hotel;
        nameLabel.setText(hotel.name);
        addressLabel.setText("地址:"+hotel.address);
        starLabel.setStar(hotel.star);
        scoreLabel.setText(String.valueOf(hotel.score));
        rateScorePane.setScore((int)hotel.score);
        minPriceLabel.setText("￥"+String.valueOf(hotel.minPrice));
    }

    @FXML
    public void showHotelDetail() {
        clientHotelListViewController.showHotelDetail(hotel.ID);
    }
}
