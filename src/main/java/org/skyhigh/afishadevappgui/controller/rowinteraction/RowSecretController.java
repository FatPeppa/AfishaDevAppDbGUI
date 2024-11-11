package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowSecretController {
    @FXML
    TextField secretIdField;

    @FXML
    TextField deploymentIdField;

    @FXML
    TextField loginField;

    @FXML
    TextField addressField;

    @FXML
    TextField passwordField;

    @FXML
    Label secretIdLabel;

    @FXML
    Label deploymentIdLabel;

    @FXML
    Label addressLabel;

    @FXML
    Label loginLabel;

    @FXML
    Label passwordLabel;

    public void initialize() throws CommonFlkException {}

    public void autoFillFields(Secret secret) {
        secretIdField.setText(secret.getSecretId().toString());
        deploymentIdField.setText(secret.getDeploymentId().toString());
        addressField.setText(secret.getAddress());
        loginField.setText(secret.getLogin());
        passwordField.setText(secret.getPassword());
    }

    public void clearFields() {
        secretIdField.setText(null);
        deploymentIdField.setText(null);
        addressField.setText(null);
        loginField.setText(null);
        passwordField.setText(null);
    }

    public Secret getSecret() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new Secret(
                        ControllerUtils.getUUIDFromTextField(
                                secretIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(secretIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                deploymentIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel)
                        ),
                        addressField.getText(),
                        loginField.getText(),
                        passwordField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении доступа к развертыванию должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getSecretId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                secretIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(secretIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (secretIdField.getText() == null || secretIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(secretIdLabel));
        if (deploymentIdField.getText() == null || deploymentIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel));
        if (addressField.getText() == null || addressField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(addressLabel));
        if (loginField.getText() == null || loginField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(loginLabel));
        //if (passwordField.getText() == null || passwordField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(passwordLabel));
        return emptyFields;
    }
}
