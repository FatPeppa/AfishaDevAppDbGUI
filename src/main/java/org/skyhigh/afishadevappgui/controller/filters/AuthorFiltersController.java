package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class AuthorFiltersController {
    @FXML
    TextField authorIdInputField;

    @FXML
    TextField authorLoginInputField;

    @FXML
    Label authorIdLabel;

    @FXML
    Label authorLoginLabel;

    public void initialize() {
        setAuthorIdInputFieldChangeListener();
        setAuthorLoginInputFieldChangeListener();
    }

    public UUID getAuthorId() {
        return ControllerUtils.getUUIDFromTextField(authorIdInputField, getFieldLocalNameFromItsLabel(authorIdLabel));
    }

    public String getAuthorLogin() {
        return authorLoginInputField.getText();
    }

    private void setAuthorIdInputFieldChangeListener() {
        authorIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
            authorLoginInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setAuthorLoginInputFieldChangeListener() {
        authorLoginInputField.textProperty().addListener((observable, oldValue, newValue) ->
                authorIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
}
