package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowDeploymentStatusController {
    @FXML
    TextField deploymentStatusIdField;

    @FXML
    TextField nameField;

    @FXML
    Label deploymentStatusIdLabel;

    @FXML
    Label statusNameLabel;

    private String deploymentStatusIdFieldPrompt;
    private String nameFieldPrompt;

    private String deploymentStatusIdFieldBasicStyle;
    private String nameFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        deploymentStatusIdFieldPrompt = deploymentStatusIdField.getPromptText();
        nameFieldPrompt = nameField.getPromptText();

        deploymentStatusIdFieldBasicStyle = deploymentStatusIdField.getStyle();
        nameFieldBasicStyle = nameField.getStyle();
    }

    public void autoFillFields(DeploymentStatus deploymentStatus) {
        deploymentStatusIdField.setText(deploymentStatus.getDeploymentStatusId().toString());
        nameField.setText(deploymentStatus.getStatusName());
    }

    public void clearFields() {
        deploymentStatusIdField.setText(null);
        nameField.setText(null);
    }

    public DeploymentStatus getDeploymentStatus() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new DeploymentStatus(
                        ControllerUtils.getUUIDFromTextField(
                                deploymentStatusIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentStatusIdLabel)
                        ),
                        nameField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении статуса развертывания должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getDeploymentStatusId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                deploymentStatusIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentStatusIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (deploymentStatusIdField.getText() == null || deploymentStatusIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(deploymentStatusIdLabel));
        if (nameField.getText() == null || nameField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(statusNameLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            deploymentStatusIdField.setPromptText(deploymentStatusIdFieldPrompt);
            deploymentStatusIdField.setEditable(false);
            deploymentStatusIdField.setStyle(deploymentStatusIdFieldBasicStyle);

            nameField.setPromptText(nameFieldPrompt);
            nameField.setEditable(true);
            nameField.setStyle(nameFieldBasicStyle);
        } else {
            deploymentStatusIdField.setPromptText("Заполняется автоматически");
            deploymentStatusIdField.setEditable(false);
            deploymentStatusIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            nameField.setPromptText("Заполняется автоматически");
            nameField.setEditable(false);
            nameField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
