package org.skyhigh.afishadevappgui.common.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class FlkExceptionMessageController {
    @FXML
    private Label errorMessage ;

    public void setErrorText(String text) {
        errorMessage.setText(text);
    }

    @FXML
    private void close() {
        errorMessage.getScene().getWindow().hide();
    }
}
