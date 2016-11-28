package ui.viewcontroller.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.hotel.ClientHotelSearchPaneController;
import ui.componentcontroller.order.ClientOrderSearchPaneController;

import java.io.IOException;

/**
 * Created by Sorumi on 16/11/27.
 */
public class ClientHotelListViewController {

    private ClientSearchHotelViewController clientSearchHotelViewController;

    @FXML
    private VBox contentVBox;

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/hotel/ClientHotelSearchPane.fxml"));
            VBox pane = loader.load();

            ClientHotelSearchPaneController controller = loader.getController();
            controller.setClientHotelListViewController(this);

            contentVBox.getChildren().add(pane);

//            for (int i=0; i<4; i++) {
//                FXMLLoader cellLoader = new FXMLLoader();
//                cellLoader.setLocation(Main.class.getResource("../component/order/OrderCell.fxml"));
//                HBox ordercell = cellLoader.load();
//
//                cellLoaders[i] = cellLoader;
//                cells[i] = ordercell;
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setClientSearchHotelViewController(ClientSearchHotelViewController clientSearchHotelViewController) {
        this.clientSearchHotelViewController = clientSearchHotelViewController;
    }

}
