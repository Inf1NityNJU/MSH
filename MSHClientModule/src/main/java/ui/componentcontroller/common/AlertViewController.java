package ui.componentcontroller.common;

import bl.blfactory.BLFactoryImpl;
import component.rectbutton.RectButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import main.Main;


import java.io.IOException;

/**
 * Created by vivian on 16/12/10.
 */
public class AlertViewController {
    @FXML
    private Label infoLabel;

    @FXML
    private RectButton sureButton;

    @FXML
    private RectButton cancelButton;

    @FXML
    private Pane leftPane;

    private ObjectProperty<EventHandler<Event>> onClickSureButton = new SimpleObjectProperty<EventHandler<Event>>();

    private ObjectProperty<EventHandler<Event>> onClickCancelButton = new SimpleObjectProperty<EventHandler<Event>>();

    @FXML
    public void initialize() {
        leftPane.setVisible(true);
        leftPane.setManaged(true);

        setOnClickSureButton(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

            }
        });

        setOnClickCancelButton(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

            }
        });

    }

    public void setInfoLabel(String info) {
        this.infoLabel.setText(info);
    }

     /* onClickSureButton */

    public final ObjectProperty<EventHandler<Event>> onClickSureButtonProperty() {
        return onClickSureButton;
    }

    public final void setOnClickSureButton(EventHandler<Event> handler) {
        onClickSureButton.set(handler);
    }

    public final EventHandler<Event> getOnClickSureButton() {
        return onClickSureButton.get();

    }

      /* onClickCancelButton */

    public final ObjectProperty<EventHandler<Event>> onClickCancelButtonProperty() {
        return onClickCancelButton;
    }

    public final void setOnClickCancelButton(EventHandler<Event> handler) {
        onClickCancelButton.set(handler);
    }

    public final EventHandler<Event> getOnClickCancelButton() {
        return onClickCancelButton.get();

    }



    @FXML
    public void clickSureButton(Event event) {
        onClickSureButton.get().handle(event);
    }

    public void clickCancelButton(Event event) {
        onClickCancelButton.get().handle(event);
    }

    public void hideLeftButton() {
        leftPane.setVisible(false);
        leftPane.setManaged(false);
    }
}
