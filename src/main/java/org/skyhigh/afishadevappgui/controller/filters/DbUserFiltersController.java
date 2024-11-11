package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class DbUserFiltersController {
    @FXML
    TextField userIdInputField;

    @FXML
    TextField authorIdInputField;

    @FXML
    TextField userLoginInputField;

    @FXML
    Label userIdLabel;

    @FXML
    Label authorIdLabel;

    @FXML
    Label loginLabel;

    public void initialize() throws CommonFlkException {
        setAuthorIdInputFieldChangeListener();
        setUserIdInputFieldChangeListener();
        setUserLoginInputFieldChangeListener();
    }

    public Integer getUserId() throws CommonFlkException {
        Integer parsedUserId = null;
        try {
            parsedUserId = ControllerUtils.getIntegerFromTextField(userIdInputField, getFieldLocalNameFromItsLabel(userIdLabel));
        } catch (CommonFlkException e) {
            return null;
        }
        if (parsedUserId != null && parsedUserId < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(String.format("Недопустимый формат поля '%s'", getFieldLocalNameFromItsLabel(userIdLabel)));
            alert.setContentText("Значение поля должно быть больше или равно нулю");

            alert.showAndWait();

            return null;
        }
        return parsedUserId;
    }

    public UUID getAuthorId() {
        try {
            return ControllerUtils.getUUIDFromTextField(authorIdInputField, getFieldLocalNameFromItsLabel(authorIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public String getUserLogin() {
        return userLoginInputField.getText();
    }

    private void setUserIdInputFieldChangeListener() {
        userIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            authorIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            userLoginInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setAuthorIdInputFieldChangeListener() {
        authorIdInputField.textProperty().addListener((observable, oldValue, newValue) ->{
            userIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            userLoginInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setUserLoginInputFieldChangeListener() {
        userLoginInputField.textProperty().addListener((observable, oldValue, newValue) ->{
            userIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            authorIdInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }
}
