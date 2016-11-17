package ui.controller;

import component.NavButton;
import javafx.fxml.FXML;

/**
 * Created by Sorumi on 16/11/17.
 */
public class NavbarController {

    @FXML
    private NavButton navButton;

    @FXML
    public void clickNavButton() {
        if (navButton.getIsCurrentProperty()) {
            navButton.setIsCurrentProperty(false);
        } else {
            navButton.setIsCurrentProperty(true);
        }

    }
}
