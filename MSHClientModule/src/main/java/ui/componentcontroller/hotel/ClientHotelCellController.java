package ui.componentcontroller.hotel;

import component.ratestarpane.RateStarPane;
import component.starlabel.StarLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.viewcontroller.client.ClientHotelListViewController;
import vo.Hotel_BriefVO;
import vo.Hotel_DetailVO;

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
    }

    @FXML
    public void showHotelDetail() {
        clientHotelListViewController.showHotelDetail(hotel.ID);
    }
}
