package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class SecretFiltersController {
    @FXML
    TextField secretIdInputField;

    @FXML
    TextField deploymentIdInputField;

    @FXML
    TextField addressInputField;

    @FXML
    TextField loginInputField;

    @FXML
    Label deploymentIdLabel;

    @FXML
    Label secretIdLabel;

    @FXML
    Label loginLabel;

    @FXML
    Label addressLabel;

    public void initialize() {
        setSecretIdInputFieldChangeListener();
        setDeploymentIdInputFieldChangeListener();
        setAddressInputFieldChangeListener();
        setLoginInputFieldChangeListener();
    }

    public UUID getSecretId() {
        return ControllerUtils.getUUIDFromTextField(secretIdInputField, getFieldLocalNameFromItsLabel(secretIdLabel));
    }

    public UUID getDeploymentId() {
        return ControllerUtils.getUUIDFromTextField(deploymentIdInputField, getFieldLocalNameFromItsLabel(deploymentIdLabel));
    }

    public String getAddress() {
        return addressInputField.getText();
    }

    public String getLogin() {
        return loginInputField.getText();
    }

    private void setSecretIdInputFieldChangeListener() {
        secretIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            addressInputField.setDisable(newValue != null && !newValue.isEmpty());
            loginInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setDeploymentIdInputFieldChangeListener() {
        deploymentIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            secretIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            addressInputField.setDisable(newValue != null && !newValue.isEmpty());
            loginInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setAddressInputFieldChangeListener() {
        addressInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            secretIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loginInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoginInputFieldChangeListener() {
        loginInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            addressInputField.setDisable(newValue != null && !newValue.isEmpty());
            secretIdInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }
}
