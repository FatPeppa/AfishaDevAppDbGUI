package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class DeploymentStatusFiltersController {
    @FXML
    TextField deploymentStatusIdInputField;

    @FXML
    TextField deploymentStatusNameInputField;

    @FXML
    Label deploymentStatusIdLabel;

    @FXML
    Label statusNameLabel;

    public void initialize() {
        setDeploymentStatusIdInputFieldChangeListener();
        setDeploymentStatusNameInputFieldChangeListener();
    }

    public UUID getDeploymentStatusId() {
        return ControllerUtils.getUUIDFromTextField(deploymentStatusIdInputField, getFieldLocalNameFromItsLabel(deploymentStatusIdLabel));
    }

    public String getDeploymentStatusName() {
        return deploymentStatusNameInputField.getText();
    }

    private void setDeploymentStatusIdInputFieldChangeListener() {
        deploymentStatusIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                deploymentStatusNameInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setDeploymentStatusNameInputFieldChangeListener() {
        deploymentStatusNameInputField.textProperty().addListener((observable, oldValue, newValue) ->
                deploymentStatusIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
}
